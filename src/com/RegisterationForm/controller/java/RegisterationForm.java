
package com.RegisterationForm.controller.java;


import java.io.File;

 
import java.io.FileOutputStream;
 
import java.io.IOException;
 
import java.io.PrintWriter;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
 
import java.sql.ResultSet;
 
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
 
import javax.servlet.annotation.WebServlet;
 
import javax.servlet.http.HttpServlet;
 
import javax.servlet.http.HttpServletRequest;
 
import javax.servlet.http.HttpServletResponse;

import com.Bean.model.RegisterationFormBean;
import com.connection.mysql.ConnectionUtil;
import com.connection.mysql.Connectionn;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.xml.internal.txw2.Document;
 
@WebServlet("/RegisterForm")
 
public class RegisterationForm extends HttpServlet {
 
    
 
    @Override
 
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {    
 
    	RegisterationFormBean scBean=new RegisterationFormBean();
    	int count=1;
    	Connectionn connectionn=new ConnectionUtil();
        resp.setContentType("text/html");
 
        PrintWriter out = resp.getWriter();
 
        String id = req.getParameter("id");
 //       int i=Integer.parseInt(id);
 //       scBean.setId(i);
        
  //      String pic = req.getParameter("pic");
        scBean.setPic(req.getParameter("pic"));
        
        scBean.setDate(req.getParameter("date"));
 //       String date = req.getParameter("date");
 
        scBean.setApplicantName(req.getParameter("applicantName"));
  //      String applicantName = req.getParameter("applicantName");
 
        scBean.setAddress(req.getParameter("address"));
 //       String address = req.getParameter("address");
        
        scBean.setGender(req.getParameter("gender"));
 //       String gender = req.getParameter("gender");
 
        scBean.setMaritalStatus(req.getParameter("maritalStatus"));
  //      String maritalStatus = req.getParameter("maritalStatus");
 
        scBean.setDob(req.getParameter("dob"));
  //      String dob = req.getParameter("dob");
 
        scBean.setPan(req.getParameter("pan"));
  //      String pan = req.getParameter("pan");
        
        scBean.setAadhar(req.getParameter("aadhar"));
   //   String aadhar = req.getParameter("aadhar");
 
        scBean.setEmail(req.getParameter("email"));
  //      String email = req.getParameter("email");
 
        scBean.setPhone("req.getParameter(\"phone\")");
 //       String phone = req.getParameter("phone");
 
        scBean.setQualification(req.getParameter("qualification"));
  //      String qualification = req.getParameter("qualification");
 
        scBean.setSSC(req.getParameter("SSC"));
 //       String SSC = req.getParameter("SSC");
 
        scBean.setSSC_Percentage(req.getParameter("SSC_Percentage"));
//        String SSC_Percentage = req.getParameter("SSC_Percentage");
 
        scBean.setSSC_Year(req.getParameter("SSC_Year"));
 //       String SSC_Year = req.getParameter("SSC_Year");
 
        scBean.setHSC(req.getParameter("HSC"));
//        String HSC = req.getParameter("HSC");
 
        scBean.setHSC_Percentage(req.getParameter("HSC_Percentage"));
 //       String HSC_Percentage = req.getParameter("HSC_Percentage");

        scBean.setHSC_Year(req.getParameter("HSC_Year"));
 //       String HSC_Year = req.getParameter("HSC_Year");
 
        scBean.setGraduate(req.getParameter("Graduate"));
  //      String Graduate = req.getParameter("Graduate");
 
        scBean.setGraduate_Percentage(req.getParameter("Graduate_Percentage"));
 //      String Graduate_Percentage = req.getParameter("Graduate_Percentage");
 
        scBean.setGraduate_Year(req.getParameter("Graduate_Year"));
  //      String Graduate_Year = req.getParameter("Graduate_Year");
 
        scBean.setPost_Graduate(req.getParameter("Post_Graduate"));
  //      String Post_Graduate = req.getParameter("Post_Graduate");
 
        scBean.setPost_Graduate_Percentage(req.getParameter("Post_Graduate_Percentage"));
 //       String Post_Graduate_Percentage = req.getParameter("Post_Graduate_Percentage");
 
        scBean.setPost_Graduate_Year(req.getParameter("Post_Graduate_Year"));
//        String Post_Graduate_Year = req.getParameter("Post_Graduate_Year");
 
        scBean.setSign(req.getParameter("sign"));
 //       String sign = req.getParameter("sign");
 
        try {
        	
			Connection con =connectionn.getConnection(); 

			PreparedStatement statement = con.prepareStatement("SELECT * FROM registerform WHERE  aadhar_detail= ? AND pancard_detail = ?");

			statement.setString(1, scBean.getAadhar());
			statement.setString(2, scBean.getPan());
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					count++;
					if (count == 2) {
						out.print("<h2>" + "" + "<h2>");
						out.print("<h2>" + " please enter the correct information" + "<h2>");
						RequestDispatcher rd = req.getRequestDispatcher("Registeration.jsp");
						rd.include(req, resp);
					}
				}
			}


 //----------------------------------------------------------
           if(count==1)
           {
            connectionn.update(scBean);
           
            if(scBean!=null)
            {

            	out.println("<h2>" + "register sucessfull" + "<h2>");
            	
				
            }
           } 
       
        } catch (Exception e) {
 
            e.printStackTrace();
 
        }
 
     }
}