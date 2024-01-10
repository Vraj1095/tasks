package com.example.dao;

import com.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao {
    public void insert(Employee employee) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }

    public List search() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Employee ");
        List<Employee> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public List<Employee> edit(String id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Employee where employeeId = " + id);
        List<Employee> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public void update(Employee employee) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(employee);
        transaction.commit();
        session.close();
    }

    public void delete(Employee employee){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(employee);
        transaction.commit();
        session.close();
    }

    public List<Object[]> findEmployeeByProject(int projectId){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT et.Employee_ID , et.Employee_Name from employee_table et join Employee_Project ep on et.Employee_ID = ep.eid  where ep.pid ="+projectId);
        List<Object[]> list = query.getResultList();
        transaction.commit();
        session.close();
        return list;
    }

    public List<Object[]> findEmployeeNotOfProject(int projectId){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String que = "SELECT * from employee_table et where et.Employee_ID not in (SELECT et.Employee_ID  from employee_table et left join Employee_Project ep on et.Employee_ID = ep.eid WHERE ep.pid = "+projectId+")";
        Query query = session.createNativeQuery(que);
        List<Object[]> list = query.getResultList();
        transaction.commit();
        session.close();
        return list;
    }
}
