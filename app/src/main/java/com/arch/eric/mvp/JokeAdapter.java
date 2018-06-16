package com.arch.eric.mvp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arch.eric.R;
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false);
        return new JokeViewHolder(itemView);
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
        holder.mTvId.setText(entity.getId());
        holder.mTvCategory.setText(entity.getCategory());
        holder.mTvJoke.setText(entity.getJoke());
    }

    @Override
    public int getItemCount()
    {
        return mList == null ? 0 : mList.size();
    }

    static class JokeViewHolder extends RecyclerView.ViewHolder
    {
        final TextView mTvId;
        final TextView mTvCategory;
        final TextView mTvJoke;

        JokeViewHolder(View itemView)
        {
            super(itemView);
            mTvId = itemView.findViewById(R.id.tv_id);
            mTvCategory = itemView.findViewById(R.id.category);
            mTvJoke = itemView.findViewById(R.id.joke);
        }
    }
}
