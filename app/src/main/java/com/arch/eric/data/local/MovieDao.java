package com.arch.eric.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.arch.eric.data.local.MovieSubjectEntity.CastsBean;
import com.arch.eric.data.local.MovieSubjectEntity.SubjectsBean;
import com.arch.eric.data.local.MovieSubjectEntity.DirectorsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 2018/5/31
 */
@Dao
public abstract class MovieDao {
    @Query("SELECT * FROM tb_movie")
    @Transaction
    public abstract LiveData<List<MovieInfo>> getAllMovies();

    @Query("DELETE FROM tb_movie")
    public abstract void deleteAllMovie();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertMovies(List<SubjectsBean> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertCasts(List<CastsBean> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertDirectors(List<DirectorsBean> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertGenres(List<MovieGenre> list);

    @Transaction
    public void insetAllMovies(List<MovieInfo> movieInfoList) {

        List<SubjectsBean> subjectList = new ArrayList<>();
        List<CastsBean> castsList = new ArrayList<>();
        List<MovieGenre> genresList = new ArrayList<>();
        List<DirectorsBean> directorList = new ArrayList<>();

        for (MovieInfo movieInfo : movieInfoList) {
            SubjectsBean subjectsBean = movieInfo.getSubject();
            subjectList.add(subjectsBean);


            List<CastsBean> casts = subjectsBean.getCasts();
            for (CastsBean castsBean : casts) {
                castsBean.setMovieId(subjectsBean.getSubjectId());
            }
            castsList.addAll(casts);

            List<String> genres = subjectsBean.getGenres();
            genresList.addAll(MovieGenre.transform(subjectsBean.getSubjectId(), genres));

            List<DirectorsBean> directors = subjectsBean.getDirectors();
            for (DirectorsBean directorsBean : directors) {
                directorsBean.setMovieId(subjectsBean.getSubjectId());
            }
            directorList.addAll(directors);
        }

        insertMovies(subjectList);
        insertGenres(genresList);
        insertCasts(castsList);
        insertDirectors(directorList);
    }
}
