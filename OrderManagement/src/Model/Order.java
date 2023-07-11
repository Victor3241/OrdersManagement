package Model;

public class Order {
    private Integer OrderId;
    private Integer ClientId;
    private Integer ProductId;
    private Integer Amount;

    public Order(){}

    public Order(Integer orderId, Integer clientId, Integer productId, Integer amount) {
        OrderId = orderId;
        ClientId = clientId;
        ProductId = productId;
        Amount = amount;
    }

    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public Integer getClientId() {
        return ClientId;
    }

    public void setClientId(Integer clientId) {
        ClientId = clientId;
    }

    public Integer getProductId() {
        return ProductId;
    }

    public void setProductId(Integer productId) {
        ProductId = productId;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }
}
