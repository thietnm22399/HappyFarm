/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.FarmDAO;
import DAO.HarvestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
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
import support.load.StringCode;

/**
 *
 * @author nguye
 */
@WebServlet(name = "HarvestController", urlPatterns = {"/harvest"})
public class HarvestController extends HttpServlet {

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
            String plantName = request.getParameter("plantName");
            Player player = (Player) StringCode.decode(stringPlayer);
            Farm farm = (Farm) StringCode.decode(stringFarm);
            Calendar cal = Calendar.getInstance();
            Iterator<Farm.Position> i = farm.getPos().iterator();
            FarmDAO farmDAO = new FarmDAO();
            HarvestDAO harvestDAO = new HarvestDAO();
            response.getWriter().println();
//            while(i.hasNext()) {
//                Farm.Position p = i.next();
//                response.getWriter().println("halu");
//                
//            }
            while(i.hasNext()) {
                Farm.Position p = i.next();
                Date now = Date.valueOf(LocalDate.now());
                if (now.getTime() - p.getDateStarted().getTime() >= p.getPlant().getTime()*86400*1000) {
                    p.setSeasonLeft(p.getSeasonLeft() - 1);
                    farmDAO.harvest(player.getId(), p.getX(), p.getY(), p.getSeasonLeft(), now);
                    Harvest h = new Harvest();
                    harvestDAO.add(player.getId(),p.getPlant().getId());
                    if (p.getSeasonLeft() == 0) {
                        i.remove();
                    }
                }
            }
            ArrayList<Harvest> harvest = new HarvestDAO().all(player.getId());

            request.setAttribute("player", player);
            farm = farmDAO.all(player.getId());
            request.setAttribute("farm", farm);
            request.setAttribute("harvest", harvest);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HarvestController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(HarvestController.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }   catch (SQLException ex) {
            Logger.getLogger(HarvestController.class.getName()).log(Level.SEVERE, null, ex);
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
