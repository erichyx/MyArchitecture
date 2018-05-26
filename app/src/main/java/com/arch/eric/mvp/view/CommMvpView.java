package com.arch.eric.mvp.view;

/**
 * Created by eric on 2018/5/25
 */
public interface CommMvpView<T> extends BaseMvpView {
    void setLoadingIndicator(boolean active);
    void onSucess(T data);
    void onFailure(String error);
}
