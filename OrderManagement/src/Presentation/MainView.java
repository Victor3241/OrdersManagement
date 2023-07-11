package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import DAO.AbstractDAO;
import DAO.ClientDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Client;
import Model.Order;
import Model.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static BusinessLogic.Controller.*;

import static BusinessLogic.ClientBLL.*;
import static BusinessLogic.ProductBLL.*;
import static BusinessLogic.OrderBLL.*;
public class MainView  extends JFrame{
    private JButton addClient;
    private JButton addProduct;
    private JButton addOrder;
    private JButton showOrder;
    private JButton showClient;
    private JButton showProduct;
    private JTable view;
    private JScrollPane viewScroll;
    private JLabel Title;
    private JLabel Image;
    private ImageIcon ImageIcon;
    private JPanel ImagePanel;
    private JButton showBill;
    private String[][] data;
    private String[] coloumns;
    private int rows = 0;

    public MainView() throws IOException {
        this.setTitle("ORDER MANAGEMENT");
        this.setBounds(0, 0, 620, 1000);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(210, 210, 255));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        BufferedImage img = ImageIO.read(new File("C:\\Users\\user\\Desktop\\PT2023_30422_Seserman_Victor_Assignment_3\\OrderManagement\\src\\Logo.png"));
        this.ImageIcon = new ImageIcon(img);
        this.Image = new JLabel(ImageIcon);
        this.ImagePanel = new JPanel();
        this.ImagePanel.setBackground(new Color(210, 210, 255));
        this.ImagePanel.setBounds(260, 150, 100, 100);
        this.ImagePanel.add(Image);
        this.getContentPane().add(ImagePanel);

        this.Title = new JLabel("ORDERS MANAGEMENT");
        this.Title.setBounds(135, 25, 350,50);
        this.Title.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        this.getContentPane().add(Title);

        this.addClient = new JButton("EDIT CLIENT");
        this.addClient.setBounds(50, 100, 150, 50);
        this.addClient.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        this.addClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientView clientView = new ClientView();
            }
        });
        this.getContentPane().add(addClient);

        this.addProduct = new JButton("EDIT PRODUCT");
        this.addProduct.setBounds(225, 100, 150, 50);
        this.addProduct.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        this.addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductView productView = new ProductView();
            }
        });
        this.getContentPane().add(addProduct);

        this.addOrder = new JButton("EDIT ORDERS");
        this.addOrder.setBounds(400, 100, 150, 50);
        this.addOrder.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        this.addOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderView orderView = new OrderView();
            }
        });
        this.getContentPane().add(addOrder);

        this.showClient = new JButton("SHOW CLIENTS");
        this.showClient.setBounds(50,250, 150, 50);
        this.showClient.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        this.showClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view = ClientBLL.ShowClient();
                view.setBounds(50, 350, 500, 500);
                view.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                viewScroll = new JScrollPane(view);
                viewScroll.setBounds(50, 350, 500, 500);
                getContentPane().add(viewScroll);
            }
        });
        this.getContentPane().add(showClient);

        this.showProduct = new JButton("SHOW PRODUCTS");
        this.showProduct.setBounds(225, 250, 150, 50);
        this.showProduct.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        this.showProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view = ProductBLL.ShowProduct();
                view.setBounds(50, 350, 500, 500);
                view.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                viewScroll = new JScrollPane(view);
                viewScroll.setBounds(50, 350, 500, 500);
                getContentPane().add(viewScroll);
            }
        });
        this.getContentPane().add(showProduct);

        this.showOrder = new JButton("SHOW ORDERS");
        this.showOrder.setBounds(400, 250, 150, 50);
        this.showOrder.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        this.showOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view = OrderBLL.ShowOrder();
                view.setBounds(50, 350, 500, 500);
                view.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                viewScroll = new JScrollPane(view);
                viewScroll.setBounds(50, 350, 500, 500);
                getContentPane().add(viewScroll);
            }
        });
        this.getContentPane().add(showOrder);


        this.view = new JTable();
        this.view.setBounds(50, 350, 500, 500);
        this.view.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        this.viewScroll = new JScrollPane(view);
        this.viewScroll.setBounds(50, 350, 500, 500);
        this.getContentPane().add(viewScroll);

        this.showBill = new JButton("SHOW BILL");
        this.showBill.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.showBill.setBounds(235,875,150,50);
        this.showBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data = new String[1][];
                coloumns = new String[4];
                int order = Integer.parseInt(JOptionPane.showInputDialog("Select the Id of the order:", "1"));
                showBills(data, coloumns, order);
                view = new JTable(data, coloumns);
                view.setBounds(50, 350, 500, 500);
                view.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                viewScroll = new JScrollPane(view);
                viewScroll.setBounds(50, 350, 500, 500);
                getContentPane().add(viewScroll);
            }
        });
        this.getContentPane().add(showBill);
        setVisible(true);
    }

}
