package BusinessLogic;

import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Order;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OrderBLL {
    public static Order FindByIdOrder(int id){
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findOrderById(id, new Order());
    }
    public static List<Order> FindAllOrders(){
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findOrderAll();
    }
    public static void InsertOrder(Order order){
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.insertOrder(order);
    }
    public static void UpdateOrder(Order order, int id){
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.updateOrder(order, id);
    }
    public static void DeleteOrder(Order order, int id){
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.deleteOrderById(order, id);
    }
    public static JTable ShowOrder(){
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.jTable((ArrayList<Order>) orderDAO.findOrderAll());
    }
}
