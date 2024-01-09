package com.example.dao;

import com.example.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class CountryDAO {
    public void insertCountry(Country country){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(country);
        transaction.commit();
        session.close();
    }

    public List searchCountry(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query= session.createQuery("from Country");
        List list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public List findCountryById(int id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query= session.createQuery("from Country where id="+id);
        List list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public void deleteCountry(Country country){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(country);
        transaction.commit();
        session.close();
    }
}
