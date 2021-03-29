package com.example.timeflow;

import android.text.Layout;
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
        if (duration < 10) {
            holder.eventId.setBackgroundResource(R.color.dodgerblue);
            holder.eventBeginTs.setBackgroundResource(R.color.dodgerblue);
            holder.eventEndTs.setBackgroundResource(R.color.dodgerblue);
            holder.eventDesc.setBackgroundResource(R.color.dodgerblue);
            holder.eventId.setHeight(100);
            holder.eventBeginTs.setHeight(100);
            holder.eventEndTs.setHeight(100);
            holder.eventDesc.setHeight(100);
        } else if (duration < 30) {
            holder.eventId.setBackgroundResource(R.color.green);
            holder.eventBeginTs.setBackgroundResource(R.color.green);
            holder.eventEndTs.setBackgroundResource(R.color.green);
            holder.eventDesc.setBackgroundResource(R.color.green);
            holder.eventId.setHeight(200);
            holder.eventBeginTs.setHeight(200);
            holder.eventEndTs.setHeight(200);
            holder.eventDesc.setHeight(200);
        } else {
            holder.eventId.setBackgroundResource(R.color.red);
            holder.eventBeginTs.setBackgroundResource(R.color.red);
            holder.eventEndTs.setBackgroundResource(R.color.red);
            holder.eventDesc.setBackgroundResource(R.color.red);
            holder.eventId.setHeight(300);
            holder.eventBeginTs.setHeight(300);
            holder.eventEndTs.setHeight(300);
            holder.eventDesc.setHeight(300);
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
