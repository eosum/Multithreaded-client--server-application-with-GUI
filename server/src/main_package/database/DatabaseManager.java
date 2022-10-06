package main_package.database;

import main_package.dao.DataDAO;
import main_package.data.HumanBeing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager implements DataDAO {
    private final Connection connection = DatabaseConnection.getConnection();
    public DatabaseManager() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
            "create table if not exists character (" +
                "id serial primary key," +
                "name varchar(50) not null," +
                "coordinate_x float not null,"  +
                "coordinate_y float not null," +
                "real_hero boolean not null," +
                "has_toothpick boolean," +
                "impact_speed integer," +
                "soundtrack varchar(50)," +
                "minutes_of_waiting integer," +
                "weapon_type varchar(50)," +
                "car varchar(50) not null," +
                "owner varchar(50) not null);"
            );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getFromDB() throws SQLException {
        String sql = "select * from character";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public Long add(HumanBeing element, String owner) throws SQLException {
        String sql = "insert into character (name, coordinate_x, coordinate_y, real_hero, has_toothpick, impact_speed, soundtrack, minutes_of_waiting, weapon_type, car, owner) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning id";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, element.getName());
        preparedStatement.setDouble(2, element.getCoordinateX());
        preparedStatement.setDouble(3, element.getCoordinateY());
        preparedStatement.setBoolean(4, element.getRealHero());
        preparedStatement.setBoolean(5, element.getHasToothpick());
        preparedStatement.setLong(6, element.getImpactSpeed());
        preparedStatement.setString(7, element.getSoundtrackName());
        preparedStatement.setInt(8, element.getMinutesOfWaiting());
        preparedStatement.setString(9, element.getWeaponType());
        preparedStatement.setString(10, element.getCar().toString());
        preparedStatement.setString(11, owner);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        return result.getLong("id");

    }

    public Boolean update(HumanBeing element, Long id, String user) throws SQLException{
        String sql = "update character set (name, coordinate_x, coordinate_y, real_hero, has_toothpick, impact_speed, soundtrack, minutes_of_waiting, weapon_type, car) = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) where id = ? and owner = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, element.getName());
        preparedStatement.setDouble(2, element.getCoordinateX());
        preparedStatement.setDouble(3, element.getCoordinateY());
        preparedStatement.setBoolean(4, element.getRealHero());
        preparedStatement.setBoolean(5, element.getHasToothpick());
        preparedStatement.setLong(6, element.getImpactSpeed());
        preparedStatement.setString(7, element.getSoundtrackName());
        preparedStatement.setInt(8, element.getMinutesOfWaiting());
        preparedStatement.setString(9, element.getWeaponType());
        preparedStatement.setString(10, element.getCar().toString());
        preparedStatement.setLong(11, id);
        preparedStatement.setString(12, user);
        return preparedStatement.executeUpdate() > 0;
    }
    public Boolean remove(Long id, String user) throws SQLException {
        String sql = "delete from character where id = ? and owner = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, user);
        return preparedStatement.executeUpdate() == 1;
    }

    public Boolean removeAll(String user) throws SQLException {
        String sql = "delete from character where owner = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user);
        return preparedStatement.executeUpdate() > 0;
    }

}
