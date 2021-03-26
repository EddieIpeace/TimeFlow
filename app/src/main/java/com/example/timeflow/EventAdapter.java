package com.example.timeflow;

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
        holder.eventId.setText("" + event.getId());
        holder.eventBeginTs.setText("" + event.getBegin_ts());
        holder.eventEndTs.setText("" + event.getEnd_ts());
        holder.eventDesc.setText(event.getDesc());
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }
}
