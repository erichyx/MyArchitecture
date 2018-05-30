package com.arch.eric.mvvm.ui;

import com.arch.eric.BR;
import com.arch.eric.R;
import com.arch.eric.databinding.ActivityMvvmBinding;
import com.arch.eric.mvvm.viewmodel.JokeViewModel;

public class MvvmActivity extends BaseActivity<ActivityMvvmBinding, JokeViewModel> {

    private JokeViewModel mJokeViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvvm;
    }

    @Override
    public int getViewModelId() {
        return BR.viewModel;
    }

    @Override
    public JokeViewModel getViewModel() {
        mJokeViewModel = getViewModel(JokeViewModel.class);
        return mJokeViewModel;
    }
}
