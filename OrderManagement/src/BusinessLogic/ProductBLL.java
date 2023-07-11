package BusinessLogic;

import DAO.ClientDAO;
import DAO.ProductDAO;
import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    public static Product FindByIdProduct(int id){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findProductById(id, new Product());
    }
    public static List<Product> FindAllProducts(){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findProductAll();
    }
    public static void InsertProduct(Product product){
        ProductDAO productDAO = new ProductDAO();
        productDAO.insertProduct(product);
    }
    public static void UpdateProduct(Product product, int id){
        ProductDAO productDAO = new ProductDAO();
        productDAO.updateProduct(product, id);
    }
    public static void DeleteProduct(Product product, int id){
        ProductDAO productDAO = new ProductDAO();
        productDAO.deleteProductById(product, id);
    }
    public static JTable ShowProduct(){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.jTable((ArrayList<Product>) productDAO.findProductAll());
    }
}
