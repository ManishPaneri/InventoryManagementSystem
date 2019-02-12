/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/**
 *
 * @author krazybee
 */
public class StockControlSystem  implements Runnable { 

    
    @Override 
    public void run() { 

        while (true) { 
            String ProcessStr = null; 
            try { 
                // Read input from console 
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); 
                ProcessStr = bufferedReader.readLine();
             } catch (IOException e) { 
                e.printStackTrace(); 
            } 
            String strArray[] = ProcessStr.split(" ");
//            System.out.println(strArray);
            
            if (strArray[0].equals("add") && strArray.length==5) { 
                System.out.println("User Pressed : " + ProcessStr); 
                Stock stockManagement = Stock.getInstance(); 
                System.out.println(stockManagement.addStockItem(strArray[1], strArray[2], strArray[3], strArray[4])); 
                
            }else if (strArray[0].equals("checkout") && strArray.length==3) { 
                System.out.println("User Pressed : " + ProcessStr); 
                Stock stockManagement = Stock.getInstance(); 
               System.out.println(stockManagement.checkoutStockItem(strArray[1], strArray[2])); 
               
            }else if (strArray[0].equals("fetch") && strArray.length==1) { 
                System.out.println("User Pressed : " + ProcessStr); 
                Stock stockManagement = Stock.getInstance(); 
                stockManagement.fetchAllStockItems(); 
                
            } else if (strArray[0].equals("remove") && strArray.length==2) { 
                System.out.println("User Pressed : " + ProcessStr); 
                Stock stockManagement = Stock.getInstance(); 
                stockManagement.removeStockItem(strArray[1]); 
                
            } else { 
                System.out.println("Process Request Invalid : " + ProcessStr); 
            } 
        } 
    } 
} 


