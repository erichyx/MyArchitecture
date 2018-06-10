package cn.eric.basicore.image.glide;


import android.content.Context;
import android.support.annotation.IdRes;

/**
 * Created by hyx on 2017/5/15.
 */

public final class ImageLoaderManager {
    private static volatile ImageLoader sInstance;
    private static ImageFactory sFactory = new GlideFactory();

    private ImageLoaderManager() {
    }

    public static ImageLoader getImageLoader(Context context) {
        if (sInstance == null) {
            synchronized (ImageLoaderManager.class) {
                if (sInstance == null) {
                    sInstance = sFactory.create(context);
                }
            }
        }
        return sInstance;
    }

    public static void setFactory(ImageFactory factory) {
        if (factory == null) {
            throw new NullPointerException("ImageFactory factory == null");
        }

        synchronized (ImageLoaderManager.class) {
            sFactory = factory;
            sInstance = null;
        }
    }

    private static class GlideFactory implements ImageFactory {
        @Override
        public ImageLoader create(Context context) {
            return new GlideImageLoader(context);
        }
    }

    public interface ImageFactory {
        ImageLoader create(Context context);
    }
}
