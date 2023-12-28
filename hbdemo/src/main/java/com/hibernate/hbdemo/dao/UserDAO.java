package com.hibernate.hbdemo.dao;

import com.hibernate.hbdemo.vo.UserVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void insert(UserVO userVO)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(userVO);

        transaction.commit();

        session.close();
    }

    public List search()
    {
        List list = new ArrayList<>();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from UserVO ");

        list = query.list();

        session.close();

        return list;

    }

    public void delete(UserVO userVO)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session  session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.delete(userVO);

        transaction.commit();

        session.close();
    }

    public void update(UserVO userVO)
    {
        SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(userVO);

        transaction.commit();

        session.close();
    }

    public List edit(UserVO userVO)
    {
        List list;

        SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from UserVO where id="+userVO.getId()+"");

        list = query.list();

        transaction.commit();

        session.close();

        return list;
    }
}
