/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.TagsTableObject;

/**
 *
 * @author arka
 */
public class TagsParser implements Parser {

    @Override
    public void parse(Reader input, ConnectionInfo connInfo) {
        String line;
        List<DataTransferObject> tagsList = new ArrayList<DataTransferObject>();
        ExecutorService exec = Executors.newFixedThreadPool(5);
        try {
            while ((line = ((BufferedReader) input).readLine()) != null) {
                TagsTableObject tagObject = new TagsTableObject();
                String[] lineEntries = line.split("::");
                tagObject.setUserId(Integer.parseInt(lineEntries[0]));
                tagObject.setMovieId(Integer.parseInt(lineEntries[1]));
                tagObject.setTag(lineEntries[2]);
                tagObject.setTagDate(new Timestamp(Long.parseLong(lineEntries[3])));
                tagsList.add(tagObject);
                if(tagsList.size()>=1000){
                    exec.execute(new TagsProcessor(new ArrayList<DataTransferObject>(tagsList), connInfo));
                    tagsList.clear();
                }
            }
            exec.execute(new TagsProcessor(new ArrayList<DataTransferObject>(tagsList), connInfo));
            exec.shutdown();
        } catch (IOException ex) {
            Logger.getLogger(TagsParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(TagsParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
