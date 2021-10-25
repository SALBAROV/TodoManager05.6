package com.example.todomanager05.ui;


import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.todomanager05.R;
import com.example.todomanager05.databinding.ItemTaskBinding;
import com.example.todomanager05.ui.task.TaskModel;

import java.time.Instant;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<TaskModel> list = new ArrayList<>();
    ItemTaskBinding binding;
    OnItemClickListener onItemClickListener;

    public  void addList(ArrayList<TaskModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnLongClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;

    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.onFill(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        ItemTaskBinding binding;

        public TaskViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void onFill(TaskModel model) {
            binding.titleTv.setText(model.title);
            binding.timeText.setText(model.time);
            binding.leftColorV.setBackgroundColor(model.color);
            ActivityResultLauncher<String> mGetContent = null;
            Glide.with(binding.taskIm).load(model.image).centerCrop().into(binding.taskIm);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick( model);
                    return false;
                }
            });
        }
    }
}

