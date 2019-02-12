/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

/**
 *
 * @author krazybee
 */
public class InventoryManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       System.out.println("Welcome"); 
        // RequestListenerThread to read requested floor and add to Set 
        Thread requestListenerThread = new Thread(new StockControlSystem(), 
                "StockControlSystem Thread"); 
        requestListenerThread.start(); 
            
    }
    
}
