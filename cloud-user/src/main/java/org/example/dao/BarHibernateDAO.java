package org.example.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class BarHibernateDAO {

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (Exception e) {
            log.error("get session error", e);
            return sessionFactory.openSession();
        }
    }

    public void executeSQL(String sql) {
        NativeQuery nativeQuery = getSession().createSQLQuery(sql);
        nativeQuery.executeUpdate();
    }

    public List<Object> select(String sql) {
        Session session = getSession();
        NativeQuery nativeQuery = session.createSQLQuery(sql);
        List list = nativeQuery.list();
        return list;
    }

}
