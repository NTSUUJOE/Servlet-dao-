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

@WebServlet("/HWUpdateEmpJNDI")
public class HWUpdateEmpJNDI extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EmpBean emp = new EmpBean(
                request.getParameter("empno"),
                request.getParameter("ename"),
                request.getParameter("hiredate"),
                request.getParameter("salary"),
                request.getParameter("deptno"),
                request.getParameter("title")
            );
            employeeDAO.updateEmployee(emp);
            request.getRequestDispatcher("/HWGetAllEmpsJNDI").forward(request, response);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
