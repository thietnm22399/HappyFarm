/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Sale;
import model.SaleDetail;

/**
 *
 * @author nguye
 */
public class SaleDAO extends BaseDAO {
    public void addToSale(Sale sale) throws SQLException {
        String sql = "insert into Sale values(?,?)";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, sale.getId());
        statement.setInt(2, sale.getPlayerID());
        statement.executeUpdate();
    }
    
    public void addToSaleDetail(SaleDetail saleDetail) throws SQLException {
        String sql = "insert into SaleDetail values(?,?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, saleDetail.getSaleID());
        statement.setInt(2, saleDetail.getQuantity());
        statement.setInt(3, saleDetail.getPlantID());
        statement.setInt(4, saleDetail.getUnitPrice());
        statement.executeUpdate();
        
    }
    
    public int count() throws SQLException {
        String sql = "select count(*) as total from Sale";
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
