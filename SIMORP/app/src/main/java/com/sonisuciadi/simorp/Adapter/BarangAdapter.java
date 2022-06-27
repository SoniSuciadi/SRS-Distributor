package com.sonisuciadi.simorp.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelper;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelperSeles;
import com.sonisuciadi.simorp.DataBaseHelper.UsersDataBaseHelper;
import com.sonisuciadi.simorp.LoginActivity;
import com.sonisuciadi.simorp.Model.mBarang;
import com.sonisuciadi.simorp.Model.mDetailsOrder;
import com.sonisuciadi.simorp.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.viewHolder> {
    List<mBarang> mdata;
    Context context;
    String activity;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    private BarangAdapter.onAddClickListener onAddClickListener;
    private BarangAdapter.onItemClickListener onItemClickListener;

    public BarangAdapter(List<mBarang> mdata,Context context,onAddClickListener onAddClickListener,onItemClickListener onItemClickListener,String activity) {
        this.mdata = mdata;
        this.context=context;
        this.onAddClickListener=onAddClickListener;
        this.onItemClickListener=onItemClickListener;
        this.activity=activity;
    }

    @NonNull
    @Override
    public BarangAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_barang,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangAdapter.viewHolder holder, int position) {
        holder.bind(mdata.get(position),onAddClickListener);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvId,tvNama,tvHarga,tvStok;
        ImageView img;
        Button btnAdd;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvId=itemView.findViewById(R.id.tv_id_barang);
            tvNama=itemView.findViewById(R.id.tv_nama_barang);
            tvHarga=itemView.findViewById(R.id.tv_harga_barang);
            tvStok=itemView.findViewById(R.id.tv_stok_barang);
            img=itemView.findViewById(R.id.iv_barang);
            btnAdd=itemView.findViewById(R.id.btn_add);
        }
        public void bind(mBarang item, BarangAdapter.onAddClickListener onAddClickListener){
            tvId.setText(String.valueOf(item.getId()));
            tvNama.setText(item.getNama());

            tvStok.setText(String.valueOf(item.getStok()));


            byte[] imageAsBytes = Base64.decode(item.getImage().getBytes(), Base64.DEFAULT);
            img.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            if (activity.equals("Order")){
                tvHarga.setText(formatRupiah.format(Double.valueOf(item.getHargaBeli())));
                DetailsOrderDataBaseHelper detailsOrderDataBaseHelper=new DetailsOrderDataBaseHelper(context);
                Cursor cursor=detailsOrderDataBaseHelper.getDetailOrderbyName(item.getId());
                if (cursor.getCount() == 0) {

                } else {
                    btnAdd.setEnabled(false);
                }
            }else {
                tvHarga.setText(formatRupiah.format(Double.valueOf(item.getHargaJual())));
                DetailsOrderDataBaseHelperSeles detailsOrderDataBaseHelper=new DetailsOrderDataBaseHelperSeles(context);
                Cursor cursor=detailsOrderDataBaseHelper.getDetailOrderbyName(item.getId());
                if (cursor.getCount() == 0) {

                } else {
                    btnAdd.setEnabled(false);
                }
            }

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnAdd.setEnabled(false);
                    onAddClickListener.onAddClick(item,getPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(item,getPosition());
                }
            });
        }

    }
    public interface onAddClickListener{
        void onAddClick(mBarang item,int position );
    }
    public interface onItemClickListener{
        void onItemClick(mBarang item,int position );
    }
}
