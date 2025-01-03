/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HC
 */
class FundingTransaction {
    private double amount;
    private String paymentMethod;
    private String date;
    

   public FundingTransaction(double amountDouble, String paymentMethod) {
       this.amount=amount;
       this.paymentMethod=paymentMethod;
       this.date=date;
    }

    String getDate() { 
        return date;
    }

    public double getAmount() {
        return amount;
    }

     public String getPaymentMethod() {
       return paymentMethod;
    }

    
    
}
