package com.example.timeflow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {
    private List<Reminder> reminder_list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        Button reminder_del;
        TextView reminder_desc;
        TextView reminder_ts;
        Reminder reminder_ref;
        public ViewHolder(View view) {
            super(view);
            reminder_desc = (TextView) view.findViewById(R.id.reminder_desc);
            reminder_ts = (TextView) view.findViewById(R.id.reminder_ts);
            reminder_del = (Button) view.findViewById(R.id.reminder_del);
            reminder_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reminder_ref.setStatus(1);
                    reminder_ref.save();
                }
            });
        }
    }

    public ReminderAdapter(List<Reminder> reminder_list) {
        this.reminder_list = reminder_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.reminder_ref = reminder_list.get(position);
        holder.reminder_desc.setText(holder.reminder_ref.getDesc());
        holder.reminder_ts.setText("" + holder.reminder_ref.getRemind_ts());

    }

    @Override
    public int getItemCount() {
        return reminder_list.size();
    }
}
