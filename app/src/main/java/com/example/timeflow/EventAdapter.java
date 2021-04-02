package com.example.timeflow;

import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Event> mEventList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView eventId;
        TextView eventBeginTs;
        TextView eventEndTs;
        TextView eventDesc;

        public ViewHolder(View view) {
            super(view);
            eventId = (TextView) view.findViewById(R.id.event_id);
            eventBeginTs = (TextView) view.findViewById(R.id.event_begin_ts);
            eventEndTs = (TextView) view.findViewById(R.id.event_end_ts);
            eventDesc = (TextView) view.findViewById(R.id.event_desc);
        }

        public void setEventItemProps(int color, int height) {
            eventId.setBackgroundResource(color);
            eventBeginTs.setBackgroundResource(color);
            eventEndTs.setBackgroundResource(color);
            eventDesc.setBackgroundResource(color);
            eventId.setHeight(height);
            eventBeginTs.setHeight(height);
            eventEndTs.setHeight(height);
            eventDesc.setHeight(height);
        }
    }

    public EventAdapter(List<Event> eventList) {
        mEventList = eventList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = mEventList.get(position);
        long duration = (event.getEnd_ts() - event.getBegin_ts()) / 60000;
        if (event.getEnd_ts() == 0) {
            duration = 0;
        }
        if (duration < 10) {
            holder.setEventItemProps(R.color.dodgerblue, 100);
        } else if (duration < 30) {
            holder.setEventItemProps(R.color.green, 200);
        } else {
            holder.setEventItemProps(R.color.red, 300);
        }
        holder.eventId.setText("" + duration);
        holder.eventBeginTs.setText(Tool.dateToString(event.getBegin_ts(), "HH:mm"));
        holder.eventEndTs.setText(Tool.dateToString(event.getEnd_ts(), "HH:mm"));
        holder.eventDesc.setText(event.getDesc());
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

}
