package com.arch.eric.mvvm.ui;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arch.eric.R;
import com.arch.eric.databinding.FragmentHomeBinding;
import com.arch.eric.mvvm.viewmodel.JokeViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MvvmFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener
{
    private FragmentHomeBinding mBinding;
    private JokeAdapter mJokeAdapter;
    private JokeViewModel mJokeViewModel;

    public MvvmFragment()
    {
        // Required empty public constructor
    }

    public static MvvmFragment newInstance()
    {
        Bundle args = new Bundle();

        MvvmFragment fragment = new MvvmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        mBinding.setFragment(this);
        mJokeAdapter = new JokeAdapter();
        mBinding.recyclerView.setAdapter(mJokeAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        mJokeViewModel = getViewModel(JokeViewModel.class);
        mJokeViewModel.setFailureCallback("ReqJokeList", (reqTag, error) -> {
            if ("ReqJokeList".equals(reqTag)) {
                Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
            }
        });
        subscribeUi(mJokeViewModel);
    }

    private void subscribeUi(JokeViewModel jokeViewModel)
    {
        jokeViewModel.getJokes(20).observe(this, jokeEntities -> {
            mJokeAdapter.setList(jokeEntities);
            mBinding.setIsRefreshing(false);
        });
    }

    @Override
    public void onRefresh()
    {
        mJokeViewModel.getJokes(20);
    }
}
