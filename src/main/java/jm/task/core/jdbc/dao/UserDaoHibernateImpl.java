package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSession();
            transaction = session.getTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User(" +
                    "id TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(20) NOT NULL," +
                    "lastName TINYTEXT NOT NULL," +
                    "age BIGINT NOT NULL" +
                    ")").executeUpdate();
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (HibernateException he) {
                he.printStackTrace();
            }
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSession();
            transaction = session.getTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (HibernateException he) {
                he.printStackTrace();
            }
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSession();
            transaction = session.getTransaction();
            session.save(new User(name, lastName, age));
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (HibernateException he) {
                he.printStackTrace();
            }
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSession();
            transaction = session.getTransaction();
            session.createQuery("DELETE User WHERE id = :id")
                    .setLong("id", id)
                    .executeUpdate();
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (HibernateException he) {
                he.printStackTrace();
            }
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSession();
            transaction = session.getTransaction();
            list = session.createCriteria(User.class).list();
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (HibernateException he) {
                he.printStackTrace();
            }
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSession();
            transaction = session.getTransaction();
            Query query = session.createQuery("DELETE FROM " + User.class.getSimpleName());
            query.executeUpdate();
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                if (transaction != null) transaction.rollback();
            } catch (HibernateException he) {
                he.printStackTrace();
            }
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }
}
