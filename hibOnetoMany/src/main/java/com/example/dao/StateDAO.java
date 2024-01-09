package com.example.dao;

import com.example.model.State;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class StateDAO {

    public void insertState(State state){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(state);
        transaction.commit();
        session.close();
    }

    public List searchState(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from State");
        List list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public State searchStateById(int id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from State where id="+id);
        List list = query.list();
        transaction.commit();
        session.close();
        return (State) list.get(0);
    }

    public List<Object[]> findStateByCountry(int countryId){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT s.id, s.name  FROM state s INNER JOIN Country c ON s.countryId = c.id where c.id ="+countryId);
        List list = query.getResultList();
        transaction.commit();
        session.close();
        return list;
    }

    public List<Object[]> findStateOfOtherCountry(int countryId){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT s.id, s.name  FROM state s where s.countryId != "+countryId+" or s.countryId is NULL");
        List list = query.getResultList();
        transaction.commit();
        session.close();
        return list;
    }
}
