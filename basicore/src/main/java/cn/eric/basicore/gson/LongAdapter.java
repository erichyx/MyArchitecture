package cn.eric.basicore.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by hyx on 2017/6/9.
 */

public class LongAdapter implements JsonSerializer<Long>, JsonDeserializer<Long>
{
    @Override
    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if (json.getAsString().equals("") || json.getAsString().equals("null"))
        {   //定义为int类型,如果后台返回""或者null,则返回0L
            return 0L;
        }

        try
        {
            return json.getAsLong();
        } catch (NumberFormatException e)
        {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context)
    {
        return new JsonPrimitive(src);
    }
}
