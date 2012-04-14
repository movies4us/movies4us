/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.movielens.utils.dto;

/**
 *
 * @author arka
 */
public class MoviesTableObject implements TableObject{
    private Integer movieId;
    private String movieName;
    private String releaseYear;
    private String language;
    private Integer rating;
    private Integer rank;
    private Integer runtime;
    private String mpaa;
    private String imdbId;
    
    
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMpaa() {
        return mpaa;
    }

    public void setMpaa(String mpaa) {
        this.mpaa = mpaa;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Override
    public String toString() {
        return "MoviesTableObject{" + "movieId=" + movieId + ", movieName=" + movieName + ", releaseYear=" + releaseYear + ", language=" + language + ", rating=" + rating + ", rank=" + rank + ", runtime=" + runtime + ", mpaa=" + mpaa + ", imdbId=" + imdbId + '}';
    }

    
    
    
}
