package com.example.customerinfo.web;

import javax.servlet.*;
import java.util.*;
import java.io.*;

public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {        
        ServletContext context = sce.getServletContext();
        Map<String, String> companyMap = new LinkedHashMap<>();
        String companyFile = context.getInitParameter("companyFile");
        System.out.println("company File:"+companyFile);
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = context.getResourceAsStream(companyFile);
            reader = new BufferedReader(new InputStreamReader(is));
            String record;
            // Read every record (one per line)
            while ( (record = reader.readLine()) != null ) {
                String[] fields = record.split(",");
                // Extract the data fields for the record
                String code = fields[0];
                String name = fields[1];
                // Add the new League item to the list
                companyMap.put(code,name);
            }
            context.setAttribute("companys", companyMap);
            context.log("The Company Map has been loaded.");           
        } catch(Exception e){
            context.log("Exception occured while processing the leagues file.", e);
        } finally {
            if ( is != null ) {
                try { is.close(); } catch (Exception e) {}
            }
            if ( reader != null ) {
                try { reader.close(); } catch (Exception e) {}
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {       
        ServletContext context = sce.getServletContext();
        context.log("contextDestroyed!");
    }
}
