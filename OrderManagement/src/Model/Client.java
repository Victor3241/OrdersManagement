package Model;

public class Client {
    private Integer ClientId;
    private String Name;
    private String Address;

    public Client(){
    }
    public Client(Integer id, String name, String address){
        this.ClientId = id;
        this.Name = name;
        this.Address = address;
    }

    public Integer getClientId() {
        return ClientId;
    }

    public void setClientId(Integer id) {
        this.ClientId = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }
}
