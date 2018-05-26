package com.arch.eric.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eric on 2017/11/12.
 */

public class JokeResultEntity
{
    /**
     * type : success
     * value : [{"id":456,"joke":"All browsers support the hex definitions #chuck and #norris for the colors black and blue.","categories":["nerdy"]}]
     */

    private String type;
    @SerializedName("value")
    private List<JokeEntity> jokeList;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public List<JokeEntity> getJokeList()
    {
        return jokeList;
    }

    public void setJokeList(List<JokeEntity> list)
    {
        this.jokeList = list;
    }
}
