package com.arch.eric.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.arch.eric.entity.MovieSubjectEntity.SubjectsBean;

import java.util.List;

/**
 * Created by eric on 2018/5/31
 */
@Dao
public interface MovieDao {
    @Query("SELECT * FROM subjects")
    LiveData<List<SubjectsBean>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SubjectsBean> list);

    @Query("DELETE FROM subjects")
    void deleteAll();

}
