package com.arch.eric.mvvm;

import com.arch.eric.BR;
import com.arch.eric.R;
import com.arch.eric.databinding.ActivityMvvmBinding;

import cn.eric.basicore.arch.mvvm.uicontroller.BaseActivity;

public class MvvmActivity extends BaseActivity<ActivityMvvmBinding, MovieViewModel> {

    private MovieViewModel mMovieViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvvm;
    }

    @Override
    public int getViewModelId() {
        return BR.viewModel;
    }

    @Override
    public MovieViewModel getViewModel() {
        mMovieViewModel = getViewModel(MovieViewModel.class);
        return mMovieViewModel;
    }
}
