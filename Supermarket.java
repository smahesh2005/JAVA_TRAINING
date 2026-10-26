import java.util.Scanner;
public class Supermarket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] categories = {
            "Fruits", "Vegetables", "Juices"
        };

        String[][] items = {
            {"Apple", "Banana", "Orange"},
            {"Potato", "Tomato", "Carrot"},
            {"Grapes Juice", "Lemon Juice", "Pomegranate Juice"}
        };

        int[][] prices = {
            {100, 40, 60},
            {30, 40, 50},
            {30, 20, 50}
        };

        int total = 0;

        while (true) {

            System.out.println("\n===== SUPERMARKET =====");
            System.out.println("1. View Menu");
            System.out.println("2. Add Items to Cart");
            System.out.println("3. View Total");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int option = sc.nextInt();

            switch (option) {

                case 1:

                    System.out.println("\n===== SHOP MENU =====");

                    for (int i = 0; i < categories.length; i++) {

                        System.out.println("\n" + categories[i] + ":");

                        for (int j = 0; j < items[i].length; j++) {
                            System.out.println(
                                (j + 1) + ". " + items[i][j]
                                + " - Rs." + prices[i][j]
                            );
                        }
                    }
                    break;

                case 2:

                    System.out.println("\n===== CATEGORIES =====");

                    for (int i = 0; i < categories.length; i++) {
                        System.out.println(
                            (i + 1) + ". " + categories[i]
                        );
                    }

                    System.out.print("Select a category: ");
                    int category = sc.nextInt();

                    if (category < 1 || category > categories.length) {
                        System.out.println("Invalid category!");
                        break;
                    }

                    int index = category - 1;

                    System.out.println("\n--- " + categories[index] + " ---");

                    for (int i = 0; i < items[index].length; i++) {
                        System.out.println(
                            (i + 1) + ". " + items[index][i]
                            + " - Rs." + prices[index][i]
                        );
                    }
                    System.out.print("Select an item: ");
                    int choice = sc.nextInt();
                    if (choice < 1 || choice > items[index].length) {
                        System.out.println("Invalid item!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    int itemIndex = choice - 1;
                    int cost = prices[index][itemIndex] * quantity;
                    total += cost;
                    System.out.println("\nItem added successfully!");
                    System.out.println("Item: " + items[index][itemIndex]);
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Item Total: Rs." + cost);
                    
                    break;

                case 3:
                    System.out.println("Total Amount: Rs." + total);
                    break;

                case 4:
                    System.out.println("\n==================================");
                    System.out.println("Thank you for shopping with us!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option! Please select 1 to 4.");
            }
        }
    }
}
