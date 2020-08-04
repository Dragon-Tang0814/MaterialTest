package com.example.materialtest;

import android.content.Context;

import android.content.Intent;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;



import java.util.List;

public class PirateAdapter extends RecyclerView.Adapter<PirateAdapter.ViewHolder> {
    private Context mContext;
    private List<Pirate> mPirateList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView pirateImage;
        TextView pirateName;
        public ViewHolder( View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            pirateImage = itemView.findViewById(R.id.pirate_image);
            pirateName = itemView.findViewById(R.id.pirate_name);
        }
    }
    public PirateAdapter(List<Pirate> pirateList) {
        this.mPirateList = pirateList;
    }

    /**
     * 创建ViewHolder实例的
     * 1.将布局加载进来
     * 2.返回实例
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.pirate_item, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Pirate pirate=mPirateList.get(position);
                Intent intent=new Intent(mContext,PirateActivity.class);
                intent.putExtra(PirateActivity.PIRATE_NAME,pirate.getName());
                intent.putExtra(PirateActivity.PIRATE_IMAGE_ID,pirate.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }
    /**
     * 对子项进行赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pirate pirate = mPirateList.get(position);
        holder.pirateName.setText(pirate.getName());
        Glide.with(mContext).load(pirate.getImageId()).into(holder.pirateImage);
    }
    @Override
    public int getItemCount() {
        return mPirateList.size();
    }
}