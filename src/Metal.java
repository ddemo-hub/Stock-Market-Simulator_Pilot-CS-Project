/**
 * classes.Metal
 * This class is a subclass of the market class which represents a metal
 * @author Emirkan Derköken
 */

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;

public class Metal extends Market {

    LocalTime time = LocalTime.now();

    public Metal (String name, String last, String buyPrice, String sellPrice, String high, String low, String average, String percentageChange, String volumeLot, String volumeTL, String time) throws ClassNotFoundException, IOException
    {
        super( name, last,  buyPrice,  sellPrice,  high,  low,  average,  percentageChange,  volumeLot,  volumeTL,  time);
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
