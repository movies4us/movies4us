/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.qo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.m4us.movielens.utils.dto.DataTransferObject;
import org.m4us.movielens.utils.dto.MovieGenreCompositeObject;
import org.m4us.movielens.utils.dto.MoviesGenreTableObject;

/**
 *
 * @author arka
 */
public class MoviesBulkInsert implements BulkInsertObject{

    @Override
    public void bulkInsert(List<DataTransferObject> objectList, Connection conn) {
        int paramNo = 1;
        int count = 0;
        StringBuilder queryStringMovies = new StringBuilder("INSERT INTO MOVIES VALUES (");
        queryStringMovies.append(" ? , ? , ? )");
        StringBuilder queryStringGenres = new StringBuilder("INSERT INTO MOVIES_GENRE VALUES (");
        queryStringGenres.append(" ? , ? )");
        PreparedStatement moviesStmt=null;
        PreparedStatement genreStmt=null;
        MovieGenreCompositeObject object = null;
        try{
        conn.setAutoCommit(false);
        moviesStmt = conn.prepareStatement(queryStringMovies.toString());
        genreStmt = conn.prepareStatement(queryStringGenres.toString());
        
        for(DataTransferObject obj:objectList){
            
            object = (MovieGenreCompositeObject)obj;
            moviesStmt.setInt(paramNo++, object.getMovieObject().getMovieId());
            moviesStmt.setString(paramNo++, object.getMovieObject().getMovieName());
            moviesStmt.setString(paramNo++, object.getMovieObject().getReleaseYear());
            
            moviesStmt.executeUpdate();
            paramNo=1;
            
            for(MoviesGenreTableObject genreObject: object.getGenreObjectList()){
                genreStmt.setInt(paramNo++, genreObject.getMovieId());
                genreStmt.setString(paramNo++, genreObject.getGenre());
                genreStmt.executeUpdate();
                paramNo=1;
            }
            count++;
            if(count>=100){
                conn.commit();
                count=0;
            }
        }
        conn.commit();    // commit the last batch of records that may not 
                          //be multiple of 100
        }catch(SQLException e){
            System.out.println("Current object"+object.toString());
            e.printStackTrace();
        }finally{
            try {
                moviesStmt.close();
                genreStmt.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserInfoBulkInsert.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
