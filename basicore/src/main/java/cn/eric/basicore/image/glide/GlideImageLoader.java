package cn.eric.basicore.image.glide;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import cn.eric.basicore.app.Configurator;


/**
 * Created by hyx on 2017/5/27.
 */

public class GlideImageLoader implements ImageLoader
{

    private RequestOptions mOptions;

    public GlideImageLoader(Context context) {
        mOptions = Configurator.get().getRequestOptions();
        init(context);
    }

    @Override
    public void init(Context context) {
    }

    @Override
    public void loadImage(Context context, String url, ImageView view) {
        Glide.with(context).load(url).apply(mOptions).into(view);
    }

    @Override
    public void loadImage(Context context, File file, ImageView view) {
        Glide.with(context).load(file).apply(mOptions).into(view);
    }

    @Override
    public void loadImage(Context context, @DrawableRes int drawableId, ImageView view) {
        Glide.with(context).load(drawableId).apply(mOptions).into(view);
    }

    @Override
    public void loadImage(Context context, String url, ImageView view, int width, int height) {
        RequestOptions requestOptions = mOptions.override(width, height);
        Glide.with(context).load(url).apply(requestOptions).into(view);
    }

    @Override
    public void loadImageEx(Context context, String url, ImageView view,
                            @DrawableRes int loadingRes, @DrawableRes int errorRes) {
        RequestOptions requestOptions = mOptions.placeholder(loadingRes).error(errorRes);
        Glide.with(context).load(url).apply(requestOptions).into(view);
    }

    @Override
    public void loadCircleImage(Context context, String url, ImageView view) {
        RequestOptions requestOptions = mOptions.circleCrop();
        Glide.with(context).load(url).apply(requestOptions).into(view);
    }

    @Override
    public void loadCircleImageEx(Context context, String url, ImageView view,
                                  @DrawableRes int loadingRes, @DrawableRes int errorRes) {
        RequestOptions requestOptions = mOptions.placeholder(loadingRes).error(errorRes).circleCrop();
        Glide.with(context).load(url).apply(requestOptions).into(view);
    }

    @Override
    public void loadImageNoMemCache(Context context, String url, ImageView view) {
        RequestOptions requestOptions = mOptions.skipMemoryCache(true);
        Glide.with(context).load(url).apply(requestOptions).into(view);
    }

    @Override
    public void loadImageNoMemCache(Context context, String url, ImageView view,
                                    @DrawableRes int loadingRes, @DrawableRes int errorRes) {
        RequestOptions requestOptions = mOptions.placeholder(loadingRes).error(errorRes)
                .skipMemoryCache(true);
        Glide.with(context).load(url).apply(requestOptions).into(view);
    }

    @Override
    public void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        // 清理磁盘缓存，需要在子线程中执行
        Glide.get(context).clearDiskCache();
    }

    @Override
    public File getDiskCacheDir(Context context) {
        return Glide.getPhotoCacheDir(context);
    }
}
