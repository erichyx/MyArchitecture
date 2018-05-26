package com.arch.eric.common.image;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by hyx on 2017/5/15.
 */

public interface ImageLoader {
    void init(Context context);

    void loadImage(Context context, String url, ImageView view);

    void loadImageAdPage(Context context, String url, ImageView view);

    void loadImage(Context context, File file, ImageView view);

    void loadImage(Context context, @DrawableRes int drawableId, ImageView view);

    void loadImageGif(Context context, @DrawableRes int drawableId, ImageView view);

    void loadImageGif(Context context, String url, ImageView view);

    void loadImage(Context context, String url, ImageView view, int width, int height);

    void loadImageEx(Context context, String url, ImageView view, @DrawableRes int loadingRes, @DrawableRes int errorRes);

    void loadCircleImage(Context context, String url, ImageView view);

    void loadCircleImageEx(Context context, String url, ImageView view, @DrawableRes int loadingRes, @DrawableRes int errorRes);

    void loadImageNoMemCache(Context context, String url, ImageView view);

    void loadImageNoMemCache(Context context, String url, ImageView view, @DrawableRes int loadingRes, @DrawableRes int errorRes);

    void clearMemory(Context context);

    void clearDiskCache(Context context);

    File getDiskCacheDir(Context context);
}
