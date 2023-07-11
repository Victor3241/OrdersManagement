package Model;

public class Product {
    private Integer ProductId;
    private String Name;
    private Integer Price;
    private Integer Stock;

    public Product(){}

    public Product(Integer productId, String name, Integer price, Integer stock) {
        ProductId = productId;
        Name = name;
        Price = price;
        Stock = stock;
    }

    public Integer getProductId() {
        return ProductId;
    }

    public void setProductId(Integer id) {
        this.ProductId = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        this.Price = price;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        this.Stock = stock;
    }
}
