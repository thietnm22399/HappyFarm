/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Farm;
import model.Plant;

/**
 *
 * @author nguye
 */
public class FarmDAO extends BaseDAO {

    public Farm all(int playerID) throws SQLException{
        Farm farm = new Farm();
        farm.setPlayerID(playerID);
        String sql = "select plantID, x, y, dateStarted ,seasonLeft from Farm where playerID = " + playerID;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Plant plant = new PlantDAO().get(rs.getInt("plantID"));
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            Date dateStarted = rs.getDate("dateStarted");
            int seasonLeft = rs.getInt("seasonLeft");
            farm.add(plant, x, y, dateStarted, seasonLeft);
        }
        return farm;
    }

    public void add(int playerID, Plant plant, int x, int y, Date date) throws SQLException {
        String sql = "insert into Farm values (?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, playerID);
        statement.setInt(2, plant.getId());
        statement.setInt(3, x);
        statement.setInt(4, y);
        statement.setDate(5, (java.sql.Date) date);
        statement.setInt(6, plant.getSeason());
        statement.executeUpdate();
    }
    
    public void delete(int playerID, int x, int y) throws SQLException {
        String sql = "delete from Farm where playerID = ? and x = ? and y = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, playerID);
        statement.setInt(2, x);
        statement.setInt(3, y);
        statement.executeUpdate();
    }
    
    public void harvest(int playerID, int x, int y, int seasonLeft, Date now) throws SQLException {
        if (seasonLeft == 0) {
            delete(playerID, x, y);
            return;
        }
        String sql = "update Farm set seasonLeft = ?, dateStarted = ? where playerID = ? and x = ? and y = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, seasonLeft);
        st.setDate(2, now);
        st.setInt(3, playerID);
        st.setInt(4, x);
        st.setInt(5, y);
        st.executeUpdate();
    }
}
