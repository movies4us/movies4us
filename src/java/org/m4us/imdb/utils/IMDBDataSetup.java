/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.imdb.utils.qo.MovieRatingsDistinct;
import org.m4us.movielens.utils.ConnectionManager;

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
                  line = words[words.length-1];
              }
              currentMovie=line.substring(0, line.indexOf('('));
              if(distinctMoviesMap.containsKey(currentMovie)){
                  
              }
                  
          }
          String words[] = in.readLine().split("\t");
            System.out.println("splitted"+words[0]+","+words[words.length-1]);
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
