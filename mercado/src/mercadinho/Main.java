package mercadinho;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static Map<Integer, Produto> inventory = new HashMap<>();
    private static List<Pedido> paidInvoices = new ArrayList<>();
    private static Pedido activeOrder = null;

    public static void main(String[] args) {
        initializeInventory();

        Scanner input = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            displayMenu();
            try {
                option = Integer.parseInt(input.nextLine());

                switch (option) {
                    case 1:
                        createNewOrder(input);
                        break;
                    case 2:
                        processPayment(input);
                        break;
                    case 3:
                        showInvoices();
                        break;
                    case 0:
                        System.out.println("Exiting application!");
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
        input.close();
    }

    private static void initializeInventory() {
        inventory.put(1, new Produto(2.50, Descrição.RICE, 27));
        inventory.put(2, new Produto(2.00, Descrição.BEANS, 30));
        inventory.put(3, new Produto(3.50, Descrição.FLOUR, 18));
        inventory.put(4, new Produto(3.00, Descrição.MILK, 20));
    }

    private static void displayMenu() {
        System.out.println("\n===== SUPERMARKET MENU =====");
        System.out.println("1. New Order");
        System.out.println("2. Process Payment");
        System.out.println("3. Show Paid Invoices");
        System.out.println("0. Exit Application");
        System.out.print("choose an option: ");
    }

    private static void createNewOrder(Scanner input) {
        if (activeOrder != null) {
            System.out.println("there is already an active order!");
            return;
        }

        System.out.print("enter customer name: ");
        String name = input.nextLine();
        System.out.print("enter customer CPF: ");
        String cpf = input.nextLine();
        Cliente customer = new Cliente(name, cpf);
        activeOrder = new Pedido(customer);

        System.out.println("order created for " + name);
        addItemsToOrder(input);
    }

    private static void addItemsToOrder(Scanner input) {
        int productCode = -1;
        while (productCode != 0) {
            System.out.println("\n=== AVAILABLE PRODUCTS ===");
            for (Map.Entry<Integer, Produto> entry : inventory.entrySet()) {
                Produto p = entry.getValue();
                System.out.printf("%d. %s ($%.2f) - %d in stock\n", entry.getKey(), p.getDescription(), p.getPrice(), p.getStockQuantity());
            }
            System.out.print("enter product code to add (or 0 to finish adding items): ");

            try {
                productCode = Integer.parseInt(input.nextLine());
                if (productCode == 0) {
                    break;
                }

                if (inventory.containsKey(productCode)) {
                    Produto selectedProduct = inventory.get(productCode);
                    System.out.print("enter quantity for " + selectedProduct.getDescription() + ": ");
                    int quantity = Integer.parseInt(input.nextLine());

                    if (quantity > 0 && selectedProduct.getStockQuantity() >= quantity) {
                        Item newItem = new Item(selectedProduct, quantity);
                        activeOrder.addItem(newItem);
                        selectedProduct.decreaseStock(quantity);
                        System.out.println(quantity + "x " + selectedProduct.getDescription() + " added to the order!");
                    } else {
                        System.out.println("invalid quantity!");
                    }
                } else {
                    System.out.println("invalid product code!");
                }
            } catch (NumberFormatException e) {
                System.out.println("invalid input!");
            }
        }
        System.out.println("finished adding items. total so far: $" + String.format("%.2f", activeOrder.getTotalValue()));
    }

    private static void processPayment(Scanner input) {
        if (activeOrder == null) {
            System.out.println("no active order to process payment.");
            return;
        }

        System.out.println("\ntotal amount to pay: $" + String.format("%.2f", activeOrder.getTotalValue()));

        TipoPagamento payment = null;
        while (payment == null) {
            System.out.println("choose payment method:\n1. Cash\n2. Check\n3. Credit\n4. Debit\n5. Pix");
            System.out.print("option: ");
            try {
                int paymentCode = Integer.parseInt(input.nextLine());
                switch (paymentCode) {
                    case 1:
                        payment = TipoPagamento.CASH;
                        break;
                    case 2:
                        payment = TipoPagamento.CHECK;
                        break;
                    case 3:
                        payment = TipoPagamento.CREDIT;
                        break;
                    case 4:
                        payment = TipoPagamento.DEBIT;
                        break;
                    case 5:
                        payment = TipoPagamento.PIX;
                        break;
                    default:
                        System.out.println("invalid payment option!");
                }
            } catch (NumberFormatException e) {
                System.out.println("invalid input!");
            }
        }

        activeOrder.setPaymentMethod(payment);
        System.out.println("\n=== FINAL INVOICE ===");
        System.out.println(activeOrder);
        paidInvoices.add(activeOrder);
        activeOrder = null;
        System.out.println("payment successful!");
    }

    private static void showInvoices() {
        if (paidInvoices.isEmpty()) {
            System.out.println("no paid invoices to show.");
            return;
        }

        System.out.println("\n=== ALL PAID INVOICES ===");
        for (Pedido invoice : paidInvoices) {
            System.out.println(invoice);
        }
    }
}