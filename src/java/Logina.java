/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.RequestDispatcher;

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
public class Logina extends HttpServlet {

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
           
            String x=request.getParameter("n1");
           String y=request.getParameter("n2");
           InetAddress ipAddr = InetAddress.getLocalHost();
            
            String br;
            br = ipAddr.getHostAddress();
            
            
            try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","armaan8886");
            Statement st = con.createStatement();
            ResultSet rs;
            
             PreparedStatement ps=con.prepareStatement("select * from Account1 where username=? and password=? and ipaddress=?");
                  ps.setString(1, x);
                  ps.setString(2, y);
                   ps.setString(3, br);
                  
                  rs=ps.executeQuery();
                  if(rs.next())
                  {
                       PreparedStatement ps1=con.prepareStatement("select acno from Account1 where username=? and password=? ");
                 ps1.setString(1, x);
                  ps1.setString(2, y);
                  
                  rs=ps1.executeQuery();
                  
            while(rs.next()){
               Long acnu1 = rs.getLong(1);
            
                    
                      
                      HttpSession session=request.getSession();  
        session.setAttribute("acno1",acnu1);  
        session.setAttribute("uname1",x); 
        session.setAttribute("pass1",y); 
        
                      
                      
        
                      
                      
                      
                      //System.out.println("welcome ");
                        //out.println("<h3>welcome " +" " + x +"</h3>");
                        RequestDispatcher rd1=request.getRequestDispatcher("./newwel.html");
                        rd1.include(request,response);
                        
                      con.close();
            }
                  }
                  
                  
                  
                          
                   ps=con.prepareStatement("select * from Account1 where username=? and password=?");
                  ps.setString(1, x);
                  ps.setString(2, y);
                   //ps.setString(3, br);
                  
                  rs=ps.executeQuery();
                  if(rs.next())
                  {
                       PreparedStatement ps2=con.prepareStatement("select acno from Account1 where username=? and password=? ");
                 ps2.setString(1, x);
                  ps2.setString(2, y);
                  
                  rs=ps2.executeQuery();
                  
            while(rs.next()){
               Long acnu1 = rs.getLong(1);
                      HttpSession session=request.getSession();  
                      session.setAttribute("acno1",acnu1);  
        session.setAttribute("uname1",x); 
        session.setAttribute("pass1",y); 
                      
                       // out.println("<center><h2> Enter Your location password </h2></center>");
                        RequestDispatcher rd2=request.getRequestDispatcher("./Lock.html");
                        rd2.include(request,response);
                       
            }   
                  }
                  
                  
                  else
                  {
                        out.println("<center><h2>invalid username/password Enter Correct username/password</h2></center>");
                        RequestDispatcher rd2=request.getRequestDispatcher("./newloginpage.html");
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
           // out.println("<title>Servlet Logina</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet Logina at " + request.getContextPath() + "</h1>");
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
