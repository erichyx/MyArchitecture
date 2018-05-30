package com.arch.eric.mvvm;

import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

/**
 * Created by eric on 2018/5/30
 */
public interface MvvmContact<V extends ViewDataBinding, VM extends ViewModel> {
    @LayoutRes
    int getLayoutId();

    int getViewModelId();

    VM getViewModel();

    V getViewDataBinding();
}
