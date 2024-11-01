package com.library.dao;

import com.library.model.Member;
import com.library.util.LibraryUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MemberDAO {
    public void addMember(Member member) {
        try (Session session = LibraryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(member);
            transaction.commit();
        }
    }

    public Member getMemberById(int id) {
        try (Session session = LibraryUtil.getSessionFactory().openSession()) {
            return session.get(Member.class, id);
        }
    }

    public List<Member> getAllMembers() {
        try (Session session = LibraryUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Member", Member.class).list();
        }
    }

    public void updateMember(Member member) {
        try (Session session = LibraryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(member);
            transaction.commit();
        }
    }

    public void deleteMember(int id) {
        try (Session session = LibraryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Member member = session.get(Member.class, id);
            if (member != null) {
                session.delete(member);
            }
            transaction.commit();
        }
    }
}
