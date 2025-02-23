package com.lcapn.bean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public class Test {
	    public static void main(String[] args) {
	        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=servdb;encrypt=false";
	        String user = "gay2";
	        String password = "gay2";

	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            Connection conn = DriverManager.getConnection(url, user, password);
	            System.out.println("Database connected successfully!");

	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
