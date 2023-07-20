
package com.Register.java;


import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
 
import java.sql.PreparedStatement;
 
 
import javax.servlet.ServletException;
 
import javax.servlet.annotation.WebServlet;
 
import javax.servlet.http.HttpServlet;
 
import javax.servlet.http.HttpServletRequest;
 
import javax.servlet.http.HttpServletResponse;
 
 
import com.connection.mysql.ConnectionUtil;
import com.connection.mysql.Connectionn;
 
@WebServlet("/Register")
 
public class Register extends HttpServlet{
 
 
    @Override
 
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		
    		Connectionn connectionn=new ConnectionUtil();
    		
    	
           String userName = req.getParameter("userName");        
 
           String email = req.getParameter("email");        
 
           String phoneNO = req.getParameter("phoneNO"); 
 
           String password = req.getParameter("password");
           	
          
   		
           
           
          try {
        	 	
            MessageDigest messageDigest= MessageDigest.getInstance("MD5");
   			messageDigest.update(password.getBytes());
   			byte[] result=messageDigest.digest();
   			StringBuilder sb=new StringBuilder();
   			for(byte b:result)
   			{
   				sb.append(String.format("%02x", b));
   				
   			}
   			String string=sb.toString();
   			
   			
   			
   			
      		
            Connection con = connectionn.getConnection();
 
            PreparedStatement statement = con.prepareStatement("insert into register(userName,email,phoneNO,password) values(?,?,?,?)");
 
            statement.setString(1,userName );
 
            statement.setString(2, email);
 
            statement.setString(3, phoneNO);
 
            statement.setString(4, string);
 
            int data = statement.executeUpdate();
 
            
 
            if(data>0) {
 
                req.setAttribute("obj", "success");
 
            }else {
 
                req.setAttribute("obj", "fail");
 
            }
 
            req.getRequestDispatcher("Register.jsp").forward(req, resp);
 
            con.close();
 
        } catch (Exception e) {
 
        e.printStackTrace();
 
        }
 
           
 
    }
 
}