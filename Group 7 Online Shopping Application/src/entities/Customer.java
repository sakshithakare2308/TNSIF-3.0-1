package entities;

public class Customer {
    private int customerId;
    private String username;
    private String email;
    private String address;

    public Customer(int customerId, String username, String email, String address) {
        this.customerId = customerId;
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public int getCustomerId() { return customerId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return String.format("%-6d %-14s %-22s %-18s", customerId, username, email, address);
    }
}
