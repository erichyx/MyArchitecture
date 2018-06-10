package com.arch.eric.mvvm;

import com.arch.eric.BR;
import com.arch.eric.R;
import com.arch.eric.data.local.MovieInfo;
import com.arch.eric.databinding.MovieItemBinding;
import com.arch.eric.data.local.MovieSubjectEntity.SubjectsBean;

import cn.eric.basicore.arch.mvvm.uicontroller.BaseItemBindingAdapter;

/**
 * Created by eric on 2018/5/31
 */
public class MovieItemAdapter extends BaseItemBindingAdapter<MovieInfo, MovieItemBinding> {

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.movie_recycle_item;
    }

    @Override
    protected int getVariableId() {
        return BR.movieInfo;
    }

    @Override
    protected void onBeforeBindItem(MovieItemBinding binding) {
        // 这里可以做一些额外的数据绑定操作（可选）
    }
}
