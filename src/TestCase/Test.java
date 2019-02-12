/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestCase;

import inventorymanagementsystem.Stock;

/**
 *
 * @author krazybee
 */
public class Test {
    
     public static void main(String[] args) {
        Stock stockManagement = Stock.getInstance(); 
        System.out.println("Add Product "+stockManagement.addStockItem("pen", "10", "12", "1"));
        
        System.out.println("Checkout Product "+stockManagement.checkoutStockItem("pen", "2"));
        
        stockManagement.fetchAllStockItems(); 

    }
}
