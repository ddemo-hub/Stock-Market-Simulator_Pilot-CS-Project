/**
 * This class is the user class of a market simulator. It simulates a user of the application.
 * @author Tuğrul Sağlam, Emirkan Derköken
 */



import java.math.BigDecimal;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.lang.Object;
import java.io.Serializable;

public class User implements Serializable {


    String userName;
    String realName;
    String eMail;
    String password;
    private static final long serialVersionUID = 4390482518182625971L;

    BigDecimal balance;

    ArrayList<Market>          favorites;
    ArrayList<CompanyStock>   ownedStocks;

    ArrayList<Metal>           ownedMetals;
    ArrayList<CryptoCurrency>  ownedCrypto;
    ArrayList<ForeignCurrency> ownedCurrency;


    public User () throws ClassNotFoundException, IOException {
        Load();
    }

    public User (String userName, String realName, String eMail, String password, BigDecimal balance,
                 ArrayList<Market> favorites, ArrayList<CompanyStock> ownedStocks, ArrayList<Metal> ownedMetals,
                 ArrayList<CryptoCurrency> ownedCrypto, ArrayList<ForeignCurrency> ownedCurrency)
    {
        this.userName = userName;
        this.realName = realName;
        this.eMail = eMail;
        this.password = password;

        this.balance = balance;

        this.favorites = favorites;
        this.ownedStocks = ownedStocks;
        this.ownedMetals = ownedMetals;
        this.ownedCrypto = ownedCrypto;
        this.ownedCurrency = ownedCurrency;
    }

    public void Save() throws IOException {

        FileOutputStream fileOut = new FileOutputStream(new File("C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\user_Info.txt"));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

        ArrayList<Object> obj = new ArrayList<>();
        obj.add(userName);
        obj.add(realName);
        obj.add(eMail);
        obj.add(password);
        obj.add(balance);
        obj.add(favorites);
        obj.add(ownedStocks);
        obj.add(ownedMetals);
        obj.add(ownedCrypto);
        obj.add(ownedCurrency);

        objectOut.writeObject(obj);

        fileOut.close();
        objectOut.close();
    }

    public void Load() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\user_Info.txt");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);

        ArrayList<Object> data = (ArrayList<Object>) objectIn.readObject();

        this.userName = (String) data.get(0);
        this.realName = (String) data.get(1);
        this.eMail = (String) data.get(2);
        this.password = (String) data.get(3);
        this.balance = (BigDecimal) data.get(4);
        this.favorites = (ArrayList<Market>) data.get(5);
        this.ownedStocks = (ArrayList<CompanyStock>) data.get(6);
        this.ownedMetals = (ArrayList<Metal>) data.get(7);
        this.ownedCrypto = (ArrayList<CryptoCurrency>) data.get(8);
        this.ownedCurrency = (ArrayList<ForeignCurrency>) data.get(9);

        fileIn.close();
        objectIn.close();
    }

    public String buyStock (CompanyStock currentCompany, BigDecimal amountBought) throws IOException, ClassNotFoundException {

        BigDecimal price;
        price = amountBought.multiply(new BigDecimal(currentCompany.getBuyPrice()));

        if(balance.compareTo(price) != -1)
        {
            boolean present = false;

            if(ownedStocks.size() != 0)
            {
                for( CompanyStock i : ownedStocks) {
                    if(i.getName().equals(currentCompany.getName())) {
                        present = true;
                        System.out.println(i);
                    }
                    else {
                        present = false;

                    }

                }

                if(present)
                {
                    int index = 0;
                    BigDecimal newBalance = balance.subtract(price);
                    balance = newBalance;
                    for( int i = 0; i < ownedStocks.size(); i++) {
                        if ((ownedStocks.get(i).getName()).equals(currentCompany.getName())){
                            index = i;
                        }
                    }

                    ownedStocks.get(index).setAmount(ownedStocks.get(index).getAmount().add(amountBought));
                    Save();
                    return "Successfully bought";

                }
                else
                {
                    ownedStocks.add(currentCompany);
                    BigDecimal newBalance = balance.subtract(price);
                    balance = newBalance;
                    ownedStocks.get(ownedStocks.indexOf(currentCompany)).setAmount(amountBought);
                    Save();
                    return "Successfully added";

                }
            }
            else
            {
                System.out.println("Size is 0");
                ownedStocks.add(currentCompany);
                BigDecimal newBalance = balance.subtract(price);
                balance = newBalance;
                ownedStocks.get(ownedStocks.indexOf(currentCompany)).setAmount(amountBought);
                Save();
                return "First stock added";
            }


        }
        else
        {
            Save();
            System.out.println("no money");
            return "no money";
        }
    }

    public String buyCrypto (CryptoCurrency crypto, BigDecimal amountBought) throws IOException, ClassNotFoundException {
        BigDecimal price;
        price = amountBought.multiply(new BigDecimal(crypto.getBuyPrice()));

        if(balance.compareTo(price) != -1)
        {
            boolean present = false;
            if(ownedCrypto.size() != 0)
            {
                for( CryptoCurrency i : ownedCrypto) {
                    if(i.getName().equals(crypto.getName())) {
                        present = true;
                        System.out.println(i);
                    }
                    else {
                        present = false;
                        System.out.println(i + "bız");
                    }
                }

                if(present)
                {
                    int index = 0;
                    BigDecimal newBalance = balance.subtract(price);
                    balance = newBalance;
                    for( int i = 0; i < ownedCrypto.size(); i++) {
                        if ((ownedCrypto.get(i).getName()).equals(crypto.getName())){
                            index = i;
                        }
                    }
                    ownedCrypto.get(index).setAmount(ownedCrypto.get(index).getAmount().add(amountBought));
                    Save();
                    return "Successfully bought";
                }
                else
                {
                    ownedCrypto.add(crypto);
                    BigDecimal newBalance = balance.subtract(price);
                    balance = newBalance;
                    ownedCrypto.get(ownedCrypto.indexOf(crypto)).setAmount(amountBought);
                    Save();
                    return "Successfully added";
                }
            }
            else
            {
                System.out.println("Size is 0");
                ownedCrypto.add(crypto);
                BigDecimal newBalance = balance.subtract(price);
                balance = newBalance;
                ownedCrypto.get(ownedCrypto.indexOf(crypto)).setAmount(amountBought);
                Save();
                return "First crypto added";
            }
        }
        else
        {
            Save();
            System.out.println("no money");
            return "no money";
        }
    }

    public String buyMetal (Metal metal, BigDecimal amountBought) throws IOException, ClassNotFoundException {
        BigDecimal price;
        price = amountBought.multiply(new BigDecimal(metal.getBuyPrice()));

        if(balance.compareTo(price) != -1)
        {
            boolean present = false;
            if(ownedMetals.size() != 0)
            {
                for( Metal i : ownedMetals) {
                    if(i.getName().equals(metal.getName())) {
                        present = true;
                        System.out.println(i);
                    }
                    else {
                        present = false;
                        System.out.println(i + "bız");
                    }
                }

                if(present)
                {
                    int index = 0;
                    BigDecimal newBalance = balance.subtract(price);
                    balance = newBalance;
                    for( int i = 0; i < ownedMetals.size(); i++) {
                        if ((ownedMetals.get(i).getName()).equals(metal.getName())){
                            index = i;
                        }
                    }
                    ownedMetals.get(index).setAmount(ownedMetals.get(index).getAmount().add(amountBought));
                    Save();
                    return "Successfully bought";
                }
                else
                {
                    ownedMetals.add(metal);
                    BigDecimal newBalance = balance.subtract(price);
                    balance = newBalance;
                    ownedMetals.get(ownedMetals.indexOf(metal)).setAmount(amountBought);
                    Save();
                    return "Successfully added";
                }
            }
            else
            {
                System.out.println("Size is 0");
                ownedMetals.add(metal);
                BigDecimal newBalance = balance.subtract(price);
                balance = newBalance;
                ownedMetals.get(ownedMetals.indexOf(metal)).setAmount(amountBought);
                Save();
                return "First metal added";
            }
        }
        else
        {
            Save();
            System.out.println("no money");
            return "no money";
        }
    }

    public String buyCurrency (ForeignCurrency currency, BigDecimal amountBought) throws IOException, ClassNotFoundException {
        BigDecimal price;
        price = amountBought.multiply(new BigDecimal(currency.getBuyPrice()));

        if(balance.compareTo(price) != -1)
        {
            boolean present = false;
            if(ownedCurrency.size() != 0)
            {
                for( ForeignCurrency i : ownedCurrency) {
                    if(i.getName().equals(currency.getName())) {
                        present = true;
                        System.out.println(i);
                    }
                    else {
                        present = false;
                        System.out.println(i + "bız");
                    }
                }

                if(present)
                {
                    int index = 0;
                    BigDecimal newBalance = balance.subtract(price);
                    balance = newBalance;
                    for( int i = 0; i < ownedCurrency.size(); i++) {
                        if ((ownedCurrency.get(i).getName()).equals(currency.getName())){
                            index = i;
                        }
                    }
                    ownedCurrency.get(index).setAmount(ownedCurrency.get(index).getAmount().add(amountBought));
                    Save();
                    return "Successfully bought";
                }
                else
                {
                    ownedCurrency.add(currency);
                    BigDecimal newBalance = balance.subtract(price);
                    balance = newBalance;
                    ownedCurrency.get(ownedCurrency.indexOf(currency)).setAmount(amountBought);
                    Save();
                    return "Successfully added";
                }
            }
            else
            {
                System.out.println("Size is 0");
                ownedCurrency.add(currency);
                BigDecimal newBalance = balance.subtract(price);
                balance = newBalance;
                ownedCurrency.get(ownedCurrency.indexOf(currency)).setAmount(amountBought);
                Save();
                return "First currency added";
            }
        }
        else
        {
            Save();
            System.out.println("no money");
            return "no money";
        }
    }

    public String sellStock (CompanyStock currentCompany, BigDecimal amountSold) throws IOException, ClassNotFoundException
    {
        BigDecimal price;
        price = amountSold.multiply(new BigDecimal(currentCompany.getSellPrice()));

        boolean present = false;

        if (ownedStocks.size() != 0)
        {
            for (CompanyStock i : ownedStocks)
            {
                if (i.getName().equals(currentCompany.getName())) {
                    present = true;
                    System.out.println(i + "SELL");
                }
                else
                {
                    present = false;
                    System.out.println(i + "bızSELL");
                }
            }
            if (present) {
                int index = 0;

                for (int i = 0; i < ownedStocks.size(); i++) {
                    if ((ownedStocks.get(i).getName()).equals(currentCompany.getName()))
                    {
                        index = i;
                    }
                }
                if(new BigDecimal(String.valueOf(ownedStocks.get(index).getAmount())).compareTo(amountSold) == -1)
                    return "You don't have enough.";
                else if(new BigDecimal(String.valueOf(ownedStocks.get(index).getAmount())).compareTo(amountSold) == 0)
                {
                    BigDecimal newBalance = balance.add(price);
                    balance = newBalance;
                    ownedStocks.remove(index);
                    Save();
                    return "Sold all.";
                }
                else
                {
                    BigDecimal newBalance = balance.add(price);
                    balance = newBalance;
                    ownedStocks.get(index).setAmount(ownedStocks.get(index).getAmount().subtract(amountSold));
                    Save();
                    return "Successfully sold.";
                }
            }
            else
            {
                Save();
                return "You don't have this.";
            }
        }
        else
        {
            Save();
            return "You don't have any commodities.";
        }
    }

    public String sellCrypto (CryptoCurrency crypto, BigDecimal amountSold) throws IOException, ClassNotFoundException
    {
        BigDecimal price;
        price = amountSold.multiply(new BigDecimal(crypto.getSellPrice()));

        boolean present = false;

        if (ownedCrypto.size() != 0)
        {
            for (CryptoCurrency i : ownedCrypto)
            {
                if (i.getName().equals(crypto.getName())) {
                    present = true;
                    System.out.println(i + "SELL");
                }
                else
                {
                    present = false;
                    System.out.println(i + "bızSELL");
                }
            }
            if (present) {
                int index = 0;

                for (int i = 0; i < ownedCrypto.size(); i++) {
                    if ((ownedCrypto.get(i).getName()).equals(crypto.getName()))
                    {
                        index = i;
                    }
                }
                if(new BigDecimal(String.valueOf(ownedCrypto.get(index).getAmount())).compareTo(amountSold) == -1)
                    return "You don't have enough.";
                else if(new BigDecimal(String.valueOf(ownedCrypto.get(index).getAmount())).compareTo(amountSold) == 0)
                {
                    BigDecimal newBalance = balance.add(price);
                    balance = newBalance;
                    ownedCrypto.remove(index);
                    Save();
                    return "Sold all.";
                }
                else
                {
                    BigDecimal newBalance = balance.add(price);
                    balance = newBalance;
                    ownedCrypto.get(index).setAmount(ownedCrypto.get(index).getAmount().subtract(amountSold));
                    Save();
                    return "Successfully sold.";
                }
            }
            else
            {
                Save();
                return "You don't have this.";
            }
        }
        else
        {
            Save();
            return "You don't have any commodities.";
        }
    }

    public String sellMetal (Metal metal, BigDecimal amountSold) throws IOException, ClassNotFoundException
    {
        BigDecimal price;
        price = amountSold.multiply(new BigDecimal(metal.getSellPrice()));

        boolean present = false;

        if (ownedMetals.size() != 0)
        {
            for (Metal i : ownedMetals)
            {
                if (i.getName().equals(metal.getName())) {
                    present = true;
                    System.out.println(i + "SELL");
                }
                else
                {
                    present = false;
                    System.out.println(i + "bızSELL");
                }
            }
            if (present) {
                int index = 0;

                for (int i = 0; i < ownedMetals.size(); i++) {
                    if ((ownedMetals.get(i).getName()).equals(metal.getName()))
                    {
                        index = i;
                    }
                }
                if(new BigDecimal(String.valueOf(ownedMetals.get(index).getAmount())).compareTo(amountSold) == -1)
                    return "You don't have enough.";
                else if(new BigDecimal(String.valueOf(ownedMetals.get(index).getAmount())).compareTo(amountSold) == 0)
                {
                    BigDecimal newBalance = balance.add(price);
                    balance = newBalance;
                    ownedMetals.remove(index);
                    Save();
                    return "Sold all.";
                }
                else
                {
                    BigDecimal newBalance = balance.add(price);
                    balance = newBalance;
                    ownedMetals.get(index).setAmount(ownedMetals.get(index).getAmount().subtract(amountSold));
                    Save();
                    return "Successfully sold.";
                }
            }
            else
            {
                Save();
                return "You don't have this.";
            }
        }
        else
        {
            Save();
            return "You don't have any commodities.";
        }
    }

    public String sellCurrency (ForeignCurrency currency, BigDecimal amountSold) throws IOException, ClassNotFoundException
    {
        BigDecimal price;
        price = amountSold.multiply(new BigDecimal(currency.getSellPrice()));

        boolean present = false;

        if (ownedCurrency.size() != 0)
        {
            for (ForeignCurrency i : ownedCurrency)
            {
                if (i.getName().equals(currency.getName())) {
                    present = true;
                    System.out.println(i + "SELL");
                }
                else
                {
                    present = false;
                    System.out.println(i + "bızSELL");
                }
            }
            if (present) {
                int index = 0;

                for (int i = 0; i < ownedCurrency.size(); i++) {
                    if ((ownedCurrency.get(i).getName()).equals(currency.getName()))
                    {
                        index = i;
                    }
                }
                if(new BigDecimal(String.valueOf(ownedCurrency.get(index).getAmount())).compareTo(amountSold) == -1)
                    return "You don't have enough.";
                else if(new BigDecimal(String.valueOf(ownedCurrency.get(index).getAmount())).compareTo(amountSold) == 0)
                {
                    BigDecimal newBalance = balance.add(price);
                    balance = newBalance;
                    ownedCurrency.remove(index);
                    Save();
                    return "Sold all.";
                }
                else
                {
                    BigDecimal newBalance = balance.add(price);
                    balance = newBalance;
                    ownedCurrency.get(index).setAmount(ownedCurrency.get(index).getAmount().subtract(amountSold));
                    Save();
                    return "Successfully sold.";
                }
            }
            else
            {
                Save();
                return "You don't have this.";
            }
        }
        else
        {
            Save();
            return "You don't have any commodities.";
        }
    }


    private ArrayList<CompanyStock> getCompanies() throws IOException, ClassNotFoundException {
        ArrayList<CompanyStock> companies = new ArrayList<>();
        File file = new File("C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\bist100.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String[] details = sc.nextLine().split(" ");
            companies.add(new CompanyStock(details[0], details[1], details[2],details[3], details[4], details[5],details[6], details[7], details[8],details[9], details[10]));
        }
        return companies;
    }

    protected void addFavorites(Market favoriteCommodity)
    {
        favorites.add(favoriteCommodity);
    }

    protected String getUserName()
    {
        return userName;
    }

    protected String getRealName()
    {
        return realName;
    }

    protected String getMail()
    {
        return eMail;
    }

    protected String getPassword()
    {
        return password;
    }

    protected BigDecimal getBalance()
    {
        return balance;
    }

    protected void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public void sendMessage () {
        //To-Do
    }
}