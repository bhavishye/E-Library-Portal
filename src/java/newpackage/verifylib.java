/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import static com.ibm.db2.jcc.a.d.n;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author db2admin
 */
public class verifylib extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter pw2 = response.getWriter();
        
    
    try
        {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
         //  pw2.print("Drivers connected");
            
            Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/library","db2admin","12345");
        // pw2.print("connection created");
            String n = request.getParameter("username");
            String p = request.getParameter("password");            
                Statement ps = con.createStatement();
        
            ResultSet rs = ps.executeQuery("Select * from tab1 where email='"+n+"' and password='"+p+"' ");
         //pw2.print("query executed");
           if(rs.next())
            {
              RequestDispatcher re = request.getRequestDispatcher("librarian.html");
              re.forward(request, response);
            //pw2.print("OK");
            }
            
               else
           {
              //pw2.print("SORRY");
          RequestDispatcher re = request.getRequestDispatcher("index.html");
          re.include(request, response);
                   
           }
                    
    }
    
    catch(Exception e){
    e.printStackTrace();}

    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

