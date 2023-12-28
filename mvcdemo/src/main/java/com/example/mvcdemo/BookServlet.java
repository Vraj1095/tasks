package com.example.mvcdemo;

import java.io.*;
import java.util.List;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/BookController")
public class BookServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("act");
        BooksDAO booksDAO = new BooksDAO();

        if(action.equals("deleteBook"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            booksDAO.deleteBook(id);
            response.sendRedirect("BookController?act=searchBook");

        }
        else if(action.equals("editBook"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            List list = booksDAO.getBookById(id);
            HttpSession session = request.getSession();
            session.setAttribute("editData", list);
            response.sendRedirect("BookEdit.jsp");
        }
        else if(action.equals("searchBook"))
        {
            List ls = booksDAO.searchBooks();
            HttpSession session = request.getSession();
            session.setAttribute("bookData", ls);
            response.sendRedirect("BookList.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        BooksDAO booksDAO = new BooksDAO();
        if(action.equals("insertBook"))
        {
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            Books books = new Books();
            books.setName(name);
            books.setAuthor(author);
            booksDAO.insertBook(books);
            response.sendRedirect("books.jsp");
        }
        else if(action.equals("editBook"))
        {
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            int id = Integer.parseInt(request.getParameter("id"));
            Books books = new Books();
            books.setName(name);
            books.setAuthor(author);
            books.setId(id);
            booksDAO.updateBooks(books);
            response.sendRedirect("BookController?act=searchBook");
        }
    }

    public void destroy() {
    }
}