package com.example.user.hansamustada_1202144142_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView list_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        list_item = (ListView)findViewById(R.id.list_item);

        LoadTasklist();
    }

    private void LoadTasklist(){
        ArrayList<String> taskList = dbHelper.getTaskList();
        if (mAdapter==null){
            mAdapter = new ArrayAdapter<String>(this,R.layout.row,R.id.task_title,taskList);
            list_item.setAdapter(mAdapter);
        }
        else{
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void view(View view) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
        View parent = (View)view.getParent();
        TextView tasktextView = (TextView)findViewById(R.id.task_title);
        String task = String.valueOf(tasktextView.getText());
        dbHelper.deleteTask(task);
        LoadTasklist();

    }
}
