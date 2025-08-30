package mercadinho;

public class Item {
    private int quantity;
    private Produto product;

    public Item(Produto product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return quantity * product.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemPrice() {
        return product.getPrice();
    }

    public Descrição getItemDescription() {
        return product.getDescription();
    }

    public Produto getProduct() {
        return product;
    }
}