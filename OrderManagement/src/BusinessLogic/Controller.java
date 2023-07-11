package BusinessLogic;

import DAO.ClientDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Bill;
import Model.Client;
import Model.Order;
import Model.Product;
import Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    public static void showBills(String[][] data, String[] coloumns, int orderId){
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.findOrderById(orderId, new Order());
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.findClientById(order.getClientId(), new Client());
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findProductById(order.getProductId(), new Product());
        coloumns[0] = "ORDERID";
        coloumns[1]= "CLIENT NAME";
        coloumns[2] = "PRODUCT NAME";
        coloumns[3] = "TOTAL PRICE";
        data[0] = new String[4];
        Integer totalprice = product.getPrice() * order.getAmount();
        data[0][0] = order.getOrderId().toString();
        data[0][1] = client.getName();
        data[0][2] = product.getName();
        data[0][3] = totalprice.toString();
        Bill bill = new Bill(orderId, client.getName(), product.getName(), totalprice);
        System.out.println(bill.toString());
    }

    private static String createInsertBill(Bill bill){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO \"Log\" (\"OrderId\", \"ClientName\", \"ProductName\", \"TotalPrice\") ");
        stringBuilder.append("VALUES ('" + bill.OrderId() + "', '" + bill.ClientName() + "', '" + bill.ProductName() + "', '" + bill.TotalPrice() + "')");
        return stringBuilder.toString();
    }
    private static String createDeleteBill(Bill bill){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM \"Log\" WHERE \"OrderId\" = '" + bill.OrderId() + "'");
        return stringBuilder.toString();
    }
    private static Bill createBill(Order order){
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.findClientById(order.getClientId(), new Client());
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findProductById(order.getProductId(), new Product());
        return new Bill(order.getOrderId(), client.getName(), product.getName(), product.getPrice() * order.getAmount());
    }
    public static void updateBill(){
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.findOrderAll();
        for(Order order : orders){
            Bill bill = createBill(order);
            String query = createDeleteBill(bill);
            Connection connection = null;
            PreparedStatement statement = null;
            try{
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                System.out.println(statement.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
        }
        for(Order order : orders){
            Bill bill = createBill(order);
            String query = createInsertBill(bill);
            Connection connection = null;
            PreparedStatement statement = null;
            try{
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                System.out.println(statement.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
        }
    }
}
