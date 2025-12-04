import services.*;
import models.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        EventService eventService = new EventService();
        TicketService ticketService = new TicketService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new NotificationService();
        UserService userService = new UserService();
        OrganizerService organizerService = new OrganizerService();
        TickettypeService tickettypeService = new TickettypeService();

        System.out.println("=== EVENT TICKETING PLATFORM ===");

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Sign Up User (register new user)");
            System.out.println("2. Register Organizer (event owner)");
            System.out.println("3. Create Event");
            System.out.println("4. Create Tickettype AND Generate Tickets");
            System.out.println("5. Create Order (user buys a ticket)");
            System.out.println("6. Process Payment for an Order");
            System.out.println("7. Send Notification to a User");
            System.out.println("8. Cancel Event");
            System.out.println("9. View All Events");
            System.out.println("10. View All Tickets");
            System.out.println("11. View All Orders");
            System.out.println("12. View All Payments");
            System.out.println("13. View All Notifications");
            System.out.println("14. View All Users");
            System.out.println("15. View All Organizers");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

                // 1. SIGN UP USER (self-registration)
                case 1 -> {
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter user email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    User user = userService.registerUser(name, email, password);
                    System.out.println("User registered with ID: " + user.getUserId());
                }

                // 2. REGISTER ORGANIZER
                case 2 -> {
                    System.out.print("Enter organizer name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter organizer email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter organizer password: ");
                    String password = scanner.nextLine();

                    Organizer organizer = organizerService.createOrganizer(name, email, password);
                    System.out.println("Organizer registered with ID: " + organizer.getOrganizerId());
                }

                // 3. CREATE EVENT  (only if at least one organizer exists)
                case 3 -> {
                    List<Organizer> organizers = organizerService.getAllOrganizers();
                    if (organizers.isEmpty()) {
                        System.out.println("No organizers found. Please register an organizer first (option 2).");
                        break;
                    }

                    System.out.println("\n-- ORGANIZERS --");
                    organizers.forEach(o -> System.out.println(
                            "ID: " + o.getOrganizerId() +
                                    " | Name: " + o.getName() +
                                    " | Email: " + o.getEmail()
                    ));

                    // Ask which organizer owns this event
                    System.out.print("\nEnter organizer ID for this event: ");
                    int organizerId = scanner.nextInt();
                    scanner.nextLine(); 
                    Organizer organizer = organizerService.getById(organizerId);
                    if (organizer == null) {
                        System.out.println("Invalid organizer ID. Event not created.");
                        break;
                    }

                    System.out.print("Enter event title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter event description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter event location: ");
                    String location = scanner.nextLine();

                    LocalDate date = null;
                    while (date == null) {
                        System.out.print("Enter event date (YYYY-MM-DD): ");
                        String dateInput = scanner.nextLine();
                        try {
                            date = LocalDate.parse(dateInput);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date. Please use format YYYY-MM-DD, e.g. 2025-12-15.");
                        }
                    }

                    LocalDate today = LocalDate.now();
                    if (date.isBefore(today)) {
                        System.out.println("Event date cannot be in the past. Event not created.");
                        break;
                    }

                    Event event = new Event(0, title, description, location, date, organizerId);
                    boolean success = eventService.createEvent(event);

                    if (success) {
                        System.out.println("Event created successfully!");
                        System.out.println("➡ Event ID: " + event.getEventId());
                        System.out.println("➡ Title: " + event.getTitle());
                        System.out.println("➡ Date: " + event.getDate());
                        System.out.println("➡ Organizer ID: " + event.getOrganizerId());
                    } else {
                        System.out.println("Failed to create event.");
                    }
                }

                // 4. CREATE TICKETTYPE AND GENERATE TICKETS
                case 4 -> {
                    List<Event> events = eventService.getAllEvents();
                    if (events.isEmpty()) {
                        System.out.println("No events found. Please create an event first (option 3).");
                        break;
                    }

                    System.out.println("\n--- EVENTS ---");
                    events.forEach(e -> System.out.println(
                            "ID: " + e.getEventId() +
                                    " | " + e.getTitle() +
                                    " | " + e.getDate() +
                                    " | Cancelled: " + e.isCancelled()
                    ));

                    System.out.print("\nEnter event ID to create ticket types for: ");
                    int eventId = scanner.nextInt();   
                    scanner.nextLine(); 

                    Event selectedEvent = eventService.getEventById(eventId);
                    if (selectedEvent == null) {
                        System.out.println("Invalid event ID. No ticket types created.");
                        break;
                    }

                    if (selectedEvent.isCancelled()) {
                        System.out.println(
                                "This event is cancelled. You cannot create new ticket types for a cancelled event.");
                        break;
                    }

                    System.out.print("Enter ticket type name (e.g. Standard, VIP): ");
                    String typeName = scanner.nextLine();

                    System.out.print("Enter ticket price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter how many tickets to generate: ");
                    int quantity = scanner.nextInt();

                    // simple validation (optional but good practice)
                    if (price <= 0) {
                        System.out.println("Price must be positive. Tickettype not created.");
                        break;
                    }
                    if (quantity <= 0) {
                        System.out.println("Quantity must be positive. Tickettype not created.");
                        break;
                    }

                    Tickettype type = tickettypeService.createTickettype(eventId, typeName, price, quantity);
                    System.out.println("Tickettype created with ID: " + type.getTicketTypeId());

                    for (int i = 0; i < quantity; i++) {
                        Ticket ticket = new Ticket(
                                0,
                                eventId,
                                type.getTicketTypeId(),
                                price,
                                false
                        );
                        ticketService.issueTicket(ticket);
                    }

                    System.out.println("Generated " + quantity + " tickets for this ticket type (" + type.getName() + ").");
                }


                // 5. CREATE ORDER (USER BUYS A TICKET)
                case 5 -> {
                    // Show available tickets (unsold)
                    System.out.println("\n--- AVAILABLE TICKETS (unsold) ---");
                    List<Ticket> tickets = ticketService.getAllTickets();
                    boolean anyAvailable = false;
                    for (Ticket t : tickets) {
                        if (!t.isSold() && !t.isCancelled()) {
                            anyAvailable = true;

                            Tickettype type = tickettypeService.getById(t.getTicketTypeId());
                            String typeName = (type != null) ? type.getName() : "(no type)";

                            System.out.println(
                                    "Ticket ID: " + t.getTicketId() +
                                    " | Event " + t.getEventId() +
                                    " | Type: " + typeName +
                                    " | Price: " + t.getPrice()
                            );
                        }
                    }
                    if (!anyAvailable) {
                        System.out.println("No available tickets for sale. They may all be sold or the event was cancelled.");
                        break;
                    }

                    // Show users – this enforces "no order without user"
                    System.out.println("\n--- USERS ---");
                    List<User> users = userService.getAllUsers();
                    if (users.isEmpty()) {
                        System.out.println("No users found. Please sign up a user first (option 1).");
                        break;
                    }
                    users.forEach(u -> System.out.println(
                            "ID: " + u.getUserId() +
                                    " | Name: " + u.getName() +
                                    " | Email: " + u.getEmail()
                    ));

                    System.out.print("\nEnter user ID: ");
                    int userId = scanner.nextInt();

                    // Validate that this userId actually exists in the list
                    boolean userExists = false;
                    for (User u : users) {
                        if (u.getUserId() == userId) {
                            userExists = true;
                            break;
                        }
                    }
                    if (!userExists) {
                        System.out.println("Invalid user ID. Order not created.");
                        break;
                    }

                    System.out.print("Enter ticket ID: ");
                    int ticketId = scanner.nextInt();

                    Ticket ticket = ticketService.getById(ticketId);
                    if (ticket == null || ticket.isSold() || ticket.isCancelled()) {
                        System.out.println("Invalid, sold, or cancelled ticket!");
                        break;
                    }

                    // Use ticket price as order total
                    Order order = new Order(0, userId, ticketId, ticket.getPrice());
                    boolean success = orderService.createOrder(order);

                    System.out.println(success ? "Order created!" : "Unable to create order.");
                }


                // 6. PROCESS PAYMENT FOR AN ORDER
                case 6 -> {
                    List<Order> orders = orderService.getAllOrders();

                    if (orders.isEmpty()) {
                        System.out.println("No orders found. Please create an order first (option 5).");
                        break;
                    }

                    System.out.println("\n--- AVAILABLE ORDERS ---");
                    orders.forEach(o -> System.out.println(
                            "ID: " + o.getOrderId() +
                                    " | User ID: " + o.getUserId() +
                                    " | Ticket ID: " + o.getTicketId() +
                                    " | Total Price: " + o.getTotalPrice()
                    ));

                    System.out.print("\nEnter order ID: ");
                    int orderId = scanner.nextInt();

                    // Check that the orderId exists in the list
                    Order selectedOrder = null;
                    for (Order o : orders) {
                        if (o.getOrderId() == orderId) {
                            selectedOrder = o;
                            break;
                        }
                    }

                    if (selectedOrder == null) {
                        System.out.println("Invalid order ID. Payment not processed.");
                        break;
                    }

                    System.out.println("Total amount to pay for this order: " + selectedOrder.getTotalPrice());

                    System.out.print("Enter payment amount: ");
                    double amount = scanner.nextDouble();

                    // Basic validation of amount
                    if (amount <= 0) {
                        System.out.println("Payment amount must be positive. Payment not processed.");
                        break;
                    }

                    boolean success = paymentService.processPayment(orderId, amount);

                    System.out.println(success ? "Payment processed!" : "Payment failed.");
                }

                // 7. SEND NOTIFICATION TO A USER
                case 7 -> {
                    List<User> users = userService.getAllUsers();
                    if (users.isEmpty()) {
                        System.out.println("No users found. Please sign up a user first (option 1).");
                        break;
                    }

                    System.out.println("\n--- USERS ---");
                    users.forEach(u -> System.out.println(
                            "ID: " + u.getUserId() +
                                    " | Name: " + u.getName() +
                                    " | Email: " + u.getEmail()
                    ));

                    System.out.print("\nEnter user ID to send notification to: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); 

                    // validate user ID
                    boolean userExists = false;
                    for (User u : users) {
                        if (u.getUserId() == userId) {
                            userExists = true;
                            break;
                        }
                    }
                    if (!userExists) {
                        System.out.println("Invalid user ID. Notification not sent.");
                        break;
                    }

                    System.out.print("Enter message: ");
                    String msg = scanner.nextLine();

                    Notification n = new Notification(0, userId, msg);
                    boolean success = notificationService.sendNotification(n);

                    System.out.println(success ? "Notification sent!" : "Failed to send notification.");
                }

                // 8. CANCEL EVENT
                case 8 -> {
                    List<Event> events = eventService.getAllEvents();
                    if (events.isEmpty()) {
                        System.out.println("No events available.");
                        break;
                    }

                    System.out.println("\n-- EVENTS --");
                    events.forEach(e -> System.out.println(
                            "ID: " + e.getEventId() +
                            " | Title: " + e.getTitle() +
                            " | Date: " + e.getDate() +
                            " | Cancelled: " + e.isCancelled()
                    ));

                    System.out.print("\nEnter event ID to cancel: ");
                    int eventId = scanner.nextInt();

                    boolean success = eventService.cancelEvent(eventId);
                    if (success) {
                        System.out.println("Event cancelled successfully.");
                        System.out.println("All related tickets were cancelled and payments refunded.");
                    } else {
                        System.out.println("Invalid event ID or cancellation failed.");
                    }
                }

                // 9. VIEW ALL EVENTS
                case 9 -> {
                    List<Event> events = eventService.getAllEvents();
                    System.out.println("\n-- EVENTS --");
                    if (events.isEmpty()) {
                        System.out.println("No events found.");
                    } else {
                        events.forEach(e -> System.out.println(
                                "ID: " + e.getEventId() +
                                        " | " + e.getTitle() +
                                        " | " + e.getDate() +
                                        " | Cancelled: " + e.isCancelled()
                        ));
                    }
                }

                // 10. VIEW ALL TICKETS
                case 10 -> {
                    List<Ticket> tickets = ticketService.getAllTickets();
                    System.out.println("\n-- TICKETS --");
                    if (tickets.isEmpty()) {
                        System.out.println("No tickets found.");
                    } else {
                        tickets.forEach(t -> {
                            Tickettype type = tickettypeService.getById(t.getTicketTypeId());
                            String typeName = (type != null) ? type.getName() : "(no type)";

                            System.out.println(
                                    "ID: " + t.getTicketId() +
                                    " | Event ID: " + t.getEventId() +
                                    " | Type: " + typeName +
                                    " | Price: " + t.getPrice() +
                                    " | Sold: " + t.isSold() +
                                    " | Cancelled: " + t.isCancelled()
                            );
                        });
                    }
                }

                // 11. VIEW ALL ORDERS
                case 11 -> {
                    List<Order> orders = orderService.getAllOrders();
                    System.out.println("\n-- ORDERS --");
                    if (orders.isEmpty()) {
                        System.out.println("No orders found.");
                    } else {
                        orders.forEach(o -> System.out.println(
                                "ID: " + o.getOrderId() +
                                        " | User ID: " + o.getUserId() +
                                        " | Ticket ID: " + o.getTicketId() +
                                        " | Total: " + o.getTotalPrice()
                        ));
                    }
                }

                // 12. VIEW ALL PAYMENTS
                case 12 -> {
                    List<Payment> payments = paymentService.getAllPayments();
                    System.out.println("\n-- PAYMENTS --");
                    if (payments.isEmpty()) {
                        System.out.println("No payments found.");
                    } else {
                        payments.forEach(p -> {
                            String status = p.isSuccess() ? "PAID" : "REFUNDED";
                            System.out.println(
                                    "ID: " + p.getPaymentId() +
                                    " | Order ID: " + p.getOrderId() +
                                    " | Amount: " + p.getAmount() +
                                    " | Status: " + status
                            );
                        });
                    }
                }

                // 13. VIEW ALL NOTIFICATIONS
                case 13 -> {
                    List<Notification> notes = notificationService.getAllNotifications();
                    System.out.println("\n-- NOTIFICATIONS --");
                    if (notes.isEmpty()) {
                        System.out.println("No notifications found.");
                    } else {
                        notes.forEach(n -> System.out.println(
                                "ID: " + n.getNotificationId() +
                                        " | To User: " + n.getRecipientId() +
                                        " | Message: " + n.getMessage()
                        ));
                    }
                }

                // 14. VIEW ALL USERS
                case 14 -> {
                    List<User> users = userService.getAllUsers();
                    System.out.println("\n-- USERS --");
                    if (users.isEmpty()) {
                        System.out.println("No users registered.");
                    } else {
                        users.forEach(u -> System.out.println(
                                "ID: " + u.getUserId() +
                                        " | Name: " + u.getName() +
                                        " | Email: " + u.getEmail()
                        ));
                    }
                }

                // 15. VIEW ALL ORGANIZERS
                case 15 -> {
                    List<Organizer> organizers = organizerService.getAllOrganizers();
                    System.out.println("\n-- ORGANIZERS --");
                    if (organizers.isEmpty()) {
                        System.out.println("No organizers registered.");
                    } else {
                        organizers.forEach(o -> System.out.println(
                                "ID: " + o.getOrganizerId() +
                                        " | Name: " + o.getName() +
                                        " | Email: " + o.getEmail()
                        ));
                    }
                }

                // EXIT
                case 0 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid option!");
            }
        }
    }
}
