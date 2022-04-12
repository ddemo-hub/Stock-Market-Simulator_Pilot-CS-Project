/**
 * classes.Market
 * This class is the super class of classes.CompanyStock, classes.CryptoCurrency, classes.ForeignCurrency and classes.Metal classes.
 * @author Emirkan Derköken
 */

import java.io.*;
import java.math.BigDecimal;

public abstract class Market implements Serializable{

    String name;
    String last;
    String buyPrice;
    String sellPrice;
    String high;  
    String low; 
    String average; 
    String percentageChange; 
    String volumeLot;
    String volumeTL;
    String time;

    BigDecimal amountBought;

    public Market (String name, String last, String buyPrice, String sellPrice, String high, String low, String average, String percentageChange, String volumeLot, String volumeTL, String time) throws ClassNotFoundException, IOException
    {
        this.name = name;
        this.last = last;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.high = high;  
        this.low = low; 
        this.average = average; 
        this.percentageChange = percentageChange; 
        this.volumeLot = volumeLot;
        this.volumeTL = volumeTL;
        this.time = time;
    }
    public Market(String name, BigDecimal amountBought) {
        this.name = name;
        this.amountBought = amountBought;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getlastValue() {
        return new BigDecimal(last);
    }

    public void setAmount (BigDecimal amount) {
        this.amountBought = amount;
    }

    public BigDecimal getAmount() {
        return amountBought;
    }

    public  BigDecimal getFinalValue() {return  new BigDecimal("5");}

    public String getVolumeTL() {
        return volumeTL;
    }

    public String getVolumeLot() {
        return volumeLot;
    }

    public String getPercentageChange() {
        return percentageChange;
    }

    public String getAverage() {
        return average;
    }

    public String getBuyPrice() {
        return new String(buyPrice);
    }

    public String getHigh() {
        return high;
    }

    public String getLast() {
        return last;
    }


    public String getLow() {
        return low;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public String getTime() {
        return time;
    }

    public BigDecimal getAmountBought() {
        return amountBought;
    }
}
