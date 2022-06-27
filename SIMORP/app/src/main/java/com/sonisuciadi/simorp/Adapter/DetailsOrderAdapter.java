package com.sonisuciadi.simorp.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sonisuciadi.simorp.API.APIRequestData;
import com.sonisuciadi.simorp.API.RetroServer;
import com.sonisuciadi.simorp.CartActivity;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelper;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelperSeles;
import com.sonisuciadi.simorp.Model.mBarang;
import com.sonisuciadi.simorp.Model.mDetailsOrder;
import com.sonisuciadi.simorp.OrderActivity;
import com.sonisuciadi.simorp.R;
import com.sonisuciadi.simorp.Response.rBarang;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsOrderAdapter extends RecyclerView.Adapter<DetailsOrderAdapter.viewHolder> {
    Context context;
    List<mDetailsOrder> mdata;
    private OnDeleteClickListener onDeleteClickListener;
    private OnPlusClickListener onPlusClickListener;
    private OnMinusClickListener onMinusClickListener;
    private onChangePesananListener onChangePesananListener;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    String activity;

    public DetailsOrderAdapter(Context context, List<mDetailsOrder> mdata,OnDeleteClickListener onItemClickListener,
                               OnPlusClickListener onPlusClickListener,OnMinusClickListener onMinusClickListener, String activity) {
        this.context = context;
        this.mdata = mdata;
        this.onDeleteClickListener=onItemClickListener;
        this.onPlusClickListener=onPlusClickListener;
        this.onMinusClickListener=onMinusClickListener;
        this.activity=activity;


    }

    @NonNull
    @Override
    public DetailsOrderAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_cart, parent, false);
        return new DetailsOrderAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsOrderAdapter.viewHolder holder, int position) {
        holder.bind(mdata.get(position),onDeleteClickListener);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaBarang, tvIdBarang, tvHarga, tvStok, tvSubtotal;
        EditText etPesanan;
        Button btnPlus, btnMinus;
        ImageButton btnDelete;
        List<mBarang> mBarangs;
        ImageView imgBarang;
        DetailsOrderDataBaseHelper detailsOrderDataBaseHelper;
        DetailsOrderDataBaseHelperSeles detailsOrderDataBaseHelperSales;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            DetailsOrderDataBaseHelper detailsOrderDataBaseHelper=new DetailsOrderDataBaseHelper(context);
            tvIdBarang = itemView.findViewById(R.id.tv_id_barang_cart);
            tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang_cart);
            tvStok = itemView.findViewById(R.id.tv_stok_barang_cart);
            tvHarga = itemView.findViewById(R.id.tv_harga_barang_cart);
            tvSubtotal = itemView.findViewById(R.id.tv_subtotal_cart);
            etPesanan = itemView.findViewById(R.id.et_jumlah_pesanan_cart);
            btnMinus = itemView.findViewById(R.id.btn_minus_jumlah);
            btnPlus = itemView.findViewById(R.id.btn_plus_jumlah);
            btnDelete=itemView.findViewById(R.id.btn_delete_cart);
            imgBarang=itemView.findViewById(R.id.iv_barang_cart);
        }

        public void bind(mDetailsOrder item, OnDeleteClickListener onDeleteClickListener) {
            tvIdBarang.setText(String.valueOf(item.getId()));
            tvNamaBarang.setText(item.getNama());
            tvStok.setText(String.valueOf(item.getStok()));
            etPesanan.setText(String.valueOf(item.getJumlah()));
            Integer harga=0;
            if (activity.equals("Order")){
                harga=item.getHargaBeli();
            }else {
                harga=item.getHargaJual();
            }
            tvHarga.setText(formatRupiah(Double.valueOf(harga)));

            byte[] imageAsBytes = Base64.decode(item.getImage().getBytes(), Base64.DEFAULT);
            imgBarang.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

            Integer subtotal = item.getJumlah() * harga;
            tvSubtotal.setText(formatRupiah.format(Integer.valueOf(subtotal.toString())));
            DetailsOrderDataBaseHelper detailsOrderDataBaseHelper=new DetailsOrderDataBaseHelper(context);
            DetailsOrderDataBaseHelperSeles detailsOrderDataBaseHelperSeles=new DetailsOrderDataBaseHelperSeles(context);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDeleteClickListener.onItemClick(item,getPosition());
                    Toast.makeText(context, "Biso", Toast.LENGTH_SHORT).show();
                }
            });
            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer jumlah= Integer.valueOf(etPesanan.getText().toString())+1;
                    if (jumlah<=Integer.valueOf(tvStok.getText().toString())){
                        if (activity.equals("Order")){
                            detailsOrderDataBaseHelper.updateDetailOrder(tvIdBarang.getText().toString(),jumlah);

                        }else {
                            detailsOrderDataBaseHelperSeles.updateDetailOrder(tvIdBarang.getText().toString(),jumlah);

                        }
                        etPesanan.setText(jumlah.toString());
//                                Integer sub=subtotal+item.getHarga();
//                                tvSubtotal.setText(formatRupiah(Double.parseDouble(sub.toString())));
                    }else {
                        Toast.makeText(context, "STOK INVALID", Toast.LENGTH_SHORT).show();
                    }
                    onPlusClickListener.onPlusClick(item,getPosition());
                    Toast.makeText(context, "PLUS", Toast.LENGTH_SHORT).show();


                }
            });
            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer jumlah= Integer.valueOf(etPesanan.getText().toString())-1;
                    if (jumlah>0){
                        if (activity.equals("Order")){
                            detailsOrderDataBaseHelper.updateDetailOrder(tvIdBarang.getText().toString(),jumlah);

                        }else {
                            detailsOrderDataBaseHelperSeles.updateDetailOrder(tvIdBarang.getText().toString(),jumlah);

                        }

                        etPesanan.setText(jumlah.toString());
//                                Integer sub=subtotal-item.getHarga();
//                                tvSubtotal.setText(formatRupiah(Double.parseDouble(sub.toString())));
                    }else {
                        Toast.makeText(context, "STOK INVALID", Toast.LENGTH_SHORT).show();
                    }
                    onMinusClickListener.onMinusClick(item,getPosition());
                    Toast.makeText(context, "MINUS", Toast.LENGTH_SHORT).show();

                }
            });

            etPesanan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });



        }
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
    public int getTotal(){
        int total = 0;
        if (mdata != null){
            for (int i = 0; i< mdata.size(); i++){
                if (activity.equals("Order")){
                    total = total + (mdata.get(i).getJumlah() * mdata.get(i).getHargaBeli());
                }else {
                    total = total + (mdata.get(i).getJumlah() * mdata.get(i).getHargaJual());
                }

            }
        }else {

        }
        notifyDataSetChanged();
        return total;
    }
    public int getUntung(){
        int total = 0;
        if (mdata != null){
            for (int i = 0; i< mdata.size(); i++){
                total = total + (mdata.get(i).getJumlah() * (mdata.get(i).getHargaJual()- mdata.get(i).getHargaBeli()));
            }
        }else {

        }
        notifyDataSetChanged();
        return total;
    }

    public interface OnDeleteClickListener{
        void onItemClick(mDetailsOrder item,int position );
    }
    public interface onChangePesananListener{
        void afterTextChanged(Editable editable);
    }
    public interface OnPlusClickListener{
        void onPlusClick(mDetailsOrder item,int position );
    }
    public interface OnMinusClickListener{
        void onMinusClick(mDetailsOrder item,int position );
    }
}
