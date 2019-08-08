/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.HarvestDAO;
import DAO.PlantDAO;
import DAO.PlayerDAO;
import DAO.SaleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Farm;
import model.Harvest;
import model.Player;
import model.Sale;
import model.SaleDetail;
import support.load.StringCode;

/**
 *
 * @author nguye
 */
@WebServlet(name = "SellController", urlPatterns = {"/sell"})
public class SellController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String stringPlayer = request.getParameter("player");
            String stringFarm = request.getParameter("farm");
            String stringHarvest = request.getParameter("harvest");
            Player player = (Player) StringCode.decode(stringPlayer);
            Farm farm = (Farm) StringCode.decode(stringFarm);
            ArrayList<Harvest> harvest = (ArrayList) StringCode.decode(stringHarvest);
            
            PlantDAO plantDAO = new PlantDAO();
            SaleDAO saleDAO = new SaleDAO();
            PlayerDAO playerDAO = new PlayerDAO();
            HarvestDAO harvestDAO = new HarvestDAO();
            
            Sale sale = new Sale();
            sale.setId(saleDAO.count() + 1);
            sale.setPlayerID(player.getId());
            saleDAO.addToSale(sale);
            for (Harvest h : harvest) {
                SaleDetail detail = new SaleDetail();
                detail.setPlantID(h.getPlantID());
                detail.setQuantity(h.getQuantity());
                detail.setSaleID(sale.getId());
                detail.setUnitPrice(plantDAO.get(h.getPlantID()).getFruitPrice());
                sale.getDetails().add(detail);
                saleDAO.addToSaleDetail(detail);
                harvestDAO.delete(h.getPlayerID(), h.getPlantID());
            }
            player.setGold(player.getGold() + sale.totalPrice());
            playerDAO.minus(player, sale.totalPrice() * (-1));
            harvest = harvestDAO.all(player.getId());
            request.setAttribute("player", player);
            request.setAttribute("farm", farm);
            request.setAttribute("harvest", harvest);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
