package com.arch.eric.mvp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cn.eric.basicore.arch.mvvm.uicontroller.BottomTabFragment;
import com.arch.eric.R;
import com.arch.eric.entity.JokeEntity;

import cn.eric.basicore.arch.mvp.uicontroller.BaseMvpFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by eric on 2018/1/7.
 */
public class MvpFragment extends BaseMvpFragment implements JokeView,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private JokeAdapter mJokeAdapter;
    private JokePresenter jokePresenter;

    public static MvpFragment newInstance(int sceneId) {

        Bundle args = new Bundle();
        args.putString(BottomTabFragment.ARGS_SCENE_ID, sceneId + "");
        MvpFragment fragment = new MvpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mvp;
    }

    @Override
    public void initView() {
        mJokeAdapter = new JokeAdapter();
        mRecyclerView.setAdapter(mJokeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSrl.setOnRefreshListener(this);
    }

    @Override
    public void onPrepare() {
        jokePresenter = getPresenter(JokePresenter.class, this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        jokePresenter.fetchJokes(20);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mSrl.setRefreshing(active);
    }

    @Override
    public void showJokes(List<JokeEntity> data) {
        mJokeAdapter.setList(data);
    }
}
