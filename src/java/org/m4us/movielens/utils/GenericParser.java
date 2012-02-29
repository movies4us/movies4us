/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.sql.Connection;
import java.util.Queue;

/**
 *
 * @author arka
 */
public abstract class GenericParser implements Parser{
    private Queue<String> movieLensData;
    private Connection dbConn;

    public GenericParser(Queue<String> movieLensData, Connection dbConn) {
        this.movieLensData = movieLensData;
        this.dbConn = dbConn;
    }
    
    
}
