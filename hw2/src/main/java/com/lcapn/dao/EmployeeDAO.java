package com.lcapn.dao;

import com.lcapn.bean.EmpBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection getConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
        return ds.getConnection();
    }

    
    public void insertEmployee(EmpBean emp) throws SQLException, NamingException {
        String sql = "INSERT INTO employee (empno, ename, hiredate, salary, deptno, title) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getEmpno());
            stmt.setString(2, emp.getEname());
            stmt.setString(3, emp.getHiredate());
            stmt.setString(4, emp.getSalary());
            stmt.setString(5, emp.getDeptno());
            stmt.setString(6, emp.getTitle());
            stmt.executeUpdate();
        }
    }

    
    public void deleteEmployee(String empno) throws SQLException, NamingException {
        String sql = "DELETE FROM employee WHERE empno = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empno);
            stmt.executeUpdate();
        }
    }

  
    public void updateEmployee(EmpBean emp) throws SQLException, NamingException {
        String sql = "UPDATE employee SET ename = ?, hiredate = ?, salary = ?, deptno = ?, title = ? WHERE empno = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getEname());
            stmt.setString(2, emp.getHiredate());
            stmt.setString(3, emp.getSalary());
            stmt.setString(4, emp.getDeptno());
            stmt.setString(5, emp.getTitle());
            stmt.setString(6, emp.getEmpno());
            stmt.executeUpdate();
        }
    }

  
    public EmpBean getEmployeeById(String empno) throws SQLException, NamingException {
        String sql = "SELECT * FROM employee WHERE empno = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empno);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new EmpBean(
                    rs.getString("empno"),
                    rs.getString("ename"),
                    rs.getString("hiredate"),
                    rs.getString("salary"),
                    rs.getString("deptno"),
                    rs.getString("title")
                );
            }
        }
        return null;
    }

    
    public List<EmpBean> getAllEmployees() throws SQLException, NamingException {
        List<EmpBean> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                employees.add(new EmpBean(
                    rs.getString("empno"),
                    rs.getString("ename"),
                    rs.getString("hiredate"),
                    rs.getString("salary"),
                    rs.getString("deptno"),
                    rs.getString("title")
                ));
            }
        }
        return employees;
    }
}
