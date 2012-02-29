/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.io.Reader;
import java.sql.Connection;
import java.util.Queue;

/**
 *
 * @author arka
 */
public class MoviesParser extends GenericParser{

    public MoviesParser(Queue<String> movieLensData, Connection dbConn) {
        super(movieLensData, dbConn);
    }

    
    
    @Override
    public void parse(Reader input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
