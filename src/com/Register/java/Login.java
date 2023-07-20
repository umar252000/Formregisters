
package com.Register.java;


import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
 
import java.sql.PreparedStatement;
 
import java.sql.ResultSet;
 
 
import javax.servlet.RequestDispatcher;
 
import javax.servlet.ServletException;
 
import javax.servlet.annotation.WebServlet;
 
import javax.servlet.http.HttpServlet;
 
import javax.servlet.http.HttpServletRequest;
 
import javax.servlet.http.HttpServletResponse;
 
import javax.servlet.http.HttpSession;
 
 
import com.connection.mysql.ConnectionUtil;
import com.connection.mysql.Connectionn;
 
@WebServlet("/Login")
 
public class Login extends HttpServlet {
 
    @Override
 
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 
    	
    	Connectionn connectionn=new ConnectionUtil();
        HttpSession session = req.getSession();
 
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
 
            PreparedStatement statement = con.prepareStatement("SELECT * FROM register WHERE email =? OR phoneNO=? AND password=?");
            statement.setString(1,phoneNO);
            statement.setString(2, phoneNO);
 
            statement.setString(3, string);
            
            
            
 
            ResultSet resultSet = statement.executeQuery();
 
            if (resultSet.next()) {
 
                RequestDispatcher dispatcher = req.getRequestDispatcher("Registeration.jsp");
 
                dispatcher.forward(req, resp);
 
            } else {
 
                req.setAttribute("obj", "failed");
 
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
 
                dispatcher.forward(req, resp);
 
            }
 
        } catch (Exception e) {
 
           e.printStackTrace();
 
        }
 
    }
 
}
