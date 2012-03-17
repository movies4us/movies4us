/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.imdb.utils.dto.MoviesPersonsTableObject;
import org.m4us.imdb.utils.qo.MovieRatingsDistinct;
import org.m4us.movielens.utils.ConnectionManager;
import org.m4us.movielens.utils.dto.DataTransferObject;

/**
 *
 * @author arka
 */
public class IMDBDataSetup {
    public static void main(String[] args) {
        
        Map<String,Integer> distinctMoviesMap = new HashMap<String, Integer>();
        distinctMoviesMap = getDistinctRatedMovies();
        processActorsFile(distinctMoviesMap);
        processActressFile(distinctMoviesMap);
    }

    private static Map<String, Integer> getDistinctRatedMovies() {
        return new MovieRatingsDistinct().retrieveList(ConnectionManager.getConnection());
    }

    private static void processActorsFile(Map<String, Integer> distinctMoviesMap) {
        BufferedReader in = null;
        int lineCount = 0;
        String line = "";
        String currentActor = "";
        String currentMovie = "";
        String currentMovieRelYr = "";
        List<DataTransferObject> personsList = new ArrayList<DataTransferObject>();
        ExecutorService exec = Executors.newFixedThreadPool(5);
        try {
          in   = new BufferedReader(new FileReader("./dataSetup/actors.list"));
          while(lineCount<239){
              in.readLine();lineCount++;
          }
          
          while((line = in.readLine())!=null){
              if(line.trim().equals(""))
                  continue;
              if (line.charAt(0) != ' ' && line.charAt(0) != '\t' ){
                  String words[] = line.split("\t");
                  currentActor = words[0].trim();
                  line = words[words.length-1].trim();
              }
              currentMovie=line.substring(0, line.indexOf('(')).trim();
              currentMovieRelYr=line.substring(line.indexOf('(')+1, line.indexOf(')')).trim();
              if(distinctMoviesMap.containsKey(currentMovie+"|"+currentMovieRelYr)){
                  MoviesPersonsTableObject personsObject = new MoviesPersonsTableObject();
                  personsObject.setMovieId(distinctMoviesMap.get(currentMovie+"|"+currentMovieRelYr));
                  personsObject.setPersonName(currentActor);
                  personsObject.setPersonRole("AR");
                  personsList.add(personsObject);
              }
              if(personsList.size()>=1000){
                    exec.execute(new PersonsProcessor(new ArrayList<DataTransferObject>(personsList), null));
                    personsList.clear();
                }
                  
          }
          exec.execute(new PersonsProcessor(new ArrayList<DataTransferObject>(personsList), null));
          exec.shutdown();
        } catch (IOException ex) {
            Logger.getLogger(IMDBDataSetup.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(IMDBDataSetup.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    private static void processActressFile(Map<String, Integer> distinctMoviesMap) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
