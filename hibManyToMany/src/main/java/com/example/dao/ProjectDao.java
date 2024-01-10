package com.example.dao;

import com.example.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProjectDao {
    public void insert(Project project) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(project);
        transaction.commit();
        session.close();
    }

    public List<Project> search() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Project");
        List<Project> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public void update(Project project) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(project);
        transaction.commit();
        session.close();
    }

    public List<Project> findProjectById(int id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Project where projectId="+id);
        List<Project> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public List<Object[]> findProjectByEmployee(int employeeId){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT pt.Project_ID , pt.Project_Name from Project_Table pt join Employee_Project ep on pt.Project_ID = ep.pid WHERE ep.eid ="+employeeId);
        List<Object[]> list = query.getResultList();
        transaction.commit();
        session.close();
        return list;
    }

    public void delete(Project project){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(project);
        transaction.commit();
        session.close();
    }
}
