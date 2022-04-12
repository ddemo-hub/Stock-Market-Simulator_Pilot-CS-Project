import java.io.IOException;
import java.io.Serializable;

public class CryptoCurrency extends Market  {

    public CryptoCurrency (String name, String last, String buyPrice, String sellPrice, String high, String low, String average, String percentageChange, String volumeLot, String volumeTL, String time) throws ClassNotFoundException, IOException
    {
        super( name, last,  buyPrice,  sellPrice,  high,  low,  average,  percentageChange,  volumeLot,  volumeTL,  time);
    }
}
