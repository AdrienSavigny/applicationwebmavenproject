/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pac;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author asavigny
 */
@WebServlet(name = "Showclientforstate", urlPatterns = {"/Showclientforstate"})
public class Showclientforstate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Showclientforstate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Adress</th>");
            out.println("</tr>");
            
            try {
                String val = request.getParameter("state");
                if (val == null) {
                    throw new Exception("Le paramètre State n'a pas été transmis");
                }

                DAO dao = new DAO(DataSourceFactory.getDataSource());

                List<CustomerEntity> customer = dao.customersInState(val);

                if (customer == null) {
                    throw new Exception("Etat non connu");
                }
                for (int i=0;i<customer.size();i++) {
                    out.println("<tr>");
                    out.println("<td>");
                    out.println(customer.get(i).getName());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(customer.get(i).getAddressLine1());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(customer.get(i).getCustomerId());   
                    out.println("</td>");
                    out.println("</tr>");
              }

            } catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
             out.println("</table>");
            out.printf("<hr><a href='%s'>Retour au menu</a>", request.getContextPath());
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
    }
// commentaires 
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
