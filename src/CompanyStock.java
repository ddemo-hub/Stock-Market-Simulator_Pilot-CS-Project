/**
 * Company Stock
 * This class is a subclass of the market class which represents a company stock
 * @author Emirkan Derköken
 */

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;

public class CompanyStock extends Market implements Serializable{

    LocalTime time = LocalTime.now();
    
    public CompanyStock (String name, String last, String buyPrice, String sellPrice, String high, String low, String average, String percentageChange, String volumeLot, String volumeTL, String time) throws ClassNotFoundException, IOException
    {
        super( name, last,  buyPrice,  sellPrice,  high,  low,  average,  percentageChange,  volumeLot,  volumeTL,  time);
    }

    public CompanyStock(String name, BigDecimal amountBought) {
        super(name, amountBought);
    }

    @Override
    public String toString() {
        return "CompanyStock{" +
                "name='" + name + '\'' +
                '}';
    }

    public boolean isTradeable() {

        int hour = time.getHour();
        int minute = time.getMinute();

        if (hour >= 10 && hour <= 13)
            return true;
        else if (hour >= 14 && hour < 18)
            return true;
        else if (hour == 18 && (minute == 0 || minute == 8 || minute == 9 ))
            return true;
        else 
            return false;
    }

}
