package com.arch.eric.mvvm.ui;

import com.arch.eric.BR;
import com.arch.eric.R;
import com.arch.eric.databinding.MovieItemBinding;
import com.arch.eric.entity.MovieSubjectEntity.SubjectsBean;

/**
 * Created by eric on 2018/5/31
 */
public class MovieItemAdapter extends BaseItemBindingAdapter<SubjectsBean, MovieItemBinding> {

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.movie_recycle_item;
    }

    @Override
    protected int getVariableId() {
        return BR.movieSubject;
    }

    @Override
    protected void onBeforeBindItem(MovieItemBinding binding) {
        // 这里可以做一些额外的数据绑定操作（可选）
    }
}
