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
import java.util.concurrent.TimeUnit;
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
        //processActorsFile(distinctMoviesMap);
        //processActressFile(distinctMoviesMap);
        processDirectorsFile(distinctMoviesMap);
        try {
            TimeUnit.MINUTES.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(IMDBDataSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Map<String, Integer> getDistinctRatedMovies() {
        return new MovieRatingsDistinct().retrieveList(ConnectionManager.getConnection());
    }

    private static void processActorsFile(Map<String, Integer> distinctMoviesMap) {
        BufferedReader in = null;
        long lineCount = 0;
        String line = "";
        String currentActor = "";
        String currentMovie = "";
        String currentMovieRelYr = "";
        
        Map<String,Boolean> personMovieCombination = new HashMap<String, Boolean>();

        List<DataTransferObject> personsList = new ArrayList<DataTransferObject>();
        ExecutorService exec = Executors.newFixedThreadPool(5);
        try {
          in   = new BufferedReader(new FileReader("./dataSetup/actors.list"));
          while(lineCount<239){
              in.readLine();lineCount++;
          }
          
          while((line = in.readLine())!=null){
              lineCount++;
              if(lineCount>=12269596)
                  break;
              if(line.trim().equals(""))
                  continue;
              if (line.charAt(0) != ' ' && line.charAt(0) != '\t' ){
                  personMovieCombination.clear();
                  String words[] = line.split("\t");
                  currentActor = words[0].trim();
                  line = words[words.length-1].trim();
              }
              try{
                  currentMovie=line.substring(0, line.indexOf('(')).trim();
              }catch(Exception e){
                  System.out.println("at line number ::"+lineCount+" ::value of current line------"+line);
                  System.out.println("exception is----"+e.getMessage());
                  continue;
              }
              if(personMovieCombination.containsKey(currentActor+currentMovie))
                  continue;
              else
                  personMovieCombination.put(currentActor+currentMovie,true);
              int relYrStart = line.indexOf('(')+1;
              currentMovieRelYr=line.substring(relYrStart, relYrStart+4).trim();
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
        }catch (Exception ex) {
            System.out.println("at line number ::"+lineCount+" ::value of current line------"+line);
            System.out.println("exception is----"+ex.getMessage());
        }finally{
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(IMDBDataSetup.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    private static void processActressFile(Map<String, Integer> distinctMoviesMap) {
        BufferedReader in = null;
        long lineCount = 0;
        String line = "";
        String currentActor = "";
        String currentMovie = "";
        String currentMovieRelYr = "";
        
        Map<String,Boolean> personMovieCombination = new HashMap<String, Boolean>();

        List<DataTransferObject> personsList = new ArrayList<DataTransferObject>();
        ExecutorService exec = Executors.newFixedThreadPool(5);
        try {
          in   = new BufferedReader(new FileReader("./dataSetup/actresses.list"));
          while(lineCount<241){
              in.readLine();lineCount++;
          }
          
          while((line = in.readLine())!=null){
              lineCount++;
              if(lineCount>=7229244)
                  break;
              if(line.trim().equals(""))
                  continue;
              if (line.charAt(0) != ' ' && line.charAt(0) != '\t' ){
                  personMovieCombination.clear();
                  String words[] = line.split("\t");
                  currentActor = words[0].trim();
                  line = words[words.length-1].trim();
              }
              try{
                  currentMovie=line.substring(0, line.indexOf('(')).trim();
              }catch(Exception e){
                  System.out.println("at line number ::"+lineCount+" ::value of current line------"+line);
                  System.out.println("exception is----"+e.getMessage());
                  continue;
              }
              if(personMovieCombination.containsKey(currentActor+currentMovie))
                  continue;
              else
                  personMovieCombination.put(currentActor+currentMovie,true);
              int relYrStart = line.indexOf('(')+1;
              currentMovieRelYr=line.substring(relYrStart, relYrStart+4).trim();
              if(distinctMoviesMap.containsKey(currentMovie+"|"+currentMovieRelYr)){
                  MoviesPersonsTableObject personsObject = new MoviesPersonsTableObject();
                  personsObject.setMovieId(distinctMoviesMap.get(currentMovie+"|"+currentMovieRelYr));
                  personsObject.setPersonName(currentActor);
                  personsObject.setPersonRole("AS");
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
        }catch (Exception ex) {
            System.out.println("at line number ::"+lineCount+" ::value of current line------"+line);
            System.out.println("exception is----"+ex.getMessage());
        }finally{
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(IMDBDataSetup.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    private static void processDirectorsFile(Map<String, Integer> distinctMoviesMap) {
        BufferedReader in = null;
        long lineCount = 0;
        String line = "";
        String currentActor = "";
        String currentMovie = "";
        String currentMovieRelYr = "";
        
        Map<String,Boolean> personMovieCombination = new HashMap<String, Boolean>();

        List<DataTransferObject> personsList = new ArrayList<DataTransferObject>();
        ExecutorService exec = Executors.newFixedThreadPool(5);
        try {
          in   = new BufferedReader(new FileReader("./dataSetup/directors.list"));
          while(lineCount<235){
              in.readLine();lineCount++;
          }
          
          while((line = in.readLine())!=null){
              lineCount++;
              if(lineCount>=1719851)
                  break;
              if(line.trim().equals(""))
                  continue;
              if (line.charAt(0) != ' ' && line.charAt(0) != '\t' ){
                  personMovieCombination.clear();
                  String words[] = line.split("\t");
                  currentActor = words[0].trim();
                  line = words[words.length-1].trim();
              }
              try{
                  currentMovie=line.substring(0, line.indexOf('(')).trim();
              }catch(Exception e){
                  System.out.println("at line number ::"+lineCount+" ::value of current line------"+line);
                  System.out.println("exception is----"+e.getMessage());
                  continue;
              }
              if(personMovieCombination.containsKey(currentActor+currentMovie))
                  continue;
              else
                  personMovieCombination.put(currentActor+currentMovie,true);
              int relYrStart = line.indexOf('(')+1;
              currentMovieRelYr=line.substring(relYrStart, relYrStart+4).trim();
              if(distinctMoviesMap.containsKey(currentMovie+"|"+currentMovieRelYr)){
                  MoviesPersonsTableObject personsObject = new MoviesPersonsTableObject();
                  personsObject.setMovieId(distinctMoviesMap.get(currentMovie+"|"+currentMovieRelYr));
                  personsObject.setPersonName(currentActor);
                  personsObject.setPersonRole("DI");
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
        }catch (Exception ex) {
            System.out.println("at line number ::"+lineCount+" ::value of current line------"+line);
            System.out.println("exception is----"+ex.getMessage());
        }finally{
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(IMDBDataSetup.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}
