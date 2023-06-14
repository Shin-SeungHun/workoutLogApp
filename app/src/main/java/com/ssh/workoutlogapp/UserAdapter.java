package com.ssh.workoutlogapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public ArrayList<User> user;

    public UserAdapter(ArrayList<User> user) {
        this.user = user;
    }

    public interface MyRecyclerViewClickListener {
        void onIdClicked(int position);
        void onPwClicked(int position);
        void onNameClicked(int position);
        void onHpClicked(int position);
        void onBirthClicked(int position);
        void onGenderClicked(int position);
        void onAddrClicked(int position);
        void onItemLongClicked(int position);

    }


    private MyRecyclerViewClickListener mListener;

    public void setOnClickListener(MyRecyclerViewClickListener listener) {
        this.mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_user_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        User item = user.get(position);

        holder.tvId.setText(item.getId());
        holder.tvPw.setText(item.getPw());
        holder.tvName.setText(item.getName());
        holder.tvHp.setText(item.getHp());
        holder.tvBirth.setText(item.getBirth());
        holder.tvGender.setText(item.getGender());
        holder.tvAddr.setText(item.getAddr());


        if (mListener != null) {
            final int pos = position;

            holder.tvId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onIdClicked(pos);
                }
            });

            holder.tvPw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onPwClicked(pos);
                }
            });

            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onNameClicked(pos);
                }
            });

            holder.tvHp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onHpClicked(pos);
                }
            });

            holder.tvBirth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onBirthClicked(pos);
                }
            });

            holder.tvGender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onGenderClicked(pos);
                }
            });

            holder.tvAddr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onAddrClicked(pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClicked(holder.getAdapterPosition());
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvPw, tvName, tvHp, tvBirth, tvGender, tvAddr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvPw = itemView.findViewById(R.id.tvPw);
            tvName = itemView.findViewById(R.id.tvName);
            tvHp = itemView.findViewById(R.id.tvHp);
            tvBirth = itemView.findViewById(R.id.tvBirth);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvAddr = itemView.findViewById(R.id.tvAddr);

        }
    }

    //리스트 삭제 이벤트
    public void remove(int position) {
        try {
            user.remove(position);
            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}