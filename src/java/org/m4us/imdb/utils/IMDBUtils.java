/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils;

/**
 *
 * @author arka
 */
public class IMDBUtils {
    public static String stripAliasInfo(String movieNameWithAlias){
        String movieNameWithoutAlias="";
        if(movieNameWithAlias.contains("("))
            movieNameWithoutAlias = movieNameWithAlias.substring(0,movieNameWithAlias.indexOf("(")).trim();
        else
            movieNameWithoutAlias = movieNameWithAlias;
        return movieNameWithoutAlias;
    }
}
