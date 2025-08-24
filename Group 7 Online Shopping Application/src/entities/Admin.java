package entities;

public class Admin {
    private int adminId;
    private String username;
    private String password;
    private String email;

    public Admin(int adminId, String username, String password, String email) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getAdminId() { return adminId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("%-6d %-14s %-22s", adminId, username, email);
    }
}
