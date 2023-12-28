package com.example.mvcdemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {

    public void insertBook(Books books)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "root");
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into book(name,author) values('"+books.getName()+"','"+books.getAuthor()+"')");
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List searchBooks()
    {
        List ls = new ArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from book");
            while(rs.next())
            {
                Books books = new Books();
                books.setId(rs.getInt("id"));
                books.setAuthor(rs.getString("author"));
                books.setName(rs.getString("name"));
                ls.add(books);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ls;
    }

    public void deleteBook(int id)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "root");
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from book where id='"+id+"'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List getBookById(int id)
    {
        List ls = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from book where id='"+id+"'");
            while(rs.next())
            {
                Books books = new Books();
                books.setId(rs.getInt("id"));
                books.setAuthor(rs.getString("author"));
                books.setName(rs.getString("name"));
                ls.add(books);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ls;
    }

    public void updateBooks(Books books)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "root");
            Statement statement = connection.createStatement();
            statement.executeUpdate("update book set author='"+books.getAuthor()+"',name='"+books.getName()+"' where id='"+books.getId()+"'");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
