package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.model.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class EmployeeDAO {
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public int save(Employee p){
        String sql="insert into Employee(name,salary,designation) values('"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";
        return template.update(sql);
    }
    public int update(Employee p){
        String sql= "update Employee set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId();
        return template.update(sql);
    }
    public int delete(int id){
        String sql= "delete from Employee where id="+id;
        return template.update(sql);
    }
    public Employee getEmpById(int id){
        String sql="select * from Employee where id=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class));
    }
    public List<Employee> getEmployees(){
        return template.query("select * from Employee", (rs, row) -> {
            Employee e=new Employee();
            e.setId(rs.getInt(1));
            e.setName(rs.getString(2));
            e.setSalary(rs.getFloat(3));
            e.setDesignation(rs.getString(4));
            return e;
        });
    }
}

