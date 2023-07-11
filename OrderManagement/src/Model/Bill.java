package Model;

public record Bill(int OrderId, String ClientName, String ProductName, int TotalPrice) {
    public Bill{
    }

    @Override
    public int OrderId() {
        return OrderId;
    }

    @Override
    public String ClientName() {
        return ClientName;
    }

    @Override
    public String ProductName() {
        return ProductName;
    }

    @Override
    public int TotalPrice() {
        return TotalPrice;
    }
}
