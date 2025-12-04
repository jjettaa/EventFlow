package models;

public class Organizer {

    private int organizerId;
    private String name;
    private String email;
    private String password;  // 🔹 added

    public Organizer() {
    }

    public Organizer(int organizerId, String name) {
        this.organizerId = organizerId;
        this.name = name;
    }

    public Organizer(int organizerId, String name, String email) {
        this.organizerId = organizerId;
        this.name = name;
        this.email = email;
    }

    // 🔹 new constructor with password too
    public Organizer(int organizerId, String name, String email, String password) {
        this.organizerId = organizerId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getOrganizerId() { return organizerId; }
    public void setOrganizerId(int organizerId) { this.organizerId = organizerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
