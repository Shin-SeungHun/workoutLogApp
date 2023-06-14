package com.ssh.workoutlogapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class SubItemAdapter extends RecyclerView.Adapter<SubItemAdapter.SubItemViewHolder> {


    private ArrayList<SubItem> itemData;
    private MyRecyclerViewClickListener mListener;

    SubItemAdapter() {
    }

    SubItemAdapter(ArrayList<SubItem> itemData) {
        this.itemData = itemData;
    }


    public interface MyRecyclerViewClickListener {
        void onItemClicked(int position);

        void onTitleClicked(int position);

        void onContentClicked(int position);

        void onImageViewClicked(int position);

        void onButtonClicked(int position);
    }

    public void setOnClickListener(MyRecyclerViewClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_sub_item, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    // 버튼의 상태를 설정하는 메서드


    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder subItemViewHolder, @SuppressLint("RecyclerView") int position) {
        SubItem subItem = itemData.get(position);

        subItemViewHolder.tvSubItemTitle.setText(subItem.getSubItemTitle());
        subItemViewHolder.tvSubItemTitle2.setText(subItem.getSubItemDesc());
        subItemViewHolder.imgSubItem.setImageResource(subItem.getSubItemImage());

        //아이템 클릭리스너
        subItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = subItemViewHolder.getBindingAdapterPosition();
                if (mListener != null && position != RecyclerView.NO_POSITION) {
                    mListener.onItemClicked(position);
                    mListener.onTitleClicked(position);
                    mListener.onContentClicked(position);
                    mListener.onImageViewClicked(position);
                    mListener.onButtonClicked(position);
                    switch (position) {
                        case 0:
                            Context context = v.getContext();
                            Intent intent = new Intent(context, Advertisement.class);
                            context.startActivity(intent);
                            break;
                        case 1:
                            context = v.getContext();
                            intent = new Intent(context, WorkoutMainActivity.class);
                            context.startActivity(intent);
                            break;
                        case 2:
                            // 아이템 2 클릭 시 동작 구현
                            break;
                        case 3:
                            // 아이템 3 클릭 시 동작 구현
                            break;
                        case 4:
                            // 아이템 4 클릭 시 동작 구현
                            break;
                        case 5:
                            // 아이템 5 클릭 시 동작 구현
                            break;
                        case 6:
                            // 아이템 6 클릭 시 동작 구현
                            break;
                        case 7:
                            // 아이템 7 클릭 시 동작 구현
                            break;
                        case 8:
                            // 아이템 8 클릭 시 동작 구현
                            break;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    static class SubItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSubItem;
        TextView tvSubItemTitle;
        TextView tvSubItemTitle2;

        public SubItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSubItem = itemView.findViewById(R.id.img_sub_item);
            tvSubItemTitle = itemView.findViewById(R.id.tv_sub_item_title);
            tvSubItemTitle2 = itemView.findViewById(R.id.tv_sub_item_title2);
        }

        public int getBindingAdapterPosition() {
            return 0;
        }
    }
}