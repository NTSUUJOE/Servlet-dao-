package com.lcapn.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.lcapn.bean.EmpBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetEmp")
public class GetEmp extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empno = request.getParameter("empno");
        try {
            EmpBean emp = employeeDAO.getEmployeeById(empno);
            request.setAttribute("emp", emp);
            request.getRequestDispatcher("/HomeWork1/GetAllEmp.jsp").forward(request, response);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
