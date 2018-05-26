package com.arch.eric.common.image.glide;

import android.annotation.SuppressLint;

import com.arch.eric.R;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by hyx on 2017/5/31.
 */

@GlideExtension
public class MyGlideExtension {
    private MyGlideExtension() {
    }

    @SuppressLint("CheckResult")
    @GlideOption
    public static void defaultOpts(RequestOptions options) {
        options.placeholder(R.drawable.ic_stub).error(R.drawable.ic_error);
    }

}
