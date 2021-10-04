package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static SessionFactory sessionFactory;

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/test?serverTimezone=UTC&useSSL=false";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StandardServiceRegistryBuilder registryBuilder
                = new StandardServiceRegistryBuilder();
        registryBuilder
                .applySetting("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect")
                .applySetting("hibernate.hbm2ddl.auto", "update")
                .applySetting("hibernate.connection.url", URL)
                .applySetting("hibernate.connection.username", USER)
                .applySetting("hibernate.connection.password", PASSWORD)
                .applySetting("hibernate.show_sql", "false");
        ServiceRegistry serviceRegistry = registryBuilder.build();
        sessionFactory = new Configuration()
                .addAnnotatedClass(User.class)
                .buildSessionFactory(serviceRegistry);
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
