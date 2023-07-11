package Presentation;

import DAO.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static BusinessLogic.ClientBLL.*;

public class ClientView extends JFrame {
    private JLabel id;
    private JLabel name;
    private JLabel address;
    private JSpinner idField;
    private JTextField nameField;
    private JTextField addressField;
    private JButton delete;
    private JButton update;
    private JButton insert;

    private ClientDAO clientDAO = new ClientDAO();

    public ClientView(){
        this.setTitle("CLIENT MANAGEMENT");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(210, 210, 255));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(1200, 300, 550, 600);

        id = new JLabel("ID");
        id.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        id.setBounds(50, 50, 100,50);
        getContentPane().add(id);

        name = new JLabel("NAME");
        name.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        name.setBounds(50, 150, 100,50);
        getContentPane().add(name);

        address = new JLabel("ADDRESS");
        address.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        address.setBounds(50, 250, 100,50);
        getContentPane().add(address);

        idField = new JSpinner();
        idField.setBounds(200, 50, 100, 50);
        getContentPane().add(idField);

        nameField = new JTextField();
        nameField.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        nameField.setBounds(200, 150, 200,50);
        getContentPane().add(nameField);

        addressField = new JTextField();
        addressField.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        addressField.setBounds(200, 250, 200,50);
        getContentPane().add(addressField);

        insert = new JButton("INSERT");
        insert.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        insert.setBounds(50, 350, 150, 50);
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client((int)idField.getValue(), nameField.getText(), addressField.getText());
                InsertClient(client);
            }
        });
        getContentPane().add(insert);

        update = new JButton("UPDATE");
        update.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        update.setBounds(300, 350, 150, 50);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client((int)idField.getValue(), nameField.getText(), addressField.getText());
                UpdateClient(client, client.getClientId());
            }
        });
        getContentPane().add(update);

        delete = new JButton("DELETE");
        delete.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        delete.setBounds(175,450,150,50);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client((int)idField.getValue(), nameField.getText(), addressField.getText());
                DeleteClient(client, client.getClientId());
            }
        });
        getContentPane().add(delete);
        this.setVisible(true);
    }

}
