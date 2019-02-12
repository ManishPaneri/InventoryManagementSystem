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
public final class Item {

    private int id;
    private String productName;
    private int availableQuantity;
    private double unitPrice;
    private int reorderLevel;
    private double totalValue;

 public Item(int id, String productName, int availableQuantity, double unitPrice, int reorderLevel) {
        this.id = id;
        this.productName = productName;
        this.availableQuantity = availableQuantity;
        this.unitPrice = unitPrice;
        this.reorderLevel = reorderLevel;
        double totalValue = unitPrice * availableQuantity;
        this.totalValue = totalValue;
    }
 
public Item UpdateItem( int availableQuantity, double unitPrice, int reorderLevel) {
        this.availableQuantity = availableQuantity;
        this.unitPrice = unitPrice;
        this.reorderLevel = reorderLevel;
        double totalValue = unitPrice * availableQuantity;
        this.totalValue = totalValue;
        return this;
    }
 
 public Item CheckoutItem(int checkoutQuantity) {
        this.availableQuantity = availableQuantity - checkoutQuantity;
        double totalValue = unitPrice * availableQuantity;
        this.totalValue = totalValue;
        return this;
    }
 
 public int GetItemAvailableQuantity() {
        
        return this.availableQuantity;
    }
    
    public void PrintItemAvailableQuantity() {
        System.out.println(this.productName +" : "+this.availableQuantity+" : "+this.unitPrice+" : "+this.totalValue);
    }
}
