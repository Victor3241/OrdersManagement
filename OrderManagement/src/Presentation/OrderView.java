package Presentation;

import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Order;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static BusinessLogic.Controller.updateBill;
import static BusinessLogic.OrderBLL.*;
import static BusinessLogic.ProductBLL.*;

public class OrderView extends JFrame {
    private JLabel orderId;
    private JLabel clientId;
    private JLabel productId;
    private JLabel amount;
    private JSpinner orderIdField;
    private JSpinner clientIdField;
    private JSpinner productIdField;
    private JSpinner amountField;
    private JButton delete;
    private JButton update;
    private JButton insert;

    private OrderDAO orderDAO = new OrderDAO();

    public OrderView() {
        this.setTitle("ORDER MANAGEMENT");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(210, 210, 255));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(1200, 300, 550, 700);

        orderId = new JLabel("ORDERID");
        orderId.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        orderId.setBounds(50, 50, 100, 50);
        getContentPane().add(orderId);

        clientId = new JLabel("CLIENTID");
        clientId.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        clientId.setBounds(50, 150, 100, 50);
        getContentPane().add(clientId);

        productId = new JLabel("PRODUCTID");
        productId.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        productId.setBounds(50, 250, 150, 50);
        getContentPane().add(productId);

        amount = new JLabel("AMOUNT");
        amount.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        amount.setBounds(50, 350, 100, 50);
        getContentPane().add(amount);

        orderIdField = new JSpinner();
        orderIdField.setBounds(200, 50, 100, 50);
        getContentPane().add(orderIdField);

        clientIdField = new JSpinner();
        clientIdField.setBounds(200, 150, 200, 50);
        getContentPane().add(clientIdField);

        productIdField = new JSpinner();
        productIdField.setBounds(200, 250, 200, 50);
        getContentPane().add(productIdField);

        amountField = new JSpinner();
        amountField.setBounds(200, 350, 200, 50);
        getContentPane().add(amountField);

        insert = new JButton("INSERT");
        insert.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        insert.setBounds(50, 450, 150, 50);
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Order order = new Order((int) orderIdField.getValue(), (int) clientIdField.getValue(), (int) productIdField.getValue(), (int) amountField.getValue());
                    ProductDAO productDAO = new ProductDAO();
                    Product product = productDAO.findProductById(order.getProductId(), new Product());
                    if (product.getStock() < order.getAmount()) {
                        JOptionPane.showMessageDialog(new JDialog(), "NOT ENOUGH STOCK", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            int stock = product.getStock();
                            int amount = order.getAmount();
                            int newStock = stock - amount;
                            product.setStock(newStock);
                            UpdateProduct(product, product.getProductId());
                        } finally {
                            InsertOrder(order);
                        }

                    }
                }finally {
                    updateBill();
                }
            }
        });
        getContentPane().add(insert);

        update = new JButton("UPDATE");
        update.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        update.setBounds(300, 450, 150, 50);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Order order = new Order((int) orderIdField.getValue(), (int) clientIdField.getValue(), (int) productIdField.getValue(), (int) amountField.getValue());
                    UpdateOrder(order, order.getOrderId());
                }finally {
                    updateBill();
                }
            }
        });
        getContentPane().add(update);

        delete = new JButton("DELETE");
        delete.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        delete.setBounds(175, 550, 150, 50);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Order order = new Order((int) orderIdField.getValue(), (int) clientIdField.getValue(), (int) productIdField.getValue(), (int) amountField.getValue());
                    DeleteOrder(order, order.getOrderId());
                } finally {
                    updateBill();
                }
            }
        });
        getContentPane().add(delete);
        this.setVisible(true);
    }
}