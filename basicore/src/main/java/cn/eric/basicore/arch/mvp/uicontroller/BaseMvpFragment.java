package cn.eric.basicore.arch.mvp.uicontroller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.eric.basicore.arch.mvp.factory.PresenterFactory;
import cn.eric.basicore.arch.mvp.presenter.BaseMvpPresenter;
import cn.eric.basicore.arch.mvp.view.BaseMvpView;
import me.listenzz.navigation.AwesomeFragment;

/**
 * Created by eric on 2018/1/6.
 */

public abstract class BaseMvpFragment extends AwesomeFragment implements MvpControlBehavior {

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        initView();
        onPrepare();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public <V extends BaseMvpView, P extends BaseMvpPresenter<V>> P getPresenter(Class<P> cls, V view) {
        P presenter = PresenterFactory.create(cls, view);
        getLifecycle().addObserver(presenter);
        return presenter;
    }
}
