package com.example.timeflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "eddie";
    SimpleData simple_data;
    private Button time_click;
    private EditText write_sth;
    private RecyclerView event_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        event_list = (RecyclerView) findViewById(R.id.recycler_view);
        write_sth = (EditText)  findViewById(R.id.write_sth);
        simple_data = new SimpleData(getSharedPreferences("main_data", MODE_PRIVATE));
        time_click = (Button) findViewById(R.id.time_click);
        time_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long now_ts = new Date().getTime();
                addEvent();
                updateButtonText();
            }
        });
        updateButtonText();
        Connector.getDatabase();
        showEventList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void showEventList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        event_list.setLayoutManager(layoutManager);
        List<Event> events = DataSupport.findAll(Event.class);
        EventAdapter adapter = new EventAdapter(events);
        event_list.setAdapter(adapter);
        event_list.smoothScrollToPosition(events.size() - 1);
    }

    private void updateButtonText() {
        if (simple_data.isStart()) {
            time_click.setText("结束");
            write_sth.setEnabled(false);
        } else {
            time_click.setText("开始");
            write_sth.setEnabled(true);
        }
    }

    private void addEvent() {
        long now = new Date().getTime();
        if (simple_data.isStart()) {
            Event event = DataSupport.findLast(Event.class);
            event.setEnd_ts(now);
            event.save();
            write_sth.setText("");
        } else {
            Event event = new Event(now, 0, write_sth.getText().toString());
            event.save();
        }
        simple_data.changeStart();
        showEventList();
    }
}