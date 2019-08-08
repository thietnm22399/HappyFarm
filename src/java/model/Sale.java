/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class Sale extends BaseModel {
    private int playerID;
    private ArrayList<SaleDetail> details = new ArrayList<>();

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public ArrayList<SaleDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<SaleDetail> details) {
        this.details = details;
    }
    
    public int totalPrice() {
        int res = 0;
        for (SaleDetail sd: details) {
            res += sd.getUnitPrice();
        }
        return res;
    }
    
    
}
