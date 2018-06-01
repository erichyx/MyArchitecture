package com.arch.eric.data.local;

import android.arch.persistence.room.TypeConverter;

import com.arch.eric.api.gson.GsonContext;
import com.arch.eric.entity.MovieSubjectEntity.CastsBean;
import com.arch.eric.entity.MovieSubjectEntity.DirectorsBean;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by eric on 2018/5/31
 */
public class RoomDataConverter {
    @TypeConverter
    public static List<String> toGenResList(String json) {
        return GsonContext.getGson().fromJson(json, new TypeToken<List<String>>() {
        }.getType());
    }

    @TypeConverter
    public static String toGenResJson(List<String> list) {
        return GsonContext.getGson().toJson(list);
    }

    @TypeConverter
    public static List<CastsBean> toCastList(String json) {
        return GsonContext.getGson().fromJson(json, new TypeToken<List<CastsBean>>() {
        }.getType());
    }

    @TypeConverter
    public static String toCastJson(List<CastsBean> list) {
        return GsonContext.getGson().toJson(list);
    }

    @TypeConverter
    public static List<DirectorsBean> toDirectorList(String json) {
        return GsonContext.getGson().fromJson(json, new TypeToken<List<DirectorsBean>>() {
        }.getType());
    }

    @TypeConverter
    public static String toDirectorJson(List<DirectorsBean> list) {
        return GsonContext.getGson().toJson(list);
    }
}
