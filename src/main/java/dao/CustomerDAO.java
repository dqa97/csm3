package dao;

import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Quocanh123";

    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customer" + " (room , name, cmnd, checkin, checkout) VALUES " + " (?,?,?,?,?); ";
    private static final String SELECT_CUSTOMER_BY_ID = " select * from customer where id = ?; ";
    private static final String SELECT_ALL_CUSTOMER = "select * from customer";
    private static final String DELETE_CUSTOMER_SQL = "delete from customer where id = ?;";
    private static final String UPDATE_CUSTOMER_SQL = "update customer set room = ?, name = ?, cmnd = ?, checkin = ?, checkout = ? where id = ?;";
    private static final String CHECK_OUT_BY_ID = " update customer set checkout = ? where id = ?; ";
    private static final String SEARCH_CUSTOMER_BY_ROOM = "select * from customer where id = ?;";

    public CustomerDAO() {
    }

    protected Connection getConnection() {
        java.sql.Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertCustomer(Customer customer) throws SQLException {
        System.out.println(INSERT_CUSTOMER_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getRoom());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getCmnd());
            preparedStatement.setString(4, customer.getCheckin());
            preparedStatement.setString(5, customer.getCheckout());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Customer selectCustomer(int id) {
        Customer customer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String room = rs.getString("room");
                String name = rs.getString("name");
                String cmnd = rs.getString("cmnd");
                String checkin = rs.getString("checkin");
                String checkout = rs.getString("checkout");
                customer = new Customer(id, room, name, cmnd, checkin, checkout);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    public List<Customer> selectAllCustomer() {
        List<Customer> customer = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String room = rs.getString("room");
                String name = rs.getString("name");
                String cmnd = rs.getString("cmnd");
                String checkin = rs.getString("checkin");
                String checkout = rs.getString("checkout");
                customer.add(new Customer(id, room, name, cmnd, checkin, checkout));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    public boolean deleteCustomer(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<Customer> searchCustomer(String room) {
        List<Customer> customer = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_CUSTOMER_BY_ROOM)) {
            preparedStatement.setString(1, room);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String cmnd = rs.getString("cmnd");
                String checkin = rs.getString("checkin");
                String checkout = rs.getString("checkout");
                Customer customer1 = new Customer(room, name, cmnd, checkin, checkout);
                customer.add(customer1);

            }
        } catch (SQLException e){
            printSQLException(e);
        }
        return customer;
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getRoom());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getCmnd());
            preparedStatement.setString(4, customer.getCheckin());
            preparedStatement.setString(5, customer.getCheckout());
            preparedStatement.setInt(6, customer.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;

        }
        return rowUpdated;
    }

    public boolean checkOut(Customer customer) throws SQLException {
        boolean rowCheckOut;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_OUT_BY_ID)) {
            preparedStatement.setString(1, customer.getCheckout());
            preparedStatement.setInt(2, customer.getId());

            rowCheckOut = preparedStatement.executeUpdate() > 0;
        }
        return rowCheckOut;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
