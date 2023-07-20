

package com.connection.mysql;


import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.sql.Connection;
 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.jsp.tagext.TryCatchFinally;

import com.Bean.model.RegisterationFormBean;
import com.Register.java.Register;
import com.RegisterationForm.controller.java.RegisterationForm;
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

import sun.security.util.Password;
 
 
public class ConnectionUtil implements Connectionn{
 
 
    public  Connection getConnection() {
 
        Connection con = null;
 
        try {
 
            Class.forName("com.mysql.cj.jdbc.Driver");
 
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abu","root","umar25");
 
            System.out.println("connection is created");
 
        } catch (Exception e) {
 
          e.printStackTrace();
 
        }
 
        
 
        return con;
 
        
 
    }
   
    
 
    
    public  void update(RegisterationFormBean scBean)
    {
    	try {
    		
    //		 RegisterationFormBean rBean=new RegisterationFormBean();
    		 Connection con = getConnection();
            String query = "UPDATE registerform SET Date = ?, name = ?, address = ?, gender = ?, maritalstatus = ?, dob = ?, pancard_detail = ?, aadhar_detail = ?, email_detail = ?, phone_detail = ?, qulification_detail = ?, ssc_detail = ?, ssc_percentage = ?, ssc_year = ?, hsc = ?, hsc_percentage = ?, hsc_year = ?, graduate = ?, graduate_percentage = ?, graduate_year = ?, post_graduate = ?, post_graduate_percentage = ?, post_graduate_year = ?, sign_detail = ? WHERE id= (SELECT * FROM (SELECT id FROM registerform ORDER BY id DESC LIMIT 1) AS temp)";
 
            PreparedStatement statement = con.prepareStatement(query);
 
            statement.setString(1,scBean.getDate());
 
            statement.setString(2, scBean.getApplicantName());
 
            statement.setString(3, scBean.getAddress());
 
            statement.setString(4,scBean.getGender());
 
            statement.setString(5,scBean.getMaritalStatus());
 
            statement.setString(6, scBean.getDob());
 
            statement.setString(7, scBean.getPan());
 
            statement.setString(8, scBean.getAadhar());
 
            statement.setString(9, scBean.getEmail());
 
            statement.setString(10, scBean.getPhone());
 
            statement.setString(11, scBean.getQualification());
 
            statement.setString(12, scBean.getSSC());
 
            statement.setString(13, scBean.getSSC_Percentage());
 
            statement.setString(14, scBean.getSSC_Year());
 
            statement.setString(15, scBean.getHSC());
 
            statement.setString(16, scBean.getHSC_Percentage());
 
            statement.setString(17, scBean.getHSC_Year());
 
            statement.setString(18, scBean.getGraduate());
 
            statement.setString(19, scBean.getGraduate_Percentage());
 
            statement.setString(20, scBean.getGraduate_Year());
 
            statement.setString(21, scBean.getPost_Graduate());
 
            statement.setString(22, scBean.getPost_Graduate_Percentage());
 
            statement.setString(23, scBean.getPost_Graduate_Year());
 
            statement.setString(24, scBean.getSign());
 
            int rowsAffected = statement.executeUpdate();
            
           
            generate_Pdf();
 
            con.close();

        } catch (Exception e) {
 
            e.printStackTrace();
 
        }
 
     }
    	
    	
    	
    
    	
    	
    	
    	
    
    
    
    public  void generate_Pdf() {
    	 
        try {
        	Connectionn connectionn=new ConnectionUtil();
 
            String filename = "C:\\Users\\TSIS\\Desktop\\Java_Programe.pdf";
 
          //  Document document = new Document();
            	com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            	 
            PdfWriter.getInstance(document, new FileOutputStream(filename));
 
            document.open();
 
            Connection con = connectionn.getConnection();
 
            PreparedStatement statement = con.prepareStatement("SELECT id, pic, Date, name, address, gender, maritalstatus, dob, pancard_detail, aadhar_detail, email_detail, phone_detail, qulification_detail, ssc_detail, ssc_percentage, ssc_year, hsc, hsc_percentage, hsc_year, graduate, graduate_percentage, graduate_year, post_graduate, post_graduate_percentage, post_graduate_year, sign_detail FROM registerform ORDER BY id DESC LIMIT 1");
 
            ResultSet rs = statement.executeQuery();
 
            
 
            Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);
 
            Paragraph heading = new Paragraph("Registeration Form", headingFont);
 
            heading.setAlignment(Element.ALIGN_CENTER);
 
            document.add(heading);
 
 
            Font headingFont1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.RED);
 
            Paragraph heading1 = new Paragraph("Techskills IT Solution", headingFont1);
 
            heading1.setAlignment(Element.ALIGN_CENTER);
 
            document.add(heading1);
 
 
            Font headingFont11 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
 
            Paragraph heading11 = new Paragraph("A-101, First Floor, Above A-20, Sakinaka Industrial Market,\nBeside Holiday Inn Hotel Ghatkopar-Andheri Link Road,\nSakinaka, Andheri(E), Mumbai-400072\n\n", headingFont11);
 
            heading11.setAlignment(Element.ALIGN_CENTER);
 
            document.add(heading11);
 
            
 
            // Define table properties
 
            PdfPTable table = new PdfPTable(2);
 
            table.setWidthPercentage(100);
 
            float[] columnWidths = {1f, 3f}; // Adjust column widths as needed
 
            table.setWidths(columnWidths);
 
            Paragraph paragraph = new Paragraph();
 
 
            while (rs.next()) {
 
                int id = rs.getInt("id");
 
                byte[] imageData = rs.getBytes("pic");
 
                String date = rs.getString("Date");
 
                String name = rs.getString("name");
 
                String address = rs.getString("address");
 
                String gender = rs.getString("gender");
 
                String maritalStatus = rs.getString("maritalstatus");
 
                String dob = rs.getString("dob");
 
                String pancard = rs.getString("pancard_detail");
 
                String aadhar = rs.getString("aadhar_detail");
 
                String email = rs.getString("email_detail");
 
                String phone = rs.getString("phone_detail");
 
                String qualification = rs.getString("qulification_detail");
 
                String ssc = rs.getString("ssc_detail");
 
                String sscPercentage = rs.getString("ssc_percentage");
 
                String sscYear = rs.getString("ssc_year");
 
                String hsc = rs.getString("hsc");
 
                String hscPercentage = rs.getString("hsc_percentage");
 
                String hscYear = rs.getString("hsc_year");
 
                String graduate = rs.getString("graduate");
 
                String graduatePercentage = rs.getString("graduate_percentage");
 
                String graduateYear = rs.getString("graduate_year");
 
                String postGraduate = rs.getString("post_graduate");
 
                String postGraduatePercentage = rs.getString("post_graduate_percentage");
 
                String postGraduateYear = rs.getString("post_graduate_year");
 
                String sign = rs.getString("sign_detail");
 
                
 
                Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);  
 
                Paragraph p2 = new Paragraph();
 
                Font f = new Font();
 
                f.setStyle(Font.BOLD);
 
                f.setSize(8);
 
 
                // Retrieve the ID value from the database
 
                // Assuming the variable id1 holds the ID value
 
 
                String formNo = "Form No: " + id;
 
                p2.add(new Chunk(formNo, labelFont)); // Add the form number to the paragraph
 
                document.add(p2);
 
                
 
                // Create an empty cell for spacing
 
                PdfPCell emptyCell = new PdfPCell();
 
                emptyCell.setBorder(Rectangle.NO_BORDER);
 
                table.addCell(emptyCell);
 
 
                // Create a PdfPCell for the image
 
                Image image = Image.getInstance(imageData);
 
                image.scaleToFit(200f, 200f);
 
                PdfPCell imageCell = new PdfPCell(image);
 
                imageCell.setBorder(Rectangle.NO_BORDER);
 
                imageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
 
                imageCell.setVerticalAlignment(Element.ALIGN_TOP);
 
                table.addCell(imageCell);
 
 
 
               
 
               
 
               // table.addCell(new PdfPCell(new Phrase("Form No:", labelFont)));
 
                //table.addCell(new PdfPCell(new Phrase(String.valueOf(id1), labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Date:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(date, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Name:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(name, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Address:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(address, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Gender:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(gender, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Marital Status:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(maritalStatus, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Date of Birth:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(dob, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Pancard:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(pancard, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Aadhar:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(aadhar, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Email:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(email, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Phone:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(phone, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Qualification:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(qualification, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("SSC:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(ssc, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("SSC Percentage:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(sscPercentage, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("SSC Year:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(sscYear, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("HSC:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(hsc, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("HSC Percentage:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(hscPercentage, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("HSC Year:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(hscYear, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Graduate:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(graduate, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Graduate Percentage:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(graduatePercentage, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Graduate Year:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(graduateYear, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Post Graduate:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(postGraduate, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Post Graduate Percentage:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(postGraduatePercentage, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Post Graduate Year:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(postGraduateYear, labelFont)));
 
 
                table.addCell(new PdfPCell(new Phrase("Signature:", labelFont)));
 
                table.addCell(new PdfPCell(new Phrase(sign, labelFont)));
 
               
 
                document.add(paragraph);
 
            }
 
 
            // Add the table to the PDF document
 
            document.add(table);
 
 
            document.close();
 
            System.out.println("PDF created");
 
        } catch (Exception e) {
 
            e.printStackTrace();
 
        }
 
    }
    	
    
    
 
    
 
}