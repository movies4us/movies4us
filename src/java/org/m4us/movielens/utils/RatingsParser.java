/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.RatingsTableObject;

/**
 *
 * @author arka
 */
public class RatingsParser implements Parser{

    
    @Override
    public void parse(Reader input, ConnectionInfo connInfo) {
        String line;
        List<DataTransferObject> ratingsList = new ArrayList<DataTransferObject>();
        ExecutorService exec = Executors.newFixedThreadPool(5);
        try {
            while ((line = ((BufferedReader) input).readLine()) != null) {
                RatingsTableObject ratingObject = new RatingsTableObject();
                String[] lineEntries = line.split("::");
                ratingObject.setUserId(Integer.parseInt(lineEntries[0]));
                ratingObject.setMovieId(Integer.parseInt(lineEntries[1]));
                ratingObject.setRating(Float.parseFloat(lineEntries[2]));
                ratingObject.setRatingDate(new Timestamp(Long.parseLong(lineEntries[3])));
                ratingsList.add(ratingObject);
                if(ratingsList.size()>=1000){
                    exec.execute(new RatingsProcessor(new ArrayList<DataTransferObject>(ratingsList), connInfo));
                    ratingsList.clear();
                }
            }
            exec.execute(new RatingsProcessor(new ArrayList<DataTransferObject>(ratingsList), connInfo));
            exec.shutdown();
        } catch (IOException ex) {
            Logger.getLogger(RatingsParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(RatingsParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}
