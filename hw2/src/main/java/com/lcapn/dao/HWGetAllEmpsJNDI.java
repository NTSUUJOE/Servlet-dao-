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
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.lcapn.bean.EmpBean;

@WebServlet("/HWGetAllEmpsJNDI")
public class HWGetAllEmpsJNDI extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 透過 JNDI 取得資料庫連線
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();

            // 執行 SQL 查詢
            String sql = "SELECT * FROM employee";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            List<EmpBean> emps = new ArrayList<>();
            while (rs.next()) {
                EmpBean emp = new EmpBean();
                emp.setEmpno(rs.getString("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setHiredate(rs.getString("hiredate"));
                emp.setSalary(rs.getString("salary"));
                emp.setDeptno(rs.getString("deptno"));
                emp.setTitle(rs.getString("title"));
                emps.add(emp);
            }

            // 將員工資料存入 request scope
            request.setAttribute("emps", emps);

            // 轉發到 JSP 頁面顯示結果
            request.getRequestDispatcher("/HomeWork1/GetAllEmp.jsp").forward(request, response);
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
