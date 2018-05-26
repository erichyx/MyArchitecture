package com.arch.eric.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

/**
 * Created by eric on 2017/11/12.
 */
@Entity
public class JokeEntity
{
    @PrimaryKey
    private String id;
    private String joke;
    private List<String> categories;

    public String getId()
    {
        return "id:"+ id;
    }

    public String getJoke()
    {
        return joke;
    }

    public String getCategory()
    {
        StringBuilder sb = new StringBuilder("category:");
        for (String category : categories)
        {
            sb.append(category);
        }
        return sb.toString();
    }
}
