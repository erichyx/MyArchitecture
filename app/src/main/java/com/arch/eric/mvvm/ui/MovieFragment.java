package com.arch.eric.mvvm.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.widget.Toast;

import com.arch.eric.BR;
import com.arch.eric.R;
import com.arch.eric.databinding.FragmentHomeBinding;
import com.arch.eric.mvvm.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends BaseFragment<FragmentHomeBinding, MovieViewModel> implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentHomeBinding mBinding;
    private MovieItemAdapter mMovieAdapter;
    private MovieViewModel mMovieViewModel;
    private static final String CITY = "厦门";

    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment newInstance() {
        Bundle args = new Bundle();

        MovieFragment fragment = new MovieFragment();
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
    public MovieViewModel getViewModel() {
        mMovieViewModel = getViewModel(MovieViewModel.class);
        return mMovieViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBinding = getViewDataBinding();
        mBinding.setFragment(this);
        mMovieAdapter = new MovieItemAdapter();
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mBinding.recyclerView.setAdapter(mMovieAdapter);

        mMovieViewModel.setFailureCallback((reqTag, error) -> Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show());
        subscribeUi(mMovieViewModel);
    }

    private void subscribeUi(MovieViewModel movieViewModel) {
        movieViewModel.getShowingMovie(CITY).observe(this, subjectsBeans -> {
            mMovieAdapter.setItems(subjectsBeans);
            mBinding.setIsRefreshing(false);
        });
    }

    @Override
    public void onRefresh() {
        subscribeUi(mMovieViewModel);
    }
}
