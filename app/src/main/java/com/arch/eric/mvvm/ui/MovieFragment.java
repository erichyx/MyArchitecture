package com.arch.eric.mvvm.ui;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.widget.Toast;

import com.arch.eric.BR;
import com.arch.eric.R;
import com.arch.eric.data.local.MovieInfo;
import com.arch.eric.databinding.FragmentHomeBinding;
import com.arch.eric.mvvm.fetcher.Resource;
import com.arch.eric.mvvm.viewmodel.MovieViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends BaseFragment<FragmentHomeBinding, MovieViewModel> implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentHomeBinding mBinding;
    private MovieItemAdapter mMovieAdapter;
    private MovieViewModel mMovieViewModel;
    private static final String CITY = "厦门";

    private static final String TAG = "MovieFragment";

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

        subscribeUi(mMovieViewModel, true);
    }

    private void subscribeUi(MovieViewModel movieViewModel, boolean isInit) {
        movieViewModel.getShowingMovie(CITY, isInit).observe(this, listResource -> {
            switch (listResource.status) {
                case Resource.LOADING:
                    Log.d(TAG, "数据加载中：" + listResource.data);
                    mBinding.setIsRefreshing(true);
                    if (listResource.data != null) {
                        mMovieAdapter.setItems(listResource.data);
                    }
                    break;
                case Resource.SUCCESS:
                    if (listResource.data != null) {
                        Log.d(TAG, "数据完成：" + listResource.data);
                        mMovieAdapter.setItems(listResource.data);
                    }
                    break;
                case Resource.ERROR:
                    Log.d(TAG, "数据加载失败：" + listResource.message);
                default:
                    break;
            }
            mBinding.setIsRefreshing(false);
        });
    }

    @Override
    public void onRefresh() {
        subscribeUi(mMovieViewModel, true);
    }
}
