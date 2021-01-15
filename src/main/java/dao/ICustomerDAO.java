package dao;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    public void insertCustomer(Customer customer) throws SQLException;

    public Customer selectCustomer(int id);

    public List<Customer> selectAllCustomer();

    public List<Customer> searchCustomer(String room);

    public boolean deleteCustomer(int id) throws SQLException;

    public boolean updateCustomer(Customer customer) throws SQLException;

    public boolean checkOut(Customer customer) throws SQLException;
}
