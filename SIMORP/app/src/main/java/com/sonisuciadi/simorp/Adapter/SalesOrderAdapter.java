package com.sonisuciadi.simorp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sonisuciadi.simorp.Model.mOrder;
import com.sonisuciadi.simorp.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SalesOrderAdapter extends RecyclerView.Adapter<SalesOrderAdapter.viewHolder> {
    List<mOrder> mdata;
    Context context;
    private OnItemClickListener onItemClickListener;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public SalesOrderAdapter(List<mOrder> mdata, Context context, OnItemClickListener onItemClickListener) {
        this.mdata = mdata;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SalesOrderAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_pesanan, parent, false);
        return new SalesOrderAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesOrderAdapter.viewHolder holder, int position) {
        holder.bind(mdata.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView idorder,tanggalPesan,total,status,pemesan;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            idorder=itemView.findViewById(R.id.id_pesanan_cart);
            tanggalPesan=itemView.findViewById(R.id.tanggal_pesanan_cart);
            total=itemView.findViewById(R.id.total_harga_pesanan_cart);
            status=itemView.findViewById(R.id.status_pesanan);
            pemesan=itemView.findViewById(R.id.pemesan_cart);
        }
        public void bind(mOrder item, OnItemClickListener onItemClickListener){
            idorder.setText(item.getId().toString());
            tanggalPesan.setText(item.getTanggal());
            total.setText(formatRupiah(Double.valueOf(item.getTotal())));
            status.setText(item.getStatus());
            pemesan.setText(item.getNama());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(item,getPosition());
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(mOrder item,int position );
    }
    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
