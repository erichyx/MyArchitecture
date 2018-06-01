package com.arch.eric.common.image.glide;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.arch.eric.app.BasicApp;
import com.arch.eric.common.image.ImageLoader;

import java.io.File;


/**
 * Created by hyx on 2017/5/27.
 */

public class GlideImageLoader implements ImageLoader
{
    public GlideImageLoader() {
        init(BasicApp.getContext());
    }

    @Override
    public void init(Context context) {
    }

    @Override
    public void loadImage(Context context, String url, ImageView view) {
        GlideApp.with(context).load(url).defaultOpts().into(view);
    }

    @Override
    public void loadImageAdPage(Context context, String url, ImageView view) {
        GlideApp.with(context).load(url).into(view);
    }

    @Override
    public void loadImage(Context context, File file, ImageView view) {
        GlideApp.with(context).load(file).defaultOpts().into(view);
    }

    @Override
    public void loadImage(Context context, @DrawableRes int drawableId, ImageView view) {
        GlideApp.with(context).load(drawableId).defaultOpts().into(view);
    }

    @Override
    public void loadImageGif(Context context, @DrawableRes int drawableId, ImageView view) {
        GlideApp.with(context).asGif().load(drawableId).defaultOpts().into(view);
    }

    @Override
    public void loadImageGif(Context context, String url, ImageView view) {
        GlideApp.with(context).asGif().load(url).defaultOpts().into(view);
    }

    @Override
    public void loadImage(Context context, String url, ImageView view, int width, int height) {
        GlideApp.with(context).load(url).defaultOpts().override(width, height).into(view);
    }

    @Override
    public void loadImageEx(Context context, String url, ImageView view,
                            @DrawableRes int loadingRes, @DrawableRes int errorRes) {
        GlideApp.with(context).load(url).placeholder(loadingRes).error(errorRes).into(view);
    }

    @Override
    public void loadCircleImage(Context context, String url, ImageView view) {
        GlideApp.with(context).load(url).defaultOpts()
                .circleCrop().into(view);
    }

    @Override
    public void loadCircleImageEx(Context context, String url, ImageView view,
                                  @DrawableRes int loadingRes, @DrawableRes int errorRes) {
        GlideApp.with(context).load(url).placeholder(loadingRes).error(errorRes)
                .circleCrop().into(view);
    }

    @Override
    public void loadImageNoMemCache(Context context, String url, ImageView view) {
        GlideApp.with(context).load(url).defaultOpts().skipMemoryCache(true).into(view);
    }

    @Override
    public void loadImageNoMemCache(Context context, String url, ImageView view,
                                    @DrawableRes int loadingRes, @DrawableRes int errorRes) {
        GlideApp.with(context).load(url).defaultOpts().skipMemoryCache(true)
                .placeholder(loadingRes).error(errorRes).into(view);
    }

    @Override
    public void clearMemory(Context context) {
        GlideApp.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        // 清理磁盘缓存，需要在子线程中执行
        GlideApp.get(context).clearDiskCache();
    }

    @Override
    public File getDiskCacheDir(Context context) {
        return GlideApp.getPhotoCacheDir(context);
    }
}
