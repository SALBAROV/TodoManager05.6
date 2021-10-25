package com.example.todomanager05.ui.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todomanager05.ui.task.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM taskmodel")
    LiveData<List<TaskModel>> getALL();

    @Insert
    void insert(TaskModel taskModel);

    @Delete
    void delete(TaskModel taskModel);
}
