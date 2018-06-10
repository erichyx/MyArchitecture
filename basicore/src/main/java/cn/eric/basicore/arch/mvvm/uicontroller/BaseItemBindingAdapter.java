package cn.eric.basicore.arch.mvvm.uicontroller;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 2018/5/31
 */
public abstract class BaseItemBindingAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter {
    protected List<T> mItems;

    public BaseItemBindingAdapter() {
        mItems = new ArrayList<>();
    }

    public void setItems(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutResId(viewType), parent, false);
        return new BaseBindingViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        B binding = DataBindingUtil.getBinding(holder.itemView);
        onBeforeBindItem(binding);
        onBindItem(binding, mItems.get(position));
    }

    // 子类根据需要重写该方法
    protected void onBeforeBindItem(B binding) {

    }

    protected abstract @LayoutRes
    int getLayoutResId(int viewType);

    protected abstract int getVariableId();

    protected void onBindItem(B binding, T item) {
        binding.setVariable(getVariableId(), item);
        binding.executePendingBindings();
    }

    public static class BaseBindingViewHolder extends RecyclerView.ViewHolder {
        public BaseBindingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
