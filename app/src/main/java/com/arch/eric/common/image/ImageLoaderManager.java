package com.arch.eric.common.image;


import com.arch.eric.common.image.glide.GlideImageLoader;

/**
 * Created by hyx on 2017/5/15.
 */

public final class ImageLoaderManager
{
    private static volatile ImageLoader sInstance;
    private static ImageFactory sFactory = new GlideFactory();

    private ImageLoaderManager()
    {
    }

    public static ImageLoader getImageLoader()
    {
        if (sInstance == null)
        {
            synchronized (ImageLoaderManager.class)
            {
                if (sInstance == null)
                {
                    sInstance = sFactory.create();
                }
            }
        }
        return sInstance;
    }

    public static void setFactory(ImageFactory factory)
    {
        if (factory == null)
        {
            throw new NullPointerException("factory == null");
        }

        synchronized (ImageLoaderManager.class) {
            sFactory = factory;
            sInstance = sFactory.create();
        }
    }

    private static class GlideFactory implements ImageFactory
    {
        @Override
        public ImageLoader create()
        {
            return new GlideImageLoader();
        }
    }

    public interface ImageFactory {
        ImageLoader create();
    }
}
