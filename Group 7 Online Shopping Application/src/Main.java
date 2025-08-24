import entities.Customer;
import entities.Product;
import services.AdminService;
import services.CustomerService;
import services.OrderService;
import services.ProductService;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ProductService productService = new ProductService();
    private static final CustomerService customerService = new CustomerService();
    private static final AdminService adminService = new AdminService();
    private static final OrderService orderService = new OrderService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Online Shopping App ===");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Admin username: ");
                    String uname = sc.nextLine();
                    System.out.print("Admin password: ");
                    String pass = sc.nextLine();
                    if (adminService.validateAdminLogin(uname, pass)) {
                        adminMenu();
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                }
                case 2 -> customerMenu();
                case 0 -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Update Product Stock");
            System.out.println("5. Update Product Price");
            System.out.println("6. Create Admin");
            System.out.println("7. View Customers");
            System.out.println("8. View Orders (All)");
            System.out.println("9. Update Order Status");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int c = readInt();
            switch (c) {
                case 1 -> addProductUI();
                case 2 -> removeProductUI();
                case 3 -> viewProductsUI();
                case 4 -> updateStockUI();
                case 5 -> updatePriceUI();
                case 6 -> createAdminUI();
                case 7 -> viewCustomersUI();
                case 8 -> orderService.printAllOrders();
                case 9 -> updateOrderStatusUI();
                case 0 -> { return; }
                default -> System.out.println("Invalid");
            }
        }
    }

    private static void customerMenu() {
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Products");
            System.out.println("2. Create Customer");
            System.out.println("3. Place Order");
            System.out.println("4. View My Orders");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int c = readInt();
            switch (c) {
                case 1 -> viewProductsUI();
                case 2 -> createCustomerUI();
                case 3 -> placeOrderUI();
                case 4 -> viewMyOrdersUI();
                case 0 -> { return; }
                default -> System.out.println("Invalid");
            }
        }
    }

    private static void addProductUI() {
        System.out.print("Product ID: "); int id = readInt();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Price: "); double price = readDouble();
        System.out.print("Stock: "); int stock = readInt();
        boolean ok = productService.addProduct(new Product(id, name, price, stock));
        System.out.println(ok ? "Product added." : "Failed.");
    }

    private static void removeProductUI() {
        System.out.print("Product ID to remove: "); int id = readInt();
        boolean ok = productService.removeProduct(id);
        System.out.println(ok ? "Removed." : "Failed.");
    }

    private static void viewProductsUI() {
        List<Product> products = productService.getAllProducts();
        System.out.printf("%-6s %-22s %-10s %-6s%n", "ID", "Name", "Price", "Stock");
        for (Product p : products) System.out.println(p);
        if (products.isEmpty()) System.out.println("(no products)");
    }

    private static void updateStockUI() {
        System.out.print("Product ID: "); int id = readInt();
        System.out.print("New stock: "); int st = readInt();
        boolean ok = productService.updateProductStock(id, st);
        System.out.println(ok ? "Updated." : "Failed.");
    }

    private static void updatePriceUI() {
        System.out.print("Product ID: "); int id = readInt();
        System.out.print("New price: "); double pr = readDouble();
        boolean ok = productService.updateProductPrice(id, pr);
        System.out.println(ok ? "Updated." : "Failed.");
    }

    private static void createAdminUI() {
        System.out.print("Admin ID: "); int id = readInt();
        System.out.print("Username: "); String u = sc.nextLine();
        System.out.print("Password: "); String p = sc.nextLine();
        System.out.print("Email: "); String e = sc.nextLine();
        boolean ok = new AdminService().createAdmin(new entities.Admin(id, u, p, e));
        System.out.println(ok ? "Admin created." : "Failed.");
    }

    private static void viewCustomersUI() {
        var list = customerService.getAllCustomers();
        System.out.printf("%-6s %-14s %-22s %-18s%n", "ID", "Username", "Email", "Address");
        for (Customer c : list) System.out.println(c);
        if (list.isEmpty()) System.out.println("(no customers)");
    }

    private static void createCustomerUI() {
        System.out.print("Customer ID: "); int id = readInt();
        System.out.print("Username: "); String u = sc.nextLine();
        System.out.print("Email: "); String e = sc.nextLine();
        System.out.print("Address: "); String a = sc.nextLine();
        boolean ok = customerService.addCustomer(new Customer(id, u, e, a));
        System.out.println(ok ? "Customer created." : "Failed.");
    }

    private static void placeOrderUI() {
        System.out.print("Your customer ID: "); int cid = readInt();
        int oid = orderService.createOrder(cid);
        if (oid == -1) {
            System.out.println("Order creation failed.");
            return;
        }
        while (true) {
            System.out.print("Product ID to add (0 to finish): "); int pid = readInt();
            if (pid == 0) break;
            System.out.print("Quantity: "); int qty = readInt();
            if (!orderService.addItemToOrder(oid, pid, qty)) {
                System.out.println("Failed to add item (stock?).");
            }
        }
        System.out.println("Order placed.");
            utils.InvoiceGenerator.printInvoice(oid);
    }

    private static void viewMyOrdersUI() {
        System.out.print("Your customer ID: "); int cid = readInt();
        orderService.printOrdersByCustomer(cid);
    }

    private static void updateOrderStatusUI() {
        System.out.print("Order ID: "); int oid = readInt();
        System.out.print("New status: "); String st = sc.nextLine();
        boolean ok = orderService.updateOrderStatus(oid, st);
        System.out.println(ok ? "Status updated." : "Failed.");
    }

    private static int readInt() {
        while (!sc.hasNextInt()) { System.out.print("Enter a number: "); sc.next(); }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    private static double readDouble() {
        while (!sc.hasNextDouble()) { System.out.print("Enter a number: "); sc.next(); }
        double val = sc.nextDouble();
        sc.nextLine();
        return val;
    }
}
