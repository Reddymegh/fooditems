package food;

import java.util.*;

class FoodItem {
    String name;
    double price;

    FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class User {
    String username;
    String password;
    List<Order> orderHistory = new ArrayList<>();

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Order {
    List<FoodItem> items = new ArrayList<>();
    double total = 0;
    Date timestamp;

    Order() {
        this.timestamp = new Date();
    }

    void addItem(FoodItem item) {
        items.add(item);
        total += item.price;
    }

    void displayOrder() {
        System.out.println("\n🧾 Order Summary (" + timestamp + "):");
        for (FoodItem item : items) {
            System.out.println("- " + item.name + " ₹" + item.price);
        }
        System.out.println("Total: ₹" + total);
    }
}

public class FoodDeliveryApp {
    static Scanner sc = new Scanner(System.in);
    static List<FoodItem> menu = Arrays.asList(
        new FoodItem("Pizza", 250),
        new FoodItem("Burger", 150),
        new FoodItem("Pasta", 200),
        new FoodItem("Fries", 100)
    );
    static Map<String, User> users = new HashMap<>();
    static User currentUser;

    public static void main(String[] args) {
        System.out.println("🍽️ Welcome to Java Food Delivery!");

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) register();
            else if (choice == 2) {
                if (login()) break;
            } else return;
        }

        while (true) {
            System.out.println("\n📋 Menu:");
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i).name + " ₹" + menu.get(i).price);
            }
            System.out.println("0. Checkout\n9. View Order History\n8. Logout");

            System.out.print("Choose an item: ");
            int choice = sc.nextInt();

            if (choice == 0) {
                checkout();
            } else if (choice == 9) {
                viewHistory();
            } else if (choice == 8) {
                System.out.println("👋 Logged out.");
                break;
            } else if (choice > 0 && choice <= menu.size()) {
                currentOrder.addItem(menu.get(choice - 1));
                System.out.println("✅ Added " + menu.get(choice - 1).name);
            } else {
                System.out.println("❌ Invalid choice.");
            }
        }
    }

    static Order currentOrder = new Order();

    static void register() {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        users.put(user, new User(user, pass));
        System.out.println("✅ Registered successfully!");
    }

    static boolean login() {
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (users.containsKey(user) && users.get(user).password.equals(pass)) {
            currentUser = users.get(user);
            System.out.println("🔓 Login successful!");
            return true;
        } else {
            System.out.println("❌ Invalid credentials.");
            return false;
        }
    }

    static void checkout() {
        currentOrder.displayOrder();
        currentUser.orderHistory.add(currentOrder);
        currentOrder = new Order();
        System.out.println("🙏 Thank you for ordering!");
    }

    static void viewHistory() {
        System.out.println("\n📜 Order History:");
        if (currentUser.orderHistory.isEmpty()) {
            System.out.println("No orders yet.");
        } else {
            for (Order o : currentUser.orderHistory) {
                o.displayOrder();
                System.out.println("-------------------");
            }
        }
    }
}
