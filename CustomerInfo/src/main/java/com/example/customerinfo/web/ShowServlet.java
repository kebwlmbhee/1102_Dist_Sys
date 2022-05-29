package com.example.customerinfo.web;

import com.example.customerinfo.entity.Customer;
import com.example.customerinfo.utils.StockUtils;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "showServlet", urlPatterns = {"/showServlet"})
public class ShowServlet extends HttpServlet {
    private EntityManager em;

    @Override
    public void init() throws ServletException {
        super.init();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CustomerPU");
        em = emf.createEntityManager();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.log("id="+id);
        try {            
            Customer cust = em.find(Customer.class, id);
            StockUtils util = new StockUtils();
            String price = util.getStockPrice(cust.getCompany());
            request.setAttribute("customer", cust);
            request.setAttribute("price", price);
            RequestDispatcher rd = request.getRequestDispatcher("showView.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            this.log("ex="+ex);
            request.setAttribute("exception", ex);
            RequestDispatcher rd = request.getRequestDispatcher("exceptionView.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
