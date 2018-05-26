package com.arch.eric.api;



import okhttp3.MediaType;

/**
 * Created by hyx on 2017/5/15.
 */

public class RequestBodyGenerator
{
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    /*
    public static RequestBody createRequestBody(BaseRequest request)
    {
        String content = GsonContext.getGson().toJson(new BaseRequestParam(request));
        return RequestBody.create(MEDIA_TYPE_JSON, content);
    }*/
}
