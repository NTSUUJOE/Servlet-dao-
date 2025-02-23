<%@ page language="java" contentType="text/html; charset=UTF-8"
     import="java.util.*, com.lcapn.bean.EmpBean" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工資料</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>員工資料</h2>

<% 
List<EmpBean> emps = (List<EmpBean>) request.getAttribute("emps");
if (emps != null && !emps.isEmpty()) { 
%>
  <table border="1">
  <tr style="background-color:#a8fefa">
    <th>員工編號</th><th>姓名</th><th>到職日</th><th>薪水</th><th>部門編號</th><th>職稱</th>
  </tr>
  <% for(EmpBean emp : emps) { %>
    <tr>
      <td><%= emp.getEmpno() %></td>
      <td><%= emp.getEname() %></td>
      <td><%= emp.getHiredate() %></td>
      <td><%= emp.getSalary() %></td>
      <td><%= emp.getDeptno() %></td>
      <td><%= emp.getTitle() %></td>
    </tr>
  <% } %>
  </table>
  <h3>共 <%= emps.size() %> 筆員工資料</h3>
<% } else { %>
  <p>查無資料</p>
<% } %>

</div>
</body>
</html>
