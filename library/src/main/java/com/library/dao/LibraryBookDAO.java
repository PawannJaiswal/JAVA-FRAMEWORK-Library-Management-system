package com.library.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.library.model.LibraryBook;
import com.library.util.LibraryUtil;

import java.util.List;

public class LibraryBookDAO {

    public void addBook(LibraryBook book) {
        Session session = LibraryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }

    public LibraryBook getBook(int id) {
        Session session = LibraryUtil.getSessionFactory().openSession();
        LibraryBook book = session.get(LibraryBook.class, id);
        session.close();
        return book;
    }

    public void updateBook(LibraryBook book) {
        Session session = LibraryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(book);
        transaction.commit();
        session.close();
    }

    public void deleteBook(int id) {
        Session session = LibraryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        LibraryBook book = session.get(LibraryBook.class, id);
        if (book != null) {
            session.delete(book);
        }
        transaction.commit();
        session.close();
    }

    public List<LibraryBook> getAllBooks() {
        Session session = LibraryUtil.getSessionFactory().openSession();
        List<LibraryBook> books = session.createQuery("from LibraryBook", LibraryBook.class).list();
        session.close();
        return books;
    }
}
