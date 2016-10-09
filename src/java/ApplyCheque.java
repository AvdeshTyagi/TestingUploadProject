/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Avdesh Tyagi
 */
public class ApplyCheque extends HttpServlet {

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
            
            String email1=null;
            Long y =Long.parseLong(request.getParameter("t1"));
             //Long recac =Long.parseLong(request.getParameter("t2"));
            String name =request.getParameter("t2");
            //Long x=Long.parseLong(request.getParameter("t5"));
             
          String z=request.getParameter("t6");
         
           /* String name1="Bob";
            String name2="bob";
            String name3="Bank of Baroda";
            String name4="bank of baroda";
            String name5="Bank of baroda";*/
          
          try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","armaan8886");
            //Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","armaan8886");
            
            
            Statement st = con.createStatement();
            //Statement st1 = con1.createStatement();
            
            Calendar calendar = Calendar.getInstance();
            
    java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
            ResultSet rs;
             //ResultSet rs1;
            
             PreparedStatement ps=con.prepareStatement("select * from Account1 where acno=? and password=? ");
                  ps.setLong(1, y);
                  ps.setString(2, z);
                   //ps.setString(3, br);
                  
                  rs=ps.executeQuery();
                  
                  
                  
                  
                  if(rs.next())
                  {
                   
                  
                 
            
                 
                 String INSERT_RECORD = "insert into requesttable1(name,acno,type,date2)values(?,?,?,?)";
    
    PreparedStatement pstmt = con.prepareStatement(INSERT_RECORD);
    pstmt.setString(1,name);
    pstmt.setLong(2,y);
    pstmt.setString(3, "NEW CHEQUE BOOK REQUEST");
   // pstmt.setLong(4,0);
   // pstmt.setLong(5,0);
    //pstmt.setLong(6,0);
    
    
    
    
    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
    pstmt.setDate(4, sqlDate);
    
    pstmt.executeUpdate();
                 
                
    String  sq2 = "SELECT email FROM Account1" +
                   " WHERE acno = " + y;
                     rs = st.executeQuery(sq2);
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         //Long id  = rs.getLong("balance");
         email1=rs.getString("email");
    
      }
    
 
           RequestDispatcher rd4=request.getRequestDispatcher("./succchapp.html");
                        rd4.include(request,response);  
        
                  }
       
         
                  else
                  {
                        //out.println("<center><h2>Invalid Location Password Enter Correct Location Password</h2></center>");
                        RequestDispatcher rd8=request.getRequestDispatcher("./invalidpasschapp.html");
                        rd8.include(request,response);
                       
                  }

          }

          
        catch (Exception e)
                {
                System.out.println(e);
                }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ApplyCheque</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApplyCheque at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
