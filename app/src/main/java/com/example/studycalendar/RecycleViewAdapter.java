package com.example.studycalendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studycalendar.database.StudentTaskDatabase;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private final List<String> taskNames;
    private final List<String> taskDescription;
    private final List<String> taskDate;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    RecycleViewAdapter(Context context, List<String> taskNames, List<String> taskDescription,
                       List<String> taskDate) {
        this.mInflater = LayoutInflater.from(context);
        this.taskNames = taskNames;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleview_item, parent, false);
        return new ViewHolder(view);
    }

    Context context;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    StudentTaskDatabase db = StudentTaskDatabase.getInstance(context);

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String taskName = taskNames.get(position);
        String taskType = taskDescription.get(position);
        String taskDate1 = taskDate.get(position);
        holder.nameTextView.setText(taskName);
        holder.taskTypeTextView.setText(taskType);
        holder.dateTextView.setText(taskDate1);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditTaskActivity.class);
                i.putExtra("id", holder.getAdapterPosition());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(() ->
                        db.activityDao().deleteActivity(db.activityDao().getActivityList().get(
                                holder.getAdapterPosition()
                        ))

                );
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //Temporary HotFix
                context.startActivity(i);
            }
        });
        holder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailedTaskActivity.class);
                i.putExtra("id", holder.getAdapterPosition());
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return taskNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView taskTypeTextView;
        TextView dateTextView;
        Button btnEdit;
        Button btnDelete;
        Button btnInfo;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvSubjectName);
            taskTypeTextView = itemView.findViewById(R.id.tvTaskDescription);
            dateTextView = itemView.findViewById(R.id.tvTaskDueDate);
            btnEdit = itemView.findViewById(R.id.btnEditTask);
            btnDelete = itemView.findViewById(R.id.btnDeleteTask);
            btnInfo = itemView.findViewById(R.id.btnInfo);

          //  itemView.setBackgroundColor(Color.rgb(255, 0, 0));

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    String getItem(int id) {
        return taskNames.get(id);
    }


    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}