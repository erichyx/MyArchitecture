package com.arch.eric.mvvm.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.arch.eric.BR;
import com.arch.eric.R;
import com.arch.eric.databinding.FragmentHomeBinding;
import com.arch.eric.mvvm.viewmodel.JokeViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvvmFragment extends BaseFragment<FragmentHomeBinding, JokeViewModel> implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentHomeBinding mBinding;
    private JokeAdapter mJokeAdapter;
    private JokeViewModel mJokeViewModel;

    public MvvmFragment() {
        // Required empty public constructor
    }

    public static MvvmFragment newInstance() {
        Bundle args = new Bundle();

        MvvmFragment fragment = new MvvmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBinding = getViewDataBinding();
        mBinding.setFragment(this);
        mJokeAdapter = new JokeAdapter();
        mBinding.recyclerView.setAdapter(mJokeAdapter);

        mJokeViewModel.setFailureCallback((reqTag, error) -> Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show());
        subscribeUi(mJokeViewModel);
    }

    private void subscribeUi(JokeViewModel jokeViewModel) {
        jokeViewModel.getJokes(20).observe(this, jokeEntities -> {
            mJokeAdapter.setList(jokeEntities);
            mBinding.setIsRefreshing(false);
        });
    }

    @Override
    public void onRefresh() {
        mJokeViewModel.getJokes(20);
    }
}
