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
import model.Plant;

/**
 *
 * @author nguye
 */
public class PlantDAO extends BaseDAO {

    public ArrayList<Plant> all() throws SQLException {
        ArrayList<Plant> result = new ArrayList<Plant>();
        String sql = "select id, plantName, plantPrice, fruitPrice, season, [time] from Plant";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Plant plant = new Plant();
            plant.setFruitPrice(rs.getInt("fruitPrice"));
            plant.setId(rs.getInt("id"));
            plant.setPlantName(rs.getString("plantName"));
            plant.setPlantPrice(rs.getInt("plantPrice"));
            plant.setSeason(rs.getInt("season"));
            plant.setTime(rs.getInt("time"));
            result.add(plant);
        }
        return result;
    }

    public Plant get(int id) throws SQLException {
        String sql = "select id, plantName, plantPrice, fruitPrice, season, [time] from Plant where id = " + id;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        Plant plant = new Plant();
        while (rs.next()) {
            plant.setFruitPrice(rs.getInt("fruitPrice"));
            plant.setId(rs.getInt("id"));
            plant.setPlantName(rs.getString("plantName"));
            plant.setPlantPrice(rs.getInt("plantPrice"));
            plant.setSeason(rs.getInt("season"));
            plant.setTime(rs.getInt("time"));

        }
        return plant;
    }

    public ArrayList<Plant> search(String name) throws SQLException {
        ArrayList<Plant> result = new ArrayList<>();
        String sql = "select id, plantName, plantPrice, fruitPrice, season, [time] from Plant where plantName like '%'+ ? + '%' ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Plant plant = new Plant();
            plant.setFruitPrice(rs.getInt("fruitPrice"));
            plant.setId(rs.getInt("id"));
            plant.setPlantName(rs.getString("plantName"));
            plant.setPlantPrice(rs.getInt("plantPrice"));
            plant.setSeason(rs.getInt("season"));
            plant.setTime(rs.getInt("time"));
            result.add(plant);
        }
        return result;
    }
    
    public void updatePrice(int id, int newPrice) throws SQLException {
        String sql = "update Plant set fruitPrice = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, newPrice);
        statement.setInt(2, id);
        statement.executeUpdate();
    }

    public int count() throws SQLException {
        String sql = "select count(*) as total from Plant";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            int total;
            total = rs.getInt("total");
            return total;
        }
        return 0;
    }
}
