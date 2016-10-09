/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.servlet.http.*;
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
public class ChangePassword extends HttpServlet {

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
             String cpass =request.getParameter("t1");
             String newpass1 =request.getParameter("t2");
             String newpass2 =request.getParameter("t3");
             
             
          
          try {
               HttpSession session = request.getSession(true);
              String pass=(String)session.getAttribute("pass1");
      Long acnu=(Long)session.getAttribute("acno1");
      String uname=(String)session.getAttribute("uname1");
      if(cpass.equals(pass)&&newpass1.equals(newpass2)){
      
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","armaan8886");
            //Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","armaan8886");
            
            
            Statement st = con.createStatement();
            //Statement st1 = con1.createStatement();
            
            Calendar calendar = Calendar.getInstance();
            
    java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
            ResultSet rs;
             //ResultSet rs1;
            
             PreparedStatement ps=con.prepareStatement("update Account1 set password='"+newpass1+ "' where acno=? and username=? ");
                  ps.setLong(1, acnu);
                  ps.setString(2, uname);
                   //ps.setString(3, br);
                  
                  rs=ps.executeQuery();
                  
                  
                  
                                 
    String  sq2 = "SELECT email FROM Account1" +
                   " WHERE acno = " + acnu;
                     rs = st.executeQuery(sq2);
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         //Long id  = rs.getLong("balance");
         email1=rs.getString("email");
    
      }
    
 
                  
                  
                  
                  
             session.setAttribute("pass1", newpass1);     
                  
                  
                  
                  RequestDispatcher rd1=request.getRequestDispatcher("./passchangesucc.html");
                        rd1.include(request,response);
                 
        }
                  else
                  {
                        if(cpass.compareTo(pass)!=0&&newpass1.equals(newpass2)){
                            RequestDispatcher rd2=request.getRequestDispatcher("./invalidchangepass.html");
                        rd2.include(request,response);
                      
                  }
                        else{
                             RequestDispatcher rd2=request.getRequestDispatcher("./passwordconfirmfail.html");
                        rd2.include(request,response);
                        }
                  }
                  
            
        
        }

        catch (Exception e)
                {
                System.out.println(e);
                }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassword</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
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
