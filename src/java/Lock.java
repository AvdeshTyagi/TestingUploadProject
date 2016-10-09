/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP Pavilion
 */
public class Lock extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
           
             String y=request.getParameter("c1");
          String z=request.getParameter("c2");
          
             
            
             try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","armaan8886");
            Statement st = con.createStatement();
            ResultSet rs;
            
             PreparedStatement ps=con.prepareStatement("select * from Account1 where username=? and locationpassword=? ");
                  ps.setString(1, y);
                  ps.setString(2, z);
                   //ps.setString(3, br);
                  
                  rs=ps.executeQuery();
                  if(rs.next())
                  {
                      
                      RequestDispatcher rd1=request.getRequestDispatcher("./newwel.html");
                        rd1.include(request,response);   
                    
                  
                        
                  }
                
                 
                  else
                  {
                        out.println("<center><h2>Invalid IP Password Enter Correct IP Password</h2></center>");
                        RequestDispatcher rd2=request.getRequestDispatcher("./EnterIPPassword.html");
                        rd2.include(request,response);
                       
                  }
                  
            }
        


        catch (Exception e)
                {
                System.out.println(e);
                }
            
            
            
            
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            //out.println("<title>Servlet Lock</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet Lock at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
