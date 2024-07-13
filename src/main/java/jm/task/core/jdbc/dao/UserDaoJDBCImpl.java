package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection=Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS new_schema.users (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "lastname VARCHAR(50) NOT NULL," +
                    "age TINYINT NOT NULL)";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()) {
            String sql = "DROP TABLE IF EXISTS new_schema.users";
            statement.execute(sql);
                    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()) {
            String sql = "INSERT INTO new_schema.users (NAME, LASTNAME, AGE) VALUES('" + name + "', '" + lastName + "', " + age + ")";
            statement.execute(sql);
            System.out.println("User c именем - " + name + " добавлен в базу данных");
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()) {
            String sql = "delete from new_schema.users where id=1";
            statement.execute(sql);
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = Util.getConnection();
            Statement statement = conn.createStatement()) {
            String sql = "select * from new_schema.users";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                String lastName = rs.getString("LASTNAME");
                byte age = rs.getByte("AGE");
                User user = new User(name, lastName, age); // Установка id с помощью сеттера
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection()) {
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM new_schema.users";

            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
