package com.example.todomanager05.ui.task;

import java.io.Serializable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaskModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public int color;
    public String title;
    public String time;
    public String  image;


    public TaskModel(int color, String title, String time, String  image) {
        this.color = color;
        this.title = title;
        this.time = time;
        this.image = image;
    }
}
