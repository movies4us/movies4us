/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils.dto;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Aveek
 */
public class MovieInformation {
    
    String movieName, movieYear, movieInfo;
    String movieLanguage, mpaaRating, plot, posterLink, imdbID;
    Vector directors, actors, genre;
    float rating;
    int runtime, movieID;
    
    public MovieInformation(int ID, String name, String year, String data)
    {
        movieID=ID;
        movieName=name;
        movieYear=year;
        movieInfo=data;
        //System.out.println(movieInfo);        
        parseInformation();        
    }   
    
    public void parseInformation()
    {
        StringTokenizer st=new StringTokenizer(movieInfo);
        
        for(int i=0;i<11;i++)
            st.nextToken("\"");
        mpaaRating=st.nextToken("\"");
        
        for(int i=0;i<7;i++)
            st.nextToken("\"");
        
        String str=st.nextToken();        
        StringTokenizer stz = new StringTokenizer(str);
        genre=new Vector();
        while(stz.hasMoreTokens())
        {
            genre.add(stz.nextToken(",").trim());
        }
        
        for(int i=0;i<3;i++)
            st.nextToken("\"");
        str=st.nextToken();  
        
        stz = new StringTokenizer(str);
        directors=new Vector();
        while(stz.hasMoreTokens())
        {
            directors.add(stz.nextToken(",").trim());
        }
        
        for(int i=0;i<7;i++)
            st.nextToken("\"");
        str=st.nextToken();  
        
        stz = new StringTokenizer(str);
        actors=new Vector();
        while(stz.hasMoreTokens())
        {
            actors.add(stz.nextToken(",").trim());
        }
        
        for(int i=0;i<3;i++)
            st.nextToken("\"");
        plot=st.nextToken("\"");
        //System.out.println(plot);
        for(int i=0;i<3;i++)
            st.nextToken("\"");
        posterLink=st.nextToken("\"");
        //System.out.println(posterLink);
        for(int i=0;i<3;i++)
            st.nextToken("\"");
        str=st.nextToken("\"");
        
        String hr="0";
        if(str.indexOf("hr")>=0)
        {
            hr=str.substring(0,str.indexOf("hr"));                    
            hr=hr.trim();
        }
        //System.out.println(hr+" >> "+str);
        
        int mins=0;
        if(str.indexOf("min")>=0)
        {
            if(str.indexOf("hrs")>=0)
                str=str.substring(str.indexOf("hrs")+3, str.indexOf("min"));
            else if(str.indexOf("hr")>=0)
                str=str.substring(str.indexOf("hr")+2, str.indexOf("min"));
            else 
                str=str.substring(0, str.indexOf("min"));
            //System.out.println(str+" abcj  "+hr);
            str=str.trim();
            mins=Integer.parseInt(str);
            //System.out.println("min"+mins);        
        }
        //System.out.println(str);
        try
        {
            if(str.equals("N/A"))
                runtime=-1;    
            else        
                runtime=Integer.parseInt(hr)*60 +mins;  
        }catch(NumberFormatException e)
        {
            System.out.println("Number Format Exception");
        }
        
        for(int i=0;i<3;i++)
            st.nextToken("\"");
        str=st.nextToken("\"");
        if(str.equals("N/A"))
            rating=-1;
        else
            rating=new Float(str);
        
        str=movieInfo.substring(movieInfo.indexOf("\"ID\":\"")+6,movieInfo.indexOf("\",\"Response\""));
        imdbID=str;
        
        String url="http://api.themoviedb.org/2.1/Movie.imdbLookup/en/xml/9cdedc9118fd374c5aa17d2905080d2b/"+imdbID;
        URLData ud=new URLData(url);
        str=ud.getURLData();
        if(str.indexOf("<language>")!=-1)
            movieLanguage=str.substring(str.indexOf("<language>")+10,str.indexOf("</language>"));       
    }
    
    public String getIMDBID()
    {
        return imdbID;
    }
    
    public int getMovieID()
    {
        return movieID;
    }
    
    public String getPosterLink()
    {
        return posterLink;
    }
    
    public Vector getGenre()
    {
        return genre;
    }
    
    public int getRuntime()
    {
        return runtime;
    }
    
    public String getMovieName()
    {
        return movieName;
    }
    
    public String getMovieYear()
    {
        return movieYear;
    }
            
    public String getMovieLanguage()
    {
        return movieLanguage;
    }
    
    public float getRating()
    {
        return rating;
    }
    
    public String getMPAARating()
    {
        return mpaaRating;
    }
            
    public String getPlot()
    {
        return plot;
    }
    
    public Vector getActors()
    {
        return actors;
    }
    
    public Vector getDirectors()
    {
        return directors;
    }
}
