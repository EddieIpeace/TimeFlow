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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "eddie";
    SimpleData simple_data;
    private Button time_click;
    private EditText write_sth;
    private RecyclerView event_list;
    private TextView today_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        today_day = (TextView) findViewById(R.id.today_day);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today_str = format.format(calendar.getTime());
        today_str = "  " + today_str;
        today_day.setText(today_str);

        event_list = (RecyclerView) findViewById(R.id.recycler_view);
        write_sth = (EditText)  findViewById(R.id.write_sth);
        simple_data = new SimpleData(getSharedPreferences("main_data", MODE_PRIVATE));
        time_click = (Button) findViewById(R.id.time_click);
        time_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (write_sth.getText().toString().equals("") && !simple_data.isStart()) {
                    Toast.makeText(MainActivity.this, "描述不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
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
        long start_ts = Tool.getTodayStartTime().getTime();
        long end_ts = Tool.getTodayEndTime().getTime();
        Log.e(TAG, "showEventList: start:" + start_ts + " end:" + end_ts);
        List<Event> events = DataSupport.where("begin_ts >= ? and begin_ts <= ?", "" + start_ts, "" + end_ts).find(Event.class);
        if (0 == events.size()) {
            return;
        }
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