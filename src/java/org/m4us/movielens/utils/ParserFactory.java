/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

/**
 *
 * @author arka
 */
public class ParserFactory {
    public static final String MOVIELENS_TAGS_FILE = "./movielens/tags.dat";
    public static final String MOVIELENS_MOVIES_FILE = "./movielens/movies.dat";
    public static final String MOVIELENS_RATINGS_FILE = "./movielens/ratings.dat";
    
    public static Parser getInstance(String fileName){
        if(fileName.equalsIgnoreCase(MOVIELENS_TAGS_FILE))
            return new TagsParser();
        if(fileName.equalsIgnoreCase(MOVIELENS_MOVIES_FILE))
            return new MoviesParser();
        if(fileName.equalsIgnoreCase(MOVIELENS_RATINGS_FILE))
            return new RatingsParser();
        
        throw new UnsupportedOperationException("Invalid filename---"+ fileName);
    }
}
