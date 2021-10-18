package com.example.todomanager05.ui.task;

import java.io.Serializable;

public class TaskModel implements Serializable {
    public int color;
    public String title;
    public String time;
    public String image;

    public TaskModel(int color, String title, String time, String image) {
        this.color = color;
        this.title = title;
        this.time = time;
        this.image = image;
    }
}
