package main_package.database;

import main_package.dao.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExist implements UserDAO {
    private static Connection connection = DatabaseConnection.getConnection();

    private UserExist() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
            "create table if not exists usersinfo (" +
                    "login varchar(50) not null," +
                    "password varchar(50) not null);"
            );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String register(String user, String password) {
        String sql = "select * from usersinfo where login = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) return "Пользователь с таким логином уже существует";

            sql = "insert into usersinfo (login, password) values (?, md5(?))";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            return "Успешно";
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Ошибка подключения к БД. Повторите запрос позднее";
        }
    }

    public static String login(String user, String password) {
        String sql = "select * from usersinfo where login = ? and password = md5(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) return "Успешно";
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Ошибка подключения к БД. Повторите запрос позднее";
        }
        return "Неверный логин или пароль";
    }

}
