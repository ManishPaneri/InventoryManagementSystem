/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author krazybee
 */
public class Stock {
    private static Stock StockSingleTon ;
    
    private final HashMap<String,Item> stockRepository =  new HashMap();

    private ArrayList<String> returnMessages;

    private int totalProductNumber = 0;
    
    private Stock(){}
    
    @SuppressWarnings("DoubleCheckedLocking")
    public static Stock getInstance(){
        if (StockSingleTon==null){
            synchronized(Stock .class) {
                 if (StockSingleTon==null){
                    StockSingleTon = new Stock();
                 }
                 
            }
        
        }
        return StockSingleTon;
    }
    

    public boolean addStockItem(String productName, String availableQuantity, String unitPrice, String reorderLevel) {
        ArrayList<String> returnMessages = this.validateStockItemData(productName, availableQuantity, unitPrice, reorderLevel);

        int numericalAvailableQuantity = 0;
        int numericalReorderLevel = 0;
        double numericalUnitPrice = 0;

        try {
            numericalAvailableQuantity = Integer.parseInt(availableQuantity);
            numericalReorderLevel = Integer.parseInt(reorderLevel);
        } catch (NumberFormatException exception) {
            returnMessages.add("Available quantity and re-order level may only be whole numbers!");
        }

        try {
            numericalUnitPrice = Double.parseDouble(unitPrice);
        } catch (NumberFormatException exception) {
            returnMessages.add("The unit price may only contain numbers and a decimal point!");
        }

        if(returnMessages.isEmpty()) {
            
            totalProductNumber= totalProductNumber+1;
            Item stockItem = new Item(totalProductNumber, productName,  numericalAvailableQuantity,  numericalUnitPrice,numericalReorderLevel);
            this.stockRepository.put(productName,stockItem);
             
        return true;
           
        }

        this.returnMessages = returnMessages;

        return false;
    }

    public ArrayList<String> validateStockItemData(String productName, String availableQuantity, String unitPrice, String reorderLevel) {
        ArrayList<String> returnMessages = new ArrayList<>();

        if(productName.length() < 3) {
            returnMessages.add("The name of your product must be at least 3 characters long!");
        }

        if(availableQuantity.length() == 0) {
            returnMessages.add("Please enter the available quantity!");
        }

        if(unitPrice.length() == 0) {
            returnMessages.add("Please enter a unit price!");
        }

        if(reorderLevel.length() == 0) {
            returnMessages.add("Please enter a re-order level!");
        }

        return returnMessages;
    }

    public ArrayList<String> getReturnMessages() {
        ArrayList<String> temporary = this.returnMessages;
        this.returnMessages = new ArrayList();
        return temporary;
    }

    public boolean saveChanges(String productName, String availableQuantity, String unitPrice, String reorderLevel) {
        ArrayList<String> returnMessages = this.validateStockItemData(productName, availableQuantity, unitPrice, reorderLevel);

        int numericalAvailableQuantity = 0;
        int numericalReorderLevel = 0;
        double numericalUnitPrice = 0;

        try {
            numericalAvailableQuantity = Integer.parseInt(availableQuantity);
            numericalReorderLevel = Integer.parseInt(reorderLevel);
        } catch (NumberFormatException exception) {
            returnMessages.add("Available quantity and re-order level may only be whole numbers!");
        }

        try {
            numericalUnitPrice = Double.parseDouble(unitPrice);
        } catch (NumberFormatException exception) {
            returnMessages.add("The unit price may only contain numbers and a decimal point!");
        }

        if(returnMessages.isEmpty()) {
            if (this.stockRepository.containsKey(productName)){
                Item existingitem = this.stockRepository.get(productName);
                Item stockItem = existingitem.UpdateItem(numericalAvailableQuantity, numericalUnitPrice, numericalReorderLevel);
                this.stockRepository.put(productName,stockItem);

            }else{
                totalProductNumber= totalProductNumber+1;
                Item stockItem = new Item(totalProductNumber, productName,  numericalAvailableQuantity,  numericalUnitPrice,numericalReorderLevel);
                this.stockRepository.put(productName,stockItem);
            }

            return true;
        }

        this.returnMessages = returnMessages;

        return false;
    }

    public void fetchAllStockItems()  {
        Collection<Item> AllStock= this.stockRepository.values();
        System.out.println("Name : QTY : Cost : TotalCost");
        for (Item key : AllStock) 
        {
           key.PrintItemAvailableQuantity();
        }
    }

    public boolean checkoutStockItem(String ProductName, String checkoutQuantity) {
        ArrayList<String> returnMessages = new ArrayList<>();
        if (this.stockRepository.containsKey(ProductName)){
                Item existingitem = this.stockRepository.get(ProductName);
                int  numericalcheckoutQuantity = Integer.parseInt(checkoutQuantity);
                if(existingitem.GetItemAvailableQuantity()<numericalcheckoutQuantity){
                 returnMessages.add("The product available quantity is less");
                }else{
                    Item stockItem = existingitem.CheckoutItem(numericalcheckoutQuantity);
                    this.stockRepository.put(ProductName,stockItem);
                }
            }else{
               returnMessages.add("The product is not existing in system, please check again");
            }
        
        if(returnMessages.isEmpty()) {
             return true;
        }
        else{
            for(String key : returnMessages){
                System.out.println(key);
            }
             return false;
        }
        
    }
    
    public boolean removeStockItem(String ProductName) {
        if (this.stockRepository.containsKey(ProductName)){
            this.stockRepository.remove(ProductName);
            return true;
        }
        return false;
    }

   
}
