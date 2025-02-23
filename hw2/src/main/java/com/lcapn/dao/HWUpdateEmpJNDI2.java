package com.lcapn.dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.lcapn.bean.EmpBean;

@WebServlet("/HWUpdateEmpJNDI2")
public class HWUpdateEmpJNDI2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empno = request.getParameter("empno");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 透過 JNDI 取得資料庫連線
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();

            // 查詢特定員工資料
            String sql = "SELECT * FROM employee WHERE empno = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, empno);
            rs = stmt.executeQuery();

            EmpBean emp = null;
            if (rs.next()) {
                emp = new EmpBean();
                emp.setEmpno(rs.getString("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setHiredate(rs.getString("hiredate"));
                emp.setSalary(rs.getString("salary"));
                emp.setDeptno(rs.getString("deptno"));
                emp.setTitle(rs.getString("title"));
            }

            // 若找到員工，轉發到更新頁面
            if (emp != null) {
                request.setAttribute("emp", emp);
                request.getRequestDispatcher("/Update/GetEmployee.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "查無此員工編號：" + empno);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            request.setAttribute("error", "資料庫錯誤：" + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } finally {
            // 確保資源被關閉
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
