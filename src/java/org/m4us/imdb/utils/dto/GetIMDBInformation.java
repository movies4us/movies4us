/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils.dto;

import java.util.StringTokenizer;

/**
 *
 * @author Aveek
 */
public class GetIMDBInformation {

    String movieName="";
    String movieYear="";
    String data="";
    
    public GetIMDBInformation(String name, String year)
    {
        movieName=name;
        movieYear=year;
        
        getInformation();
    }
    
    public void getInformation()
    {
        StringTokenizer st=new StringTokenizer(movieName);        
        String url="http://www.imdbapi.com/?t=";
        
        while(st.hasMoreTokens())
        {
            url=url+st.nextToken(" ")+"%20";
        }
        url=url.substring(0,url.lastIndexOf("%20"));
        url=url+"&y="+movieYear;
        
        URLData ud=new URLData(url);
        data=ud.getURLData();
    }
    public String getData()
    {
        return data;
    }
}
