package com.ssh.workoutlogapp;

import static com.ssh.workoutlogapp.WorkoutMainActivity.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> implements ItemTouchHelperListener {

    public ArrayList<WorkoutLog> workoutLogs;
    SQLiteDatabase db;

    public WorkoutAdapter(ArrayList<WorkoutLog> workoutLogs) {
        this.workoutLogs = workoutLogs;
        this.db = db;
    }

    private MyRecyclerViewClickListener mListener;

    public void setOnClickListener(MyRecyclerViewClickListener listener) {
        this.mListener = listener;
    }

    public interface MyRecyclerViewClickListener {
        void onItemClicked(int position);

        void onDateClicked(int position);

        void onKgClicked(int position);

        void onRepsClicked(int position);

        void onSetsClicked(int position);

        void onNameClicked(int position);


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_workout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        WorkoutLog item = workoutLogs.get(position);

        holder.tvDate.setText(item.getDate());
        holder.tvKg.setText(item.getKg());
        holder.tvReps.setText(item.getReps());
        holder.tvSets.setText(item.getSets());
        holder.tvName.setText(item.getName());


        if (mListener != null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(pos);
                }
            });
            holder.tvDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onDateClicked(pos);
                }
            });
            holder.tvKg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onKgClicked(pos);
                }
            });
            holder.tvReps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onRepsClicked(pos);
                }
            });
            holder.tvSets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onSetsClicked(pos);
                }
            });
            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onNameClicked(pos);
                }
            });
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClicked(position);
                    mListener.onDateClicked(position);
                    mListener.onKgClicked(position);
                    mListener.onRepsClicked(position);
                    mListener.onSetsClicked(position);
                    mListener.onNameClicked(position);
                    if (position == 0) {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, RecyclerViewExercise.class);
                        context.startActivity(intent);
                    } else if (position == 1) {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, RecyclerViewExercise.class);
                        context.startActivity(intent);
                    } else if (position == 2) {

                    } else if (position == 3) {

                    } else if (position == 4) {

                    } else if (position == 5) {

                    } else if (position == 6) {

                    } else if (position == 7) {

                    } else if (position == 8) {

                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return workoutLogs.size();
    }


    @Override
    public boolean onItemMove(int from_position, int to_position) {
        //이동할 객체 저장
        WorkoutLog workoutLog = workoutLogs.get(from_position);
        //이동할 객체 삭제
        workoutLogs.remove(from_position);
        //이동하고 싶은 position에 추가
        workoutLogs.add(to_position, workoutLog);

        //Adapter에 데이터 이동알림
        notifyItemMoved(from_position, to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        if (position < workoutLogs.size()) { // position 값이 workoutLogs 리스트의 크기보다 작은지 확인
            //workoutLogs.remove(position);
            removeItem(position);
            //notifyItemRemoved(position);
        }
    }


    public void removeItem(int position) {
        // DB에서 아이템 삭제
        db = helper.getWritableDatabase();
        // 삭제할 아이템의 인덱스를 구한다
        int itemIdx = workoutLogs.get(position).getId();
        // workout 테이블에서 해당 아이템을 삭제한다
        db.delete("workout", "idx='" + itemIdx + "'", null);
        // 데이터 리스트에서 아이템 삭제
        workoutLogs.remove(position);
        Log.d("db", itemIdx + "가 정상적으로 삭제 되었습니다.");
        // Adapter에 데이터 삭제 알림
        notifyItemRemoved(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvName, tvKg, tvSets, tvReps, tvKg2, tvSets2, tvReps2;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvName = itemView.findViewById(R.id.tvName);
            tvKg = itemView.findViewById(R.id.tvKg);
            tvSets = itemView.findViewById(R.id.tvSets);
            tvReps = itemView.findViewById(R.id.tvReps);

        }
    }


    //리스트 삭제 이벤트
//    public void remove(int position) {
//        try {
//            workoutLogs.remove(position);
//            notifyDataSetChanged();
//        } catch (IndexOutOfBoundsException e) {
//            e.printStackTrace();
//        }
//    }
//    public void remove(int position) {
//        try {
//            int id = workoutLogs.get(position).getId();
//            // 데이터베이스에서 해당 아이템을 삭제
//            helper.deleteWorkoutLog(id);
//            workoutLogs.remove(position);
//            notifyDataSetChanged();
//        } catch (IndexOutOfBoundsException e) {
//            e.printStackTrace();
//        }
//    }

}