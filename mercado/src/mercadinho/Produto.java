package mercadinho;

public class Produto {
    private double price;
    private Descrição description;
    private int stockQuantity;

    public Produto(double price, Descrição description, int stockQuantity) {
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void decreaseStock(int quantity) {
        if (quantity > 0 && stockQuantity >= quantity) {
            stockQuantity -= quantity;
        }
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public Descrição getDescription() {
        return description;
    }
}