package com.arch.eric.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.arch.eric.data.local.MovieSubjectEntity.SubjectsBean;
import com.arch.eric.data.local.MovieSubjectEntity.CastsBean;
import com.arch.eric.data.local.MovieSubjectEntity.DirectorsBean;

import cn.eric.basicore.app.AppExecutors;

/**
 * Created by eric on 2018/5/31
 */
@Database(entities = {SubjectsBean.class, CastsBean.class, DirectorsBean.class, MovieGenre.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;
    public static final String DATABASE_NAME = "arch.db";
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public abstract MovieDao movieDao();

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        // 该方法在线程中执行
                        AppDatabase database = AppDatabase.getInstance(appContext);
                        database.setDatabaseCreated();
                    }
                }).build();
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    @WorkerThread
    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }
}
