package com.arch.eric.mvvm.ui;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arch.eric.mvvm.MvvmContact;

/**
 * Created by eric on 2018/5/29
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity
        implements MvvmContact<V, VM> {

    private V mViewDataBinding;
    protected VM mViewModel;

    protected <T extends ViewModel> T getViewModel(Class<T> cls) {
        T t = ViewModelProviders.of(this).get(cls);
        if (LifecycleObserver.class.isAssignableFrom(cls)) {
            getLifecycle().addObserver((LifecycleObserver) t);
        }
        return t;
    }

    @Override
    public V getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewModel = getViewModel();
        mViewDataBinding.setVariable(getViewModelId(), mViewModel);
    }
}
