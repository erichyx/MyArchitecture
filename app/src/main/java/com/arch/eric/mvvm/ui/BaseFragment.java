package com.arch.eric.mvvm.ui;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

/**
 * Created by eric on 2018/5/26
 */
public abstract class BaseFragment extends Fragment {

    protected <T extends ViewModel> T getViewModel(Class<T> cls) {
        T t = ViewModelProviders.of(this).get(cls);
        if (LifecycleObserver.class.isAssignableFrom(cls)) {
            getLifecycle().addObserver((LifecycleObserver) t);
        }
        return t;
    }
}
