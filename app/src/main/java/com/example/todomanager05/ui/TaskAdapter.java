package com.example.todomanager05.ui;


import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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

    ArrayList<TaskModel> list;
    ItemTaskBinding binding;
    

    public TaskAdapter(ArrayList<TaskModel> list) {
        this.list = list;
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
            Glide.with(binding.taskIm).load(R.drawable.ic_menu_camera).centerCrop().into(binding.taskIm);

        }
    }
}

