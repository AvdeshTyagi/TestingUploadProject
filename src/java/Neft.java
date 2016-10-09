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
public class Neft extends HttpServlet {

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
             Long recac =Long.parseLong(request.getParameter("t6"));
            String bankname =request.getParameter("t4");
            Long x=Long.parseLong(request.getParameter("t7"));
             
          String z=request.getParameter("t8");
          
           String name1="Bob";
            String name2="bob";
            String name3="Bank of Baroda";
            String name4="bank of baroda";
            String name5="Bank of baroda";
          
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
                   
                    String  sql = "SELECT balance,email FROM Account1" +
                   " WHERE acno = " + y;
                     rs = st.executeQuery(sql);
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         Long id  = rs.getLong("balance");
      String email=rs.getString("email");
       if(x<=id)
        {
                      
                    
                 st.executeUpdate("update Account1 set balance=balance-" +x +"where acno="+y ); 
                 
            
                 
                 String INSERT_RECORD = "insert into statementtable(acno,date2,drcr,amount,specification,type) values(?,?,?,?,?,?)";
    
    PreparedStatement pstmt = con.prepareStatement(INSERT_RECORD);
    pstmt.setLong(1,y);
    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
    pstmt.setDate(2, sqlDate);
    pstmt.setString(3, "Dr");
    pstmt.setLong(4,x);
    pstmt.setString(5,"fund transfered to Account No."+ recac);
         pstmt.setString(6,"NEFT SEND");
    pstmt.executeUpdate();
                 
                 
                 
                 
                 
                 
     
                 
                 
                 
              
           
          
        
                
                 if(bankname.equals(name1)||bankname.equals(name2)||bankname.equals(name3)||bankname.equals(name4)||bankname.equals(name5))
                 //(tv.equals(television))
                 
                 {
                     
                     st.executeUpdate("update Account1 set balance=balance+" +x +"where acno="+recac );
                     /*PreparedStatement ps2=con.prepareStatement("insert into ststementtable values(?,?,?,?,?,?)");
         ps2.setLong(1,recac);
         ps2.setDate(2, ourJavaDateObject);
         
         ps2.setString(3,"Cr");
         ps2.setLong(4,x);
         ps2.setString(5,"fund received from Account No."+ y);
         ps2.setString(6,"CASH RECEIVED");
         
           
          
         ps2.executeUpdate();*/
                     PreparedStatement pst = con.prepareStatement(INSERT_RECORD);
    pstmt.setLong(1,recac);
    java.sql.Date sqlDate1 = new java.sql.Date(new java.util.Date().getTime());
    pstmt.setDate(2, sqlDate1);
    pstmt.setString(3, "Cr");
    pstmt.setLong(4,x);
    pstmt.setString(5,"fund received from Account No."+ y);
         pstmt.setString(6,"NEFT RECEIVED");
    pstmt.executeUpdate();
                     
                     
                     
                     
                     
    
    
    
    
    
    String  sq2 = "SELECT email FROM Account1" +
                   " WHERE acno = " + recac;
                     rs = st.executeQuery(sq2);
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         //Long id  = rs.getLong("balance");
         email1=rs.getString("email");
    
      }
    
 
                 }
           RequestDispatcher rd1=request.getRequestDispatcher("./neftsucc.html");
                        rd1.include(request,response);  
        
        }
       else{
            
            RequestDispatcher rd5=request.getRequestDispatcher("./neftunfund.html");
                       rd5.include(request,response);  
        }
         
                      
                          
      }
                  }
                  else
                  {
                        //out.println("<center><h2>Invalid Location Password Enter Correct Location Password</h2></center>");
                        RequestDispatcher rd2=request.getRequestDispatcher("./neftpass.html");
                        rd2.include(request,response);
                       
                  }
                  
            }
        


        catch (Exception e)
                {
                System.out.println(e);
                }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
           /* out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Neft</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Neft at " + request.getContextPath() + "</h1>");
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
