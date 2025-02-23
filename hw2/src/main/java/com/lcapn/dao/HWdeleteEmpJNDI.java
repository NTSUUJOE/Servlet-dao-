package com.lcapn.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/HWdeleteEmpJNDI")
public class HWdeleteEmpJNDI extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empno = request.getParameter("empno");
        try {
            employeeDAO.deleteEmployee(empno);
            request.getRequestDispatcher("/HWGetAllEmpsJNDI").forward(request, response);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
