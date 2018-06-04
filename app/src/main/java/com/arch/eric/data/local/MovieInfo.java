package com.arch.eric.data.local;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.arch.eric.data.local.MovieSubjectEntity.CastsBean;
import com.arch.eric.data.local.MovieSubjectEntity.DirectorsBean;
import com.arch.eric.data.local.MovieSubjectEntity.SubjectsBean;

import java.util.List;

/**
 * Created by eric on 2018/6/3
 */
public class MovieInfo {
    @Embedded
    private SubjectsBean subject;

    @Relation(parentColumn = "subjectId", entityColumn = "movieId", entity = CastsBean.class)
    private List<CastsBean> casts;

    @Relation(parentColumn = "subjectId", entityColumn = "movieId", entity = DirectorsBean.class)
    private List<DirectorsBean> directors;

    @Relation(parentColumn = "subjectId", entityColumn = "movieId", entity = MovieGenre.class)
    private List<MovieGenre> genres;

    public SubjectsBean getSubject() {
        return subject;
    }

    public void setSubject(SubjectsBean subject) {
        this.subject = subject;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<MovieGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieGenre> genres) {
        this.genres = genres;
    }

    public String toActorStr() {
        String actors = "";
        if (casts != null) {
            StringBuilder sb = new StringBuilder();
            int num = Math.min(3, casts.size());
            for (int i = 0; i < num; ++i) {
                sb.append(casts.get(i).getName());
                if (i < num - 1) {
                    sb.append(" / ");
                }
            }
            actors = sb.toString();
        }

        return actors;
    }
}
