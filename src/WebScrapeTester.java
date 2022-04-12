/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author BARIŞ YILDIRIM
 */
public class WebScrapeTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WebScrape obj = new WebScrape();
        
        obj.setDataBist();
        obj.setDataCrypto();
        obj.setDataMetal();
        obj.setDataCurrency();
        obj.printData();
        //obj.updateData();
        obj.writeDataOn();
    }
    
}