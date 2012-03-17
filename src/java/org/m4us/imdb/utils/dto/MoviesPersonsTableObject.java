/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.imdb.utils.dto;

import org.m4us.movielens.utils.dto.TableObject;

/**
 *
 * @author arka
 */
public class MoviesPersonsTableObject implements TableObject{
    private Integer movieId;
    private String personName;
    private String personRole;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonRole() {
        return personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }

}
