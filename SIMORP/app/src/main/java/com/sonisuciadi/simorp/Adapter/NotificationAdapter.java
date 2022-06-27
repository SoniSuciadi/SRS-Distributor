package com.sonisuciadi.simorp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sonisuciadi.simorp.Model.mNotification;
import com.sonisuciadi.simorp.Model.mOrder;
import com.sonisuciadi.simorp.NotificationActivity;
import com.sonisuciadi.simorp.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder> {
    Context context;
    List<mNotification> mdata;
    private NotificationAdapter.OnItemClickListener onItemClickListener;

    public NotificationAdapter(Context context, List<mNotification> mdata, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.mdata = mdata;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NotificationAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_notification, parent, false);
        return new NotificationAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.viewHolder holder, int position) {
        holder.bind(mdata.get(position),onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView pesan,tanggal,judul;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            pesan=itemView.findViewById(R.id.news_notification);
            judul=itemView.findViewById(R.id.judul_notification);
            tanggal=itemView.findViewById(R.id.tanggal_notification);

        }
        public void bind(mNotification item, OnItemClickListener onItemClickListener){
            pesan.setText(item.getPesan());
            judul.setText(item.getJudul());
            tanggal.setText(item.getTanggal());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(item,getPosition());
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(mNotification item, int position );
    }
}
