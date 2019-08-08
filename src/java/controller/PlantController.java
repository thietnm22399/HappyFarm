/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.FarmDAO;
import DAO.PlantDAO;
import DAO.PlayerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
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
@WebServlet(name = "PlantController", urlPatterns = {"/plant"})
public class PlantController extends HttpServlet {

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
            String plantName = request.getParameter("plantName");
            String raw_x = request.getParameter("x");
            String raw_y = request.getParameter("y");
            int x, y;
            if (raw_x == null || raw_x == "") {
                x = -1;
            }
            if (raw_y == null || raw_y == "") {
                y = -1;
            }
            x = Integer.parseInt(raw_x);
            y = Integer.parseInt(raw_y);
            Player player = (Player) StringCode.decode(stringPlayer);
            Farm farm = (Farm) StringCode.decode(stringFarm);
            ArrayList<Harvest> harvest = (ArrayList) StringCode.decode(stringHarvest);

            Date now = new Date(System.currentTimeMillis());
            if ((x >= 1 && x <= 4) && (y >= 1 && y <= 8)) {
                if (farm.getPosElement(x, y) == null) {
                    ArrayList<Plant> plants = new PlantDAO().search(plantName.trim());
                    Plant plant = plants.get(0);
                    if (player.getGold() < plant.getPlantPrice()) {
                        request.setAttribute("invalid", "You do not have enough gold");
                        request.setAttribute("player", player);
                        request.setAttribute("farm", farm);
                        request.setAttribute("harvest", harvest);
                        request.getRequestDispatcher("main.jsp").forward(request, response);
                    }
                    farm.add(plant, x, y, now, plant.getSeason());
                    player.setGold(player.getGold() - plant.getPlantPrice());
                    new PlayerDAO().minus(player, plant.getPlantPrice());
                    new FarmDAO().add(player.getId(), plant, x, y, now);
                }
            }

            request.setAttribute("player", player);
            request.setAttribute("farm", farm);
            request.setAttribute("harvest", harvest);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlantController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlantController.class.getName()).log(Level.SEVERE, null, ex);
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
