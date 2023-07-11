package Presentation;

import DAO.ClientDAO;
import DAO.ProductDAO;
import Model.Client;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static BusinessLogic.ProductBLL.*;

public class ProductView extends JFrame {
    private JLabel id;
    private JLabel name;
    private JLabel price;
    private JLabel stock;
    private JSpinner idField;
    private JTextField nameField;
    private JSpinner priceField;
    private JSpinner stockField;
    private JButton delete;
    private JButton update;
    private JButton insert;

    private ProductDAO productDAO = new ProductDAO();

    public ProductView() {
        this.setTitle("PRODUCT MANAGEMENT");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(210, 210, 255));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(1200, 300, 550, 700);

        id = new JLabel("ID");
        id.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        id.setBounds(50, 50, 100, 50);
        getContentPane().add(id);

        name = new JLabel("NAME");
        name.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        name.setBounds(50, 150, 100, 50);
        getContentPane().add(name);

        price = new JLabel("PRICE");
        price.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        price.setBounds(50, 250, 100, 50);
        getContentPane().add(price);

        stock = new JLabel("STOCK");
        stock.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        stock.setBounds(50, 350, 100, 50);
        getContentPane().add(stock);

        idField = new JSpinner();
        idField.setBounds(200, 50, 100, 50);
        getContentPane().add(idField);

        nameField = new JTextField();
        nameField.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        nameField.setBounds(200, 150, 200, 50);
        getContentPane().add(nameField);

        priceField = new JSpinner();
        priceField.setBounds(200, 250, 200, 50);
        getContentPane().add(priceField);

        stockField = new JSpinner();
        stockField.setBounds(200, 350, 200, 50);
        getContentPane().add(stockField);

        insert = new JButton("INSERT");
        insert.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        insert.setBounds(50, 450, 150, 50);
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product((int) idField.getValue(), nameField.getText(), (int) priceField.getValue(), (int) stockField.getValue());
                InsertProduct(product);
            }
        });
        getContentPane().add(insert);

        update = new JButton("UPDATE");
        update.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        update.setBounds(300, 450, 150, 50);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product((int) idField.getValue(), nameField.getText(), (int) priceField.getValue(), (int) stockField.getValue());
                UpdateProduct(product, product.getProductId());
            }
        });
        getContentPane().add(update);

        delete = new JButton("DELETE");
        delete.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        delete.setBounds(175, 550, 150, 50);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product((int) idField.getValue(), nameField.getText(), (int) priceField.getValue(), (int) stockField.getValue());
                DeleteProduct(product, product.getProductId());
            }
        });
        getContentPane().add(delete);
        this.setVisible(true);
    }
}
