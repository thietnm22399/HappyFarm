/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Player;

/**
 *
 * @author nguye
 */
public class PlayerDAO extends BaseDAO {
    public void add(Player player) throws SQLException{
        String sql = "insert into [Player] values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, player.getId());
        statement.setString(2, player.getUsername());
        statement.setString(3, player.getPassword());
        statement.setInt(4, player.getGold());
        statement.executeUpdate();
    }
    
    public Player get(int id) throws SQLException {
        String sql = "select id, username, [password], gold from Player where id = " + id;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery(sql);
        Player player = new Player();
        while (rs.next()) {
            player.setId(rs.getInt("id"));
            player.setUsername(rs.getString("username"));
            player.setPassword(rs.getString("password"));
            player.setGold(rs.getInt("gold"));
        }
        return player;
    }

     public Player get(String username, String password) throws SQLException {
        String sql = "select id, username, [password], gold from Player "
                + "where [username] like '%'+ ? + '%' and [Password] like '%' + ? + '%' ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        Player player = new Player();
        while (rs.next()) {
            player.setId(rs.getInt("id"));
            player.setUsername(rs.getString("username"));
            player.setPassword(rs.getString("password"));
            player.setGold(rs.getInt("gold"));
        }
        return player;
    }
     
      public Player get(String username) throws SQLException {
        String sql = "select id, username, [password], gold from Player "
                + "where [username] like '%'+ ? + '%'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        Player player = new Player();
        while (rs.next()) {
            player.setId(rs.getInt("id"));
            player.setUsername(rs.getString("username"));
            player.setPassword(rs.getString("password"));
            player.setGold(rs.getInt("gold"));
        }
        return player;
    }
     
    public int count() throws SQLException {
        String sql = "select count(*) as total from Player";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        int res = 0;
        while(rs.next()) {
            res = rs.getInt("total");
            return res;
        }
        return 0;
    }
    
    public void minus(Player player, int plantPrice) throws SQLException {
        String sql = "update Player set gold = ? where id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, player.getGold() - plantPrice);
        statement.setInt(2, player.getId());
        statement.executeUpdate();
    }
}

