package com.arch.eric.mvvm.ui;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arch.eric.mvvm.MvvmContact;

/**
 * Created by eric on 2018/5/26
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends ViewModel> extends Fragment
        implements MvvmContact<V,VM> {

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mViewModel != null) {
            mViewDataBinding.setVariable(getViewModelId(), mViewModel);
            mViewDataBinding.executePendingBindings();
        }
    }
}
