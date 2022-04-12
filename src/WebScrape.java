/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/**
 *
 * @author BARIŞ YILDIRIM
 */
public class WebScrape {
    String[][] marketValue;
    String[][] crypto;
    String[][] metals;
    String[][] currency;
    
    
    public WebScrape() {
        marketValue = new String[97][11];
        crypto = new String[20][7];
        metals = new String[15][5];
        currency = new String[12][5];
    }
    
    public void printData(){
        for (int i = 0; i < marketValue.length; i++) {
            System.out.println(marketValue[i][0] + " " + marketValue[i][1] + " " + marketValue[i][2] + " " + marketValue[i][3]
            + " " + marketValue[i][4] + " " + marketValue[i][5] + " " + marketValue[i][6] + " " + marketValue[i][7] + " " + marketValue[i][8]+ " " + marketValue[i][9]+ " " + marketValue[i][10]);
        }
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0; i < crypto.length; i++) {
            System.out.println(crypto[i][0] + " " + crypto[i][1] + " " + crypto[i][2] + " " + crypto[i][3]
            + " " + crypto[i][4] + " " + crypto[i][5] + " " + crypto[i][6]);
        }
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0; i < metals.length; i++) {
            System.out.println(metals[i][0] + " " + metals[i][1] + " " + metals[i][2] + " " + metals[i][3] + " " + metals[i][4]);
        }
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0; i < currency.length; i++) {
            System.out.println(currency[i][0] + " " + currency[i][1] + " " + currency[i][2] + " " + currency[i][3] + " " + currency[i][4]);
        }
    }
    
    public String[][] setDataBist(){
        try {

            final String url = "https://bigpara.hurriyet.com.tr/borsa/canli-borsa/";
            final Document document = Jsoup.connect(url).timeout(6000).get();
            int counter = 0;
            
            for (Element row : document.select("div#sortable.tBody.ui-unsortable ul")) {
                
                final String thic = row.select("li.cell064.tal.arrow a").text();
                final String son = row.select("li.cell048.node-c").text().replace(".","").replace(",", ".");
                final String alış = row.select("li.cell048.node-f").text().replace(".","").replace(",", ".");
                final String satış = row.select("li.cell048.node-g").text().replace(".","").replace(",", ".");
                final String yüksek = row.select("li.cell048.node-h").text().replace(".","").replace(",", ".");
                final String düşük = row.select("li.cell048.node-i").text().replace(".","").replace(",", ".");
                final String ortalama = row.select("li.cell048.node-j").text().replace(".","").replace(",", ".");
                final String farkP = row.select("li.cell048.node-e").text().replace(".","").replace(",", ".").replace("%", "");
                final String hacimL = row.select("li.cell064.node-k").text().replace(".","").replace(",", ".");
                final String hacimT = row.select("li.cell064.node-l").text().replace(".","").replace(",", ".");
                final String zaman = row.select("li.cell064.node-s").text().replace(".","").replace(",", ".");       
                        
                marketValue[counter][0] = thic;
                marketValue[counter][1] = son;
                marketValue[counter][2] = alış;
                marketValue[counter][3] = satış;
                marketValue[counter][4] = yüksek;
                marketValue[counter][5] = düşük;
                marketValue[counter][6] = ortalama;
                marketValue[counter][7] = farkP;
                marketValue[counter][8] = hacimL;
                marketValue[counter][9] = hacimT;
                marketValue[counter][10] = zaman;
                counter ++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return marketValue;
    }
    
    public String[][] setDataCrypto(){
        try {
            final String url = "https://tr.tradingview.com/markets/cryptocurrencies/prices-all/";
            final Document document = Jsoup.connect(url).timeout(6000).get();
            int counter = 0;
            
            for (Element row : document.select("tbody.tv-data-table__tbody tr")) {
                if (counter > 19) {
                    break;
                } else {
                    final String isim = row.select("td.tv-data-table__cell.tv-screener-table__cell.tv-screener-table__cell--left.tv-screener-table__cell--big div div a").text();
                    final String değer = row.select("td.tv-screener-table__cell--big.tv-screener-table__cell.tv-data-table__cell:nth-of-type(2)").text();
                    final String son = row.select("td.tv-screener-table__cell--big.tv-screener-table__cell.tv-data-table__cell:nth-of-type(4)").text();
                    final String kullanılabilirCoin = row.select("td.tv-screener-table__cell--big.tv-screener-table__cell.tv-data-table__cell:nth-of-type(5)").text();
                    final String toplamCoin = row.select("td.tv-screener-table__cell--big.tv-screener-table__cell.tv-data-table__cell:nth-of-type(6)").text();
                    final String volume = row.select("td.tv-screener-table__cell--big.tv-screener-table__cell.tv-data-table__cell:nth-of-type(7)").text();
                    final String değerP = row.select("td.tv-screener-table__cell--big.tv-screener-table__cell.tv-data-table__cell:nth-of-type(8)").text();

                    crypto[counter][0] = isim;
                    crypto[counter][1] = değer;
                    crypto[counter][2] = son;
                    crypto[counter][3] = kullanılabilirCoin;
                    crypto[counter][4] = toplamCoin;
                    crypto[counter][5] = volume;
                    crypto[counter][6] = değerP;
                    counter ++;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return crypto;
    }
    
    public String[][] setDataMetal(){
        try {
            final String url = "https://bigpara.hurriyet.com.tr/altin/";
            final Document document = Jsoup.connect(url).timeout(6000).get();
            int counter = 0;
            
            for (Element row : document.select("div.table.wide.pgAltin div.tableBox div.tBody ul")) {
                
                final String isim = row.select("li.cell010.tal h3 a").text();
                final String alış = row.select("li.cell009:nth-of-type(2)").text().replace(".","").replace(",", ".");
                final String satış = row.select("li.cell009:nth-of-type(3)").text().replace(".","").replace(",", ".");
                final String farkP = row.select("li.cell009:nth-of-type(4)").text().replace(".","").replace(",", ".");
                final String time = row.select("li.cell009:nth-of-type(5)").text().replace(".","").replace(",", ".");
                
                metals[counter][0] = isim;
                metals[counter][1] = alış;
                metals[counter][2] = satış;
                metals[counter][3] = farkP;
                metals[counter][4] = time;
                counter ++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        
        return metals;
    }
    
    public String[][] setDataCurrency(){
        try {
            final String url = "https://bigpara.hurriyet.com.tr/doviz/";
            final Document document = Jsoup.connect(url).timeout(6000).get();
            int counter = 0;
            
            for (Element row : document.select("div.tableBox.tcmbKur div.tBody ul")) {
                
                final String isim = row.select("li.cell010.tal h3 a").text();
                final String alış = row.select("li.cell015:nth-of-type(3)").text().replace(".","").replace(",", ".");
                final String satış = row.select("li.cell015:nth-of-type(4)").text().replace(".","").replace(",", ".");
                final String farkP = row.select("li.cell015:nth-of-type(5)").text().replace(".","").replace(",", ".");
                final String time = row.select("li.cell007").text().replace(".","").replace(",", ".");
                
                currency[counter][0] = isim;
                currency[counter][1] = alış;
                currency[counter][2] = satış;
                currency[counter][3] = farkP;
                currency[counter][4] = time;
                counter ++;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        
        return currency;
    }
    
    public void writeDataOn(){
        try {
            PrintWriter writer1 = new PrintWriter( "C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\bist100.txt" );
            PrintWriter writer2 = new PrintWriter( "C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\crypto.txt" );
            PrintWriter writer3 = new PrintWriter( "C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\metals.txt" );
            PrintWriter writer4 = new PrintWriter( "C:\\Users\\ASUS\\OneDrive\\Masaüstü\\Cs project ui\\Table\\src\\valueData\\currency.txt" );
            
            for (int i = 0; i < marketValue.length; i++) {
                writer1.println(marketValue[i][0] + " " + marketValue[i][1] + " " + marketValue[i][2] + " " + marketValue[i][3]
                 + " " + marketValue[i][4] + " " + marketValue[i][5] + " " + marketValue[i][6] + " " + marketValue[i][7]+ " " + marketValue[i][8]+ " " + marketValue[i][9]+ " " + marketValue[i][10]);
            }
            for (int i = 0; i < crypto.length; i++) {
                writer2.println(crypto[i][0] + "," + crypto[i][1] + "," + crypto[i][2] + "," + crypto[i][3]
                + "," + crypto[i][4] + "," + crypto[i][5] + "," + crypto[i][6]);
            }
            for (int i = 0; i < metals.length; i++) {
                writer3.println(metals[i][0] + "," + metals[i][1] + "," + metals[i][2] + "," + metals[i][3]
                + "," + metals[i][4]);
            }
            for (int i = 0; i < currency.length; i++) {
                writer4.println(currency[i][0] + "," + currency[i][1] + "," + currency[i][2] + "," + currency[i][3]
                + "," + currency[i][4]);
            }
            
            writer1.close();
            writer2.close();
            writer3.close();
            writer4.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public Object[][] sortData() {
        String temp;

        for (int i = 0; i < marketValue.length; i++) {
            for (int j = i + 1; j < marketValue.length; j++) { 
                if (marketValue[i][0].compareTo(marketValue[j][0])>0) 
                {
                    for (int b = 0; b < 8; b++) {
                        temp = marketValue[i][b];
                        marketValue[i][b] = marketValue[j][b];
                        marketValue[j][b] = temp;
                    }                    
                }
            }
        }
        return marketValue;
    }
}
