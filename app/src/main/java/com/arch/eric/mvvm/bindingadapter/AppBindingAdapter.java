package com.arch.eric.mvvm.bindingadapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arch.eric.data.local.MovieSubjectEntity.RatingBean;

import cn.eric.basicore.image.glide.ImageLoaderManager;

/**
 * Created by eric on 2018/5/31
 */
public class AppBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        ImageLoaderManager.getImageLoader(view.getContext()).loadImage(view.getContext(), url, view);
    }

    @BindingAdapter("android:rating")
    public static void rating(RatingBar ratingBar, RatingBean ratingBean) {
        ratingBar.setRating((float) ratingBean.getAverage() * ratingBar.getNumStars() / ratingBean.getMax());
    }

    @BindingAdapter("jumpUrl")
    public static void jumpUrl(TextView view, String url) {
        /*Context context = view.getContext();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }*/
    }
}
