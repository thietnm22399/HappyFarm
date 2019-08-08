/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author nguye
 */
public class Farm extends BaseModel{

    private int playerID;

    public class Position implements java.io.Serializable  {

        Plant plant;
        int x;
        int y;
        Date dateStarted;
        int seasonLeft;

        public Position(Plant plant, int x, int y, Date dateStarted, int seasonLeft) {
            this.plant = plant;
            this.x = x;
            this.y = y;
            this.dateStarted = dateStarted;
            this.seasonLeft = seasonLeft;
        };

        public Date getDateStarted() {
            return dateStarted;
        }

        public void setDateStarted(Date dateStarted) {
            this.dateStarted = dateStarted;
        }

        public Plant getPlant() {
            return plant;
        }

        public void setPlant(Plant plant) {
            this.plant = plant;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getSeasonLeft() {
            return seasonLeft;
        }

        public void setSeasonLeft(int seasonLeft) {
            this.seasonLeft = seasonLeft;
        }

    }

     ArrayList<Position> pos = new ArrayList<>();

    public void add(Plant plant, int x, int y, Date dateStarted, int seasonLeft) {
        Position p = new Position(plant, x, y, dateStarted, seasonLeft);
        System.out.println(p);
        pos.add(p);
    }

    public int getPlayerID() {
        return playerID;
    }

    public ArrayList<Position> getPos() {
        return pos;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setPos(ArrayList<Position> pos) {
        this.pos = pos;
    }
    
    public Position getPosElement(int x, int y) {
        for (Position p : pos) {
            if (p.getX() == x && p.getY() == y) {
                 return p;
            }
        }
        return null;
    }

}
