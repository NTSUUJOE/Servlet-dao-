package com.lcapn.bean;

public class EmpBean {
    private String empno;
    private String ename;
    private String hiredate;
    private String salary;
    private String deptno;
    private String title;

    // ✅ 新增一個無參數建構子
    public EmpBean() { 
        // 預設建構子，允許先建立物件再設定屬性
    }

    // ✅ 帶參數建構子（如果需要）
    public EmpBean(String empno, String ename, String hiredate, String salary, String deptno, String title) {
        this.empno = empno;
        this.ename = ename;
        this.hiredate = hiredate;
        this.salary = salary;
        this.deptno = deptno;
        this.title = title;
    }

    // ✅ Getter 和 Setter 方法
    public String getEmpno() { return empno; }
    public void setEmpno(String empno) { this.empno = empno; }

    public String getEname() { return ename; }
    public void setEname(String ename) { this.ename = ename; }

    public String getHiredate() { return hiredate; }
    public void setHiredate(String hiredate) { this.hiredate = hiredate; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getDeptno() { return deptno; }
    public void setDeptno(String deptno) { this.deptno = deptno; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
