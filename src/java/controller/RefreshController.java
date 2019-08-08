/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.HarvestDAO;
import DAO.PlantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Farm;
import model.Harvest;
import model.Plant;
import model.Player;
import support.load.StringCode;

/**
 *
 * @author nguye
 */
@WebServlet(name = "RefreshController", urlPatterns = {"/refresh"})
public class RefreshController extends HttpServlet {

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
            Player player = (Player) StringCode.decode(stringPlayer);
            Farm farm = (Farm) StringCode.decode(stringFarm);
            PlantDAO plantDAO = new PlantDAO();
            ArrayList<Plant> plants = plantDAO.all();
            for (Plant plant : plants) {
                int newPrice = plant.getFruitPrice() + new Random().nextInt(3 + 1 + 3) - 3;
                plantDAO.updatePrice(plant.getId(), newPrice);
            }
            ArrayList<Harvest> harvest = new HarvestDAO().all(player.getId());
            request.setAttribute("player", player);
            request.setAttribute("farm", farm);
            request.setAttribute("harvest", harvest);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RefreshController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RefreshController.class.getName()).log(Level.SEVERE, null, ex);
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
