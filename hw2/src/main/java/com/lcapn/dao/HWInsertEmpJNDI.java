package com.lcapn.dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lcapn.bean.EmpBean;


@WebServlet("/HWInsertEmpJNDI")
public class HWInsertEmpJNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
		Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empno=request.getParameter("empno");
		String ename=request.getParameter("ename");
		String hiredate=request.getParameter("hiredate");
		String salary=request.getParameter("salary");
		String deptno=request.getParameter("deptno");
		String title=request.getParameter("title");
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			conn=ds.getConnection();
			String sql = "insert into employee(empno,ename,hiredate,salary,deptno,title)"
					+ " values(?,?,?,?,?,?)";
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, empno);
			stmt.setString(2, ename);
			stmt.setString(3, hiredate);
			stmt.setString(4, salary);
			stmt.setString(5, deptno);
			stmt.setString(6, title);
			stmt.execute();	
			request.getRequestDispatcher("/HWGetAllEmpsJNDI").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
