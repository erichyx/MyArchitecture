package com.arch.eric.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import static com.arch.eric.data.local.MovieSubjectEntity.*;

/**
 * Created by eric on 2018/6/3
 */
@Entity(tableName = "tb_movie_genre", indices = {@Index(value = "movieId")},
        foreignKeys = @ForeignKey(entity = SubjectsBean.class,
                parentColumns = "subjectId",
                childColumns = "movieId", onDelete = ForeignKey.CASCADE))
public class MovieGenre {

    @PrimaryKey(autoGenerate = true)
    private int autoId;

    private int movieId;

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    private String genre;

    public static List<MovieGenre> transform(int movieId, List<String> genres) {
        List<MovieGenre> movieGenreList = new ArrayList<>(genres.size());
        for (String strGenre : genres) {
            MovieGenre movieGenre = new MovieGenre();
            movieGenre.setMovieId(movieId);
            movieGenre.setGenre(strGenre);
            movieGenreList.add(movieGenre);
        }
        return movieGenreList;
    }
}
