package com.arch.eric.mvp;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arch.eric.R;
import com.arch.eric.databinding.JokeItemBinding;
import com.arch.eric.entity.JokeEntity;

import java.util.List;


/**
 * Created by eric on 2017/11/12.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeViewHolder>
{
    private List<JokeEntity> mList;
    @Override
    public JokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        JokeItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.joke_item, parent, false);
        return new JokeViewHolder(binding);
    }

    public void setList(List<JokeEntity> list)
    {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(JokeViewHolder holder, int position)
    {
        JokeEntity entity = mList.get(position);
        holder.binding.setJokeItem(entity);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount()
    {
        return mList == null ? 0 : mList.size();
    }

    static class JokeViewHolder extends RecyclerView.ViewHolder
    {
        private JokeItemBinding binding;

        public JokeViewHolder(JokeItemBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
