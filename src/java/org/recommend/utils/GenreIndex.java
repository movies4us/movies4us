/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.recommend.utils;

/**
 *
 * @author Aveek
 */
public class GenreIndex 
{    
    public int getIndex(String genre)
    {
        if(genre.equals("Action"))
            return 0;
        else if(genre.equals("Adventure"))
            return 1;
        else if(genre.equals("Animation"))
            return 2;
        else if(genre.equals("Children"))
            return 3;        
        else if(genre.equals("Comedy"))
            return 4;
        else if(genre.equals("Crime"))
            return 5;
        else if(genre.equals("Documentary"))
            return 6;
        else if(genre.equals("Drama"))
            return 7;        
        else if(genre.equals("Fantasy"))
            return 8;
        else if(genre.equals("Film-Noir"))
            return 9;        
        else if(genre.equals("Horror"))
            return 10;
        else if(genre.equals("IMAX"))
            return 11;        
        else if(genre.equals("Musical"))
            return 12;
        else if(genre.equals("Mystery"))
            return 13;
        else if(genre.equals("Romance"))
            return 14;
        else if(genre.equals("Sci-Fi"))
            return 15;        
        else if(genre.equals("Thriller"))
            return 16;
        else if(genre.equals("War"))
            return 17;
        else if(genre.equals("Western"))
            return 18;
        else
            return -1;
    }
}
