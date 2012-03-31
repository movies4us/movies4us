/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Aveek
 */
public class URLData 
{
    String data;
    
    public URLData(String url)
    {
        data="";
        
        try
        {                        
            URL myURL=new URL(url);  
            BufferedReader in=new BufferedReader(new InputStreamReader(myURL.openStream()));
            String reply;
            
            while((reply=in.readLine())!=null)
            {
                data+=reply;
            }            
            in.close();                   
        }catch(MalformedURLException e)
        {
            System.out.println("Cannot reach URL");
        }catch(IOException ex)
        {
            
        }
    }
    
    public String getURLData()
    {
        return data;
    }
}
