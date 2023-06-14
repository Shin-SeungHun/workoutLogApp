package com.ssh.workoutlogapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {


    private ArrayList<Item> itemData;
    private MyRecyclerViewClickListener mListener;

    ItemAdapter() {
    }

    ItemAdapter(ArrayList<Item> itemData) {
        this.itemData = itemData;
    }


    public interface MyRecyclerViewClickListener {
        void onItemClicked(int position);

        void onTitleClicked(int position);


        void onImageViewClicked(int position);

        void onButtonClicked(int position);
    }

    public void setOnClickListener(MyRecyclerViewClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    // 버튼의 상태를 설정하는 메서드


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder ItemViewHolder, @SuppressLint("RecyclerView") int position) {
        Item Item = itemData.get(position);

        ItemViewHolder.tvItemTitle.setText(Item.getItemTitle());

        ItemViewHolder.imgItem.setImageResource(Item.getItemImage());

        //아이템 클릭리스너
        ItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClicked(position);
                    mListener.onTitleClicked(position);

                    mListener.onImageViewClicked(position);
                    mListener.onButtonClicked(position);
                    if (position == 0) {
                        Intent intent = new Intent(v.getContext(), WorkoutLogActivity.class);
                        intent.putExtra("tvTodayText", Item.getItemTitle());
                        v.getContext().startActivity(intent);
                    } else if (position == 1) {
                        Intent intent = new Intent(v.getContext(), WorkoutLogActivity.class);
                        intent.putExtra("tvTodayText", Item.getItemTitle());
                        v.getContext().startActivity(intent);
                    } else if (position == 2) {
                        Intent intent = new Intent(v.getContext(), WorkoutLogActivity.class);
                        intent.putExtra("tvTodayText", Item.getItemTitle());
                        v.getContext().startActivity(intent);
                    } else if (position == 3) {
                        Intent intent = new Intent(v.getContext(), WorkoutLogActivity.class);
                        intent.putExtra("tvTodayText", Item.getItemTitle());
                        v.getContext().startActivity(intent);
                    } else if (position == 4) {
                        Intent intent = new Intent(v.getContext(), WorkoutLogActivity.class);
                        intent.putExtra("tvTodayText", Item.getItemTitle());
                        v.getContext().startActivity(intent);
                    } else if (position == 5) {
                        Intent intent = new Intent(v.getContext(), WorkoutLogActivity.class);
                        intent.putExtra("tvTodayText", Item.getItemTitle());
                        v.getContext().startActivity(intent);
                    } else if (position == 6) {
                        Intent intent = new Intent(v.getContext(), WorkoutLogActivity.class);
                        intent.putExtra("tvTodayText", Item.getItemTitle());
                        v.getContext().startActivity(intent);
                    } else if (position == 7) {
                        Intent intent = new Intent(v.getContext(), WorkoutLogActivity.class);
                        intent.putExtra("tvTodayText", Item.getItemTitle());
                        v.getContext().startActivity(intent);
                    } else if (position == 8) {
                        Intent intent = new Intent(v.getContext(), WorkoutLogActivity.class);
                        intent.putExtra("tvTodayText", Item.getItemTitle());
                        v.getContext().startActivity(intent);
                    }


                }

            }

        });
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        TextView tvItemTitle;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item);
            tvItemTitle = itemView.findViewById(R.id.tv_item_title);

        }
    }
}