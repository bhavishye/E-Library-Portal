
package newpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addbook extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        //pw.println("hello");
        String n = request.getParameter("add1");
        //pw.println("<br>"+n);
        String n2 = request.getParameter("add2");
        //pw.println("<br>"+n2);
        String n3 = request.getParameter("add3");
        //pw.println("<br>"+n3);
        String n4 = request.getParameter("add4");       
        try
        {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            //pw.print("Drivers connected");
            
            Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/library","db2admin","12345");
            //pw.print("connection created");
            
            PreparedStatement ps = con.prepareStatement("Insert into book values (?,?,?,?)");
        
            //pw.print("query started");
            ps.setString(1, n);
            ps.setString(2, n2);
            ps.setString(3, n3);
            ps.setString(4, n4);
            //pw.print("2last step");
            
            ps.executeUpdate();
            //pw.print("last step");
            RequestDispatcher re = request.getRequestDispatcher("saved2.html");
            re.forward(request, response);
            
        }
        catch(Exception e)
        {e.printStackTrace();}
        
        
        
  //    
    }

  
    public String getServletInfo() 
{
        return "Short description";
}


}