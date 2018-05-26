package com.arch.eric.common.image.glide;

import android.content.Context;

import com.arch.eric.utils.OkHttp3Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Created by hyx on 2017/5/31.
 */

@GlideModule
public final class MyAppGlideModule extends AppGlideModule
{
    @Override
    public void registerComponents(Context context, Glide glide, Registry registry)
    {
        super.registerComponents(context, glide, registry);
        registry.replace(GlideUrl.class, InputStream.class,
                new OkHttpUrlLoader.Factory(OkHttp3Utils.getOkHttpClientInstance(context)));
    }

    @Override
    public boolean isManifestParsingEnabled()
    {
        return false;
    }
}
