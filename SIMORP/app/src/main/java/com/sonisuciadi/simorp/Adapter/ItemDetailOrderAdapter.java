package com.sonisuciadi.simorp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sonisuciadi.simorp.Model.mDetailsOrder;
import com.sonisuciadi.simorp.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ItemDetailOrderAdapter extends RecyclerView.Adapter<ItemDetailOrderAdapter.viewHolder> {
    String context;
    List<mDetailsOrder> mdata;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public ItemDetailOrderAdapter( List<mDetailsOrder> mdata, String context ) {
        this.context=context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ItemDetailOrderAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_order, parent, false);
        return new ItemDetailOrderAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDetailOrderAdapter.viewHolder holder, int position) {
        holder.bind(mdata.get(position));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
    public class viewHolder extends RecyclerView.ViewHolder {
        TextView id, nama,jumlah,harga,total;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id_barang_item);
            nama=itemView.findViewById(R.id.nama_barang_item);
            jumlah=itemView.findViewById(R.id.jumlah_barang_item);
            harga=itemView.findViewById(R.id.harga_barang_item);
            total=itemView.findViewById(R.id.total_barang_item);
        }
        public void bind(mDetailsOrder item){
            id.setText(String.valueOf(item.getId()));
            nama.setText(item.getNama());
//            nama.setText(String.valueOf(context));
            jumlah.setText(String.valueOf(item.getJumlah()));
            if (context.equals("Income")){
                harga.setText(formatRupiah(Double.valueOf(item.getHargaJual())));
            }else {
                harga.setText(formatRupiah(Double.valueOf(item.getHargaBeli())));
            }

            total.setText(formatRupiah(Double.valueOf(item.getSubtotal())));

        }
    }
}
