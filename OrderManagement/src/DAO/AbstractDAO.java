package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;
import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AbstractDAO<T>{
    private final Class<T> type;

    /**
     * @return String
     */
    private String createSelectQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append("* ");
        stringBuilder.append("FROM ");
        stringBuilder.append("\""+(type.getSimpleName().charAt(0) + "").toUpperCase()+type.getSimpleName().substring(1)+"\"");
        stringBuilder.append(" WHERE " + "\"" +field + "\"" + " =?");
        return stringBuilder.toString();
    }
    /**
     * @return String
     */

    private String createSelectAllQuery(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append("* ");
        stringBuilder.append("FROM ");
        stringBuilder.append("\""+(type.getSimpleName().charAt(0) + "").toUpperCase()+type.getSimpleName().substring(1)+"\"");
        return stringBuilder.toString();
    }
    /**
     * @return String
     */

    private String createInsertQuery(T t){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append("\"" + (type.getSimpleName().charAt(0) + "").toUpperCase()+type.getSimpleName().substring(1) + "\"");
        stringBuilder.append(" (\"");
        int i = 0;
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            stringBuilder.append(field.getName());
            stringBuilder.append("\",\"");
            i++;
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(") VALUES (");
        while(i > 0){
            stringBuilder.append("?,");
            i--;
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
    /**
     * @return String
     */

    private String createUpdateQuery(T t, int id) throws IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE schooldb.");
        stringBuilder.append(type.getSimpleName().toLowerCase());
        stringBuilder.append(" SET ");
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(t) instanceof Integer) {
                stringBuilder.append(field.getName()).append(" = ").append(field.get(t));
                stringBuilder.append(",");
            } else if (field.get(t) instanceof String) {
                stringBuilder.append(field.getName()).append(" = '").append(field.get(t)).append("'");
                stringBuilder.append(",");
            }
        }
        boolean idFound = false;
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(" WHERE ");
        for (Field field : t.getClass().getDeclaredFields()) {
            if(idFound == false) {
                field.setAccessible(true);
                stringBuilder.append(field.getName() + " = ?");
                idFound = true;
            }
        }
        return stringBuilder.toString();
    }
    /**
     * @return String
     */

    private String createDeleteQuery(T t){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE ");
        stringBuilder.append("FROM ");
        stringBuilder.append("\""+(type.getSimpleName().charAt(0) + "").toUpperCase()+type.getSimpleName().substring(1)+"\"");
        boolean idFound = false;
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(" WHERE ");
        for (Field field : t.getClass().getDeclaredFields()) {
            if(idFound == false) {
                stringBuilder.append(" WHERE " + "\"" + field.getName() + "\"" + " =?");
                idFound = true;
            }
        }
        return stringBuilder.toString();
    }

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @return T
     */
    public T findClientById(int id, T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean idFound = false;
        String idField = new String();
        for (Field field : t.getClass().getDeclaredFields()) {
            if(idFound == false) {
                idField = field.getName();
                idFound = true;
            }
        }
        String query = createSelectQuery(idField);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @return T
     */
    public List<T> findClientsAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @return T
     */
    public T findProductById(int id, T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean idFound = false;
        String idField = new String();
        for (Field field : t.getClass().getDeclaredFields()) {
            if(idFound == false) {
                idField = field.getName();
                idFound = true;
            }
        }
        String query = createSelectQuery(idField);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @return T
     */
    public List<T> findProductAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            //statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @return T
     */
    public T findOrderById(int id, T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean idFound = false;
        String idField = new String();
        for (Field field : t.getClass().getDeclaredFields()) {
            if(idFound == false) {
                idField = field.getName();
                idFound = true;
            }
        }
        String query = createSelectQuery(idField);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @return T
     */
    public List<T> findOrderAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            //statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @return T
     */
    private List<T> createObjects(ResultSet resultSet){
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;
        for (Constructor item : constructors) {
            constructor = item;
            if (constructor.getGenericParameterTypes().length == 0) {
                break;
            }
        }
        try {
            while (resultSet.next()) {
                constructor.setAccessible(true);
                T instance = (T) constructor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 IntrospectionException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * @return void
     */
    public void insertClient(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int parameterIndex = 1;
            statement.setObject(parameterIndex, "DEFAULT");
            for (Field field : t.getClass().getDeclaredFields()) {
                if(parameterIndex != 1) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(t);
                    statement.setObject(parameterIndex, fieldValue);
                }
                parameterIndex++;
            }
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @return void
     */
    public void insertProduct(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int parameterIndex = 1;
            statement.setObject(parameterIndex, "DEFAULT");
            for (Field field : t.getClass().getDeclaredFields()) {
                if(parameterIndex != 1) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(t);
                    statement.setObject(parameterIndex, fieldValue);
                }
                parameterIndex++;
            }
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * @return void
     */
    public void insertOrder(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int parameterIndex = 1;
            statement.setObject(parameterIndex, "DEFAULT");
            for (Field field : t.getClass().getDeclaredFields()) {
                if(parameterIndex != 1) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(t);
                    statement.setObject(parameterIndex, fieldValue);
                }
                parameterIndex++;
            }
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * @return void
     */
    public void updateClient(T t, int id){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            String query = createUpdateQuery(t, id);
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
    }
    /**
     * @return void
     */
    public void updateProduct(T t, int id){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            String query = createUpdateQuery(t, id);
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
    }
    /**
     * @return void
     */
    public void updateOrder(T t, int id){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            String query = createUpdateQuery(t, id);
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
    }
    /**
     * @return void
     */

    public void deleteClientById(T t, int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(t);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * @return void
     */
    public void deleteProductById(T t, int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(t);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * @return void
     */
    public void deleteOrderById(T t, int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(t);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * @return JTable
     */
    public JTable jTable(ArrayList<T> data){
        Field[] fields = data.get(0).getClass().getDeclaredFields();
        ArrayList<String> columnsName = new ArrayList<>();
        for (Field field : fields){
            field.setAccessible(true);
            columnsName.add(field.getName());
        }
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columnsName.toArray());
        for (T t : data){
            ArrayList<Object> objects = new ArrayList<>();
            Field[] objFields = t.getClass().getDeclaredFields();
            for (Field field : objFields){
                field.setAccessible(true);
                try {
                    objects.add(field.get(t));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            defaultTableModel.addRow(objects.toArray());
        }
        return new JTable(defaultTableModel);
    }
}
