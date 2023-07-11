package BusinessLogic;

import DAO.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ClientBLL {
    public static Client FindByIdClient(int id){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findClientById(id, new Client());
    }
    public static List<Client> FindAllClients(){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findClientsAll();
    }
    public static void InsertClient(Client client){
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insertClient(client);
    }
    public static void UpdateClient(Client client, int id){
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.updateClient(client, id);
    }
    public static void DeleteClient(Client client, int id){
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.deleteClientById(client, id);
    }
    public static JTable ShowClient(){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.jTable((ArrayList<Client>) clientDAO.findClientsAll());
    }
}
