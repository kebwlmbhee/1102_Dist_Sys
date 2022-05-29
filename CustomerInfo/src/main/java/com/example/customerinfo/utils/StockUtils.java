package com.example.customerinfo.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class StockUtils {
    public String getStockPrice(String s)throws Exception{
        String source="";
        //try {
            String urlString = "https://tw.stock.yahoo.com/quote/"+s;
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-agent", "IE/6.0");
            InputStream is = conn.getInputStream();
            Document doc = Jsoup.parse(is, "Big5", urlString);
            String price = doc.select("span[class~=32px]").text().trim();;
            System.out.println(s+":"+price);
            is.close();
            return price;
//        } catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
    }
    
    public static void main(String[] args)throws Exception{
        StockUtils util = new StockUtils();
        String price = util.getStockPrice("2330");
        System.out.println("price="+price);
    }
}
