/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.MovieGenreCompositeObject;
import org.m4us.movielens.utils.dto.MoviesGenreTableObject;
import org.m4us.movielens.utils.dto.MoviesTableObject;
import org.m4us.movielens.utils.qo.MoviesBulkInsert;

/**
 *
 * @author arka
 */
public class MoviesParser implements Parser {

    @Override
    public void parse(Reader input, ConnectionInfo connInfo) {
        String line;
        List<DataTransferObject> compositeList = new ArrayList<DataTransferObject>();
        Connection m4usConn = ConnectionManager.getConnection(connInfo);
        try {
            while ((line = ((BufferedReader) input).readLine()) != null) {
                MovieGenreCompositeObject compObject = new MovieGenreCompositeObject();
                String[] lineEntries = line.split("::");
                MoviesTableObject object = new MoviesTableObject();
                
                int movieId = Integer.parseInt(lineEntries[0]);
                object.setMovieId(movieId);
                String movieName = lineEntries[1].substring(0,lineEntries[1].lastIndexOf("("));
                String releaseYear = lineEntries[1].substring(lineEntries[1].lastIndexOf("(")+1,lineEntries[1].lastIndexOf(")"));
                object.setMovieName(movieName.trim());
                object.setReleaseYear(releaseYear.trim());
                compObject.setMovieObject(object);
                String[] genres = lineEntries[2].split("\\|");
                List<MoviesGenreTableObject> genreList = new ArrayList<MoviesGenreTableObject>();
                for(String genre:genres){
                    MoviesGenreTableObject genreObject = new MoviesGenreTableObject();
                    genreObject.setMovieId(movieId);
                    genreObject.setGenre(genre);
                    genreList.add(genreObject);
                }
                compObject.setGenreObjectList(genreList);
                compositeList.add(compObject);
            }
            (new MoviesBulkInsert()).bulkInsert(compositeList, m4usConn);
        } catch (IOException ex) {
            Logger.getLogger(MoviesParser.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(MoviesParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
