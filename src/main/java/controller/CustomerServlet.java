package controller;

import dao.CustomerDAO;
import model.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init(){
        customerDAO = new CustomerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try {
            switch (action){
                case "create":
                    insertCustomer(request,response);
                    break;
                case "edit":
                    updateCustomer(request,response);
                    break;
            }
        } catch (SQLException ex){
            throw new ServletException(ex);
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try {
            switch (action){
                case "create":
                    showNewForm(request,response);
                    break;
                case "edit":
                    showEditForm(request,response);
                    break;
                case "delete":
                    deleteCustomer(request,response);
                    break;
                default:
                    listCustomer(request,response);
            }
        } catch (SQLException ex){
            throw new ServletException(ex);
        }
    }
    private void listCustomer(HttpServletRequest request,HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        List<Customer> listCustomer = customerDAO.selectAllCustomer();
        request.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");
        dispatcher.forward(request,response);
    }

    private void showEditForm(HttpServletRequest request,HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.selectCustomer(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/edit.jsp");
        request.setAttribute("customer", existingCustomer);
        dispatcher.forward(request,response);
    }

    private void insertCustomer(HttpServletRequest request,HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        String room = request.getParameter("room");
        String name = request.getParameter("name");
        String cmnd = request.getParameter("cmnd");
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");
        Customer newCustomer = new Customer(room,name,cmnd,checkin,checkout);
        customerDAO.insertCustomer(newCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");
        dispatcher.forward(request,response);
    }

    private void updateCustomer(HttpServletRequest request,HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        String room = request.getParameter("room");
        String name = request.getParameter("name");
        String cmnd = request.getParameter("cmnd");
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");
        Customer book = new Customer(id, room, name,cmnd,checkin,checkout);
        customerDAO.updateCustomer(book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void deleteCustomer(HttpServletRequest request,HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        customerDAO.deleteCustomer(id);
        List<Customer> listCustomer = customerDAO.selectAllCustomer();
        request.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");
        dispatcher.forward(request,response);
    }
}
