package com.example.todomanager05.ui.utils;

import android.app.Application;
import android.widget.Toast;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todomanager05.ui.room.DataBase;

import org.w3c.dom.Text;

public class App extends Application {
    public static App instance;
    private DataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DataBase dataBase = Room.databaseBuilder(this, DataBase.class,"tododatabase").allowMainThreadQueries().build();
    }
    public void showToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
    public static App getInstance(){
        return instance;
    }
    public DataBase getDataBase(){
        return dataBase;
    }
}
