package com.ssh.workoutlogapp;

import static com.ssh.workoutlogapp.RecyclerViewMemo.helper;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> implements ItemTouchHelperListener {

    public ArrayList<Memo> memos;

    SQLiteDatabase db;

    public MemoAdapter(ArrayList<Memo> memos) {
        this.memos = memos;
        this.db = db;
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        //이동할 객체 저장
        Memo memo = memos.get(from_position);
        //이동할 객체 삭제
        memos.remove(from_position);
        //이동하고 싶은 position에 추가
        memos.add(to_position, memo);

        //Adapter에 데이터 이동알림
        notifyItemMoved(from_position, to_position);

        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        if (position < memos.size()) { // position 값이 workoutLogs 리스트의 크기보다 작은지 확인
            //workoutLogs.remove(position);
            removeItem(position);
            //notifyItemRemoved(position);
        }
    }

    public void removeItem(int position) {
        // DB에서 아이템 삭제
        db = helper.getWritableDatabase();
        // 삭제할 아이템의 인덱스를 구한다
        int itemIdx = memos.get(position).getId();
        // workout 테이블에서 해당 아이템을 삭제한다
        db.delete("memo", "idx='" + itemIdx + "'", null);
        // 데이터 리스트에서 아이템 삭제
        memos.remove(position);
        Log.d("db", itemIdx + "가 정상적으로 삭제 되었습니다.");
        // Adapter에 데이터 삭제 알림
        notifyItemRemoved(position);
    }

    public interface MyRecyclerViewClickListener {
        void onContentClicked(int position);


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
                .inflate(R.layout.activity_memo_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Memo item = memos.get(position);

        holder.tvDate.setText(item.getDate());
        holder.tvContent.setText(item.getContent());



        if (mListener != null) {
            final int pos = position;
            holder.tvContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onContentClicked(pos);
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
        return memos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvContent = itemView.findViewById(R.id.tvContent);


        }
    }

    //리스트 삭제 이벤트
    public void remove(int position) {
        try {
            memos.remove(position);
            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}