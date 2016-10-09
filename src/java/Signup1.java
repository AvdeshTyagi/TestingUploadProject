

import javax.servlet.RequestDispatcher;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Signup1 extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            String fname=request.getParameter("t1");
            String lname=request.getParameter("t2");
            Long acno=Long.parseLong(request.getParameter("t3"));
            String email=request.getParameter("t4");
            Long mob=Long.parseLong(request.getParameter("t5"));
            String uname=request.getParameter("t6");
            String pass=request.getParameter("t7");
            String locpass=request.getParameter("t8");
            String addr=request.getParameter("t9");
            
           
            InetAddress ipAddr = InetAddress.getLocalHost();
            
            String ar;
            ar = ipAddr.getHostAddress();
           
            try{
                 Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","armaan8886");
          
                 String INSERT_RECORD = "insert into Account1(fname,lname,acno,address,mob,username,password,locationpassword,ipaddress,email,balance)values(?,?,?,?,?,?,?,?,?,?,?)";
    
    PreparedStatement pstmt = con.prepareStatement(INSERT_RECORD);
    //pstmt.setString(1,name);
   
    //pstmt.executeUpdate();
     pstmt.setString(1,fname);
         pstmt.setString(2,lname);
         pstmt.setLong(3,acno);
         pstmt.setString(4,addr);
         pstmt.setLong(5,mob);
         pstmt.setString(6,uname);
         pstmt.setString(7,pass);
         pstmt.setString(8,locpass);
         pstmt.setString(9,ar);            
          pstmt.setString(10,email); 
          pstmt.setLong(11,000);
         int i=pstmt.executeUpdate();
     
         
         if(i>0)
             out.println("<center><h2 style=\"color:red;\">You are successfully registered</h2></center>");
            
             
            
                      
                        HttpSession session2=request.getSession();  
        session2.setAttribute("acno1",acno);  
        session2.setAttribute("uname1",uname); 
        session2.setAttribute("pass1",pass); 
        
     
        
        RequestDispatcher rd2=request.getRequestDispatcher("./newwel.html");
                        rd2.include(request,response);
            
            }
            
             catch(Exception e)
            {
                System.out.println(e);
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            //out.println("<title>Servlet Signup1</title>");            
            out.println("</head>");
            out.println("<body>");
           // out.println("<h1>Servlet Signup1 at " + request.getContextPath() + "</h1>");
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
