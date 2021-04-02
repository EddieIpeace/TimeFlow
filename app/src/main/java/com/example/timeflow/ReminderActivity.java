package com.example.timeflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class ReminderActivity extends AppCompatActivity {
    private RecyclerView reminderRecycler;
    private Button setReminder;
    private EditText reminderInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        reminderRecycler = (RecyclerView) findViewById(R.id.reminder_recycler);
        setReminder = (Button) findViewById(R.id.set_reminder);
        reminderInput = (EditText) findViewById(R.id.reminder_input);

        initSetReminder();
        showReminderList();
    }

    private void initSetReminder() {
        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reminderInput.getText().toString().equals("")) {
                    Toast.makeText(ReminderActivity.this, "描述不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                addReminder();
            }
        });
    }

    private void addReminder() {
        Reminder reminder = new Reminder(reminderInput.getText().toString());
        reminder.save();
        showReminderList();
    }

    private void showReminderList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        reminderRecycler.setLayoutManager(linearLayoutManager);
        List<Reminder> reminders = DataSupport.findAll(Reminder.class);
        Log.e("eddie", "showReminderList: " + reminders.size());
        if (reminders.size() == 0) {
            return;
        }
        ReminderAdapter adapter = new ReminderAdapter(reminders);
        reminderRecycler.setAdapter(adapter);
        reminderRecycler.smoothScrollToPosition(reminders.size() - 1);
    }
}