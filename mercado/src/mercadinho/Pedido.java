package mercadinho;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente customer;
    private TipoPagamento paymentMethod;
    private List<Item> items;

    public Pedido(Cliente customer) {
        this.items = new ArrayList<>();
        this.customer = customer;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setPaymentMethod(TipoPagamento paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalValue() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getTotalValue();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder invoice = new StringBuilder();
        invoice.append("Customer: ").append(customer.getName()).append(" | CPF: ").append(customer.getCpf()).append("\n");
        invoice.append("===================================================================\n");
        invoice.append(String.format("%-10s | %-20s | %-10s | %-10s\n", "Quantity", "Product", "Unit Price", "Total"));
        invoice.append("-------------------------------------------------------------------\n");

        for (Item i : items) {
            invoice.append(String.format("%-10d | %-20s | %-10.2f | %-10.2f\n", i.getQuantity(), i.getItemDescription(), i.getItemPrice(), i.getTotalValue()));
        }

        invoice.append("-------------------------------------------------------------------\n");
        invoice.append("Payment Method: ").append(paymentMethod).append("\n");
        invoice.append(String.format("%54s $%-10.2f\n", "TOTAL:", this.getTotalValue()));
        invoice.append("===================================================================\n");

        return invoice.toString();
    }
}