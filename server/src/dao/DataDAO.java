package dao;

import data.HumanBeing;

import java.sql.SQLException;

public interface DataDAO {
    Long add(HumanBeing element, String owner) throws SQLException;

    Boolean update(HumanBeing element, Long id, String user) throws SQLException;

    Boolean remove(Long id, String user) throws SQLException;

}
