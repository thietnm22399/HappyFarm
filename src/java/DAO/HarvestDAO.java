/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Harvest;

/**
 *
 * @author nguye
 */
public class HarvestDAO extends BaseDAO {

    public ArrayList<Harvest> all(int playerID) throws SQLException {
        ArrayList<Harvest> result = new ArrayList<>();
        String sql = "select playerID, plantID, quantity from Harvest where playerID = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, playerID);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Harvest harvest = new Harvest();
            harvest.setPlayerID(rs.getInt("playerID"));
            harvest.setPlantID(rs.getInt("plantID"));
            harvest.setQuantity(rs.getInt("quantity"));
            result.add(harvest);
        }
        return result;
    }

    public Harvest search(int playerID, int plantID) throws SQLException {
        String sql = "select playerID, plantID, quantity from Harvest where playerID = ? and plantID = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, playerID);
        statement.setInt(2, plantID);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Harvest harvest = new Harvest();
            harvest.setPlantID(rs.getInt("plantID"));
            harvest.setPlayerID(rs.getInt("playerID"));
            harvest.setQuantity(rs.getInt("quantity"));
            return harvest;
        }
        return null;
    }

    public void updateQuantity(int playerID, int plantID, int newQuantity) throws SQLException {
        String sql = "update Harvest set quantity = ? where playerID = ? and plantID = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, newQuantity);
        statement.setInt(2, playerID);
        statement.setInt(3, plantID);
        statement.executeUpdate();
    }

    public void add(int playerID, int plantID) throws SQLException {
        if (search(playerID, plantID) == null) {
            String sql = "insert into Harvest values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playerID);
            statement.setInt(2, plantID);
            statement.setInt(3, 1);
            statement.executeUpdate();
        } else {
            updateQuantity(playerID, plantID, search(playerID, plantID).getQuantity() + 1);
        }
    }

    public void delete(int playerID, int plantID) throws SQLException {
        String sql = "delete from Harvest where playerID = ? and plantID = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, playerID);
        statement.setInt(2, plantID);
        statement.executeUpdate();
    }

}
