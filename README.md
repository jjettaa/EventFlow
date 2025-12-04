# Event Ticketing Platform – Part 2

This project is a simple **console-based Event Ticketing Platform** written in Java.  
Users can register, organizers can create events and ticket types, tickets can be sold and paid, and events can be cancelled with automatic ticket and payment handling.

This is **Part 2** of the project and focuses on adding more business logic and a clearer separation between the CLI, services, and repositories.

---

## Tech Stack & Prerequisites

- **Language:** Java (tested with **Java 21**, Java 17+ should also work)
- **Build/Run:** No external build tool required (simple `javac` + `java`)
- **Recommended IDE:**
  - **Visual Studio Code** with the **Extension Pack for Java**  
    (this is the easiest way to compile and run via the *Run* button)

---

## Main Features (Part 2)

- **Console Menu (CLI)**
  - Text-based menu in `Application.java` for interacting with the whole system

- **User & Organizer Management**
  - Register users (ticket buyers)
  - Register organizers (event owners)
  - View all users and organizers

- **Event & Ticket Management**
  - Create events (with future date validation)
  - Create ticket types for events and auto-generate tickets
  - Tickets track `sold` and `cancelled` state

- **Ordering & Payments**
  - Create orders only for valid users and available tickets
  - Process payments based on order total
  - Block payments if the event is cancelled
  - Show payments as **PAID** or **REFUNDED**

- **Event Cancellation**
  - Cancel an event
  - Automatically cancel all tickets for that event
  - Automatically mark related payments as refunded

- **Notifications**
  - Send notifications to valid users
  - View all sent notifications

- **View Operations**
  - View all events, tickets, orders, payments, users, organizers, and notifications

---