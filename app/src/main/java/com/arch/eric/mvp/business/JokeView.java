package com.arch.eric.mvp.business;

import com.arch.eric.entity.JokeEntity;
import com.arch.eric.mvp.view.BaseMvpView;

import java.util.List;

/**
 * Created by eric on 2018/1/6.
 */

public interface JokeView extends BaseMvpView {

    void showJokes(List<JokeEntity> data);
    void setLoadingIndicator(boolean active);
}
