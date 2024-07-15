package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String hibernateUsername = "root";
    private static final String hibernatePassword = "root";
    private static final String hibernateUrl = "jdbc:mysql://localhost:3306/new_schema";
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.setProperty("hibernate.connection.username", hibernateUsername);
                configuration.setProperty("hibernate.connection.password", hibernatePassword);
                configuration.setProperty("hibernate.connection.url", hibernateUrl);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                MetadataSources metadataSources = new MetadataSources(serviceRegistry);
                metadataSources.addAnnotatedClass(User.class);
                sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    static String url = "jdbc:mysql://localhost:3306";
    static String user = "root";
    static String password = "root";

    private Util() {
    }

    public static Connection getConnection()  {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




