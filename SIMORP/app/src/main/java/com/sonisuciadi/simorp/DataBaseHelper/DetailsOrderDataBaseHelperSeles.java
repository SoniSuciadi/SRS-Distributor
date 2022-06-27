package com.sonisuciadi.simorp.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DetailsOrderDataBaseHelperSeles extends SQLiteOpenHelper {
    Context context;
    private static final String TABLE_NAME = "tbl_detail_barang_seles";
    private static final int DATABASE_VERSION = 1;
    private static final String FIELD_ID = "id";
    private static final String FIELD_HARGA_JUAL = "hargaJual";
    private static final String FIELD_STOK = "stok";
    private static final String FIELD_NAMA = "nama_barang";
    private static final String FIELD_JUMLAH = "jumlah";
    private static final String FIELD_IMAGE = "img";
    private static final String FIELD_HARGA_BELI = "harga_beli";

    public DetailsOrderDataBaseHelperSeles(Context context) {
        super(context,TABLE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+" ("+
                FIELD_ID+ " INTEGER PRIMARY KEY , " +
                FIELD_HARGA_JUAL+ " INTEGER  , " +
                FIELD_STOK+ " INTEGER  , " +
                FIELD_NAMA+ " STRING  , " +
                FIELD_JUMLAH+ " INTEGER  , " +
                FIELD_IMAGE+ " STRING , " +
                FIELD_HARGA_BELI+ " INTEGER );";
        db.execSQL(query);
    }
    public Cursor getDetailOrder(){
        String querry="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(querry,null);
        if (cursor==null){
            cursor=sqLiteDatabase.rawQuery(querry,null);
        }
        return cursor;
    }
    public Cursor getDetailOrderbyName(Integer id){
        String querry="SELECT * FROM "+TABLE_NAME+" WHERE id="+id;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(querry,null);
        if (cursor==null){
            cursor=sqLiteDatabase.rawQuery(querry,null);
        }
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public long insertDetailsOrder(Integer id,Integer hargaJual, String nama, Integer stok,String img,Integer hargaBeli){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(FIELD_ID ,id);
        cv.put(FIELD_HARGA_JUAL ,hargaJual);
        cv.put(FIELD_NAMA ,nama);
        cv.put(FIELD_STOK ,stok);
        cv.put(FIELD_JUMLAH,1);
        cv.put(FIELD_IMAGE,img);
        cv.put(FIELD_HARGA_BELI ,hargaBeli);
        long exc=db.insert(TABLE_NAME,null,cv);
        return exc;
    }
    public long deleteDetailOrder(String index){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long exc=sqLiteDatabase.delete(TABLE_NAME,"id= ?",new String[]{index});
        return exc;
    }
    public long updateDetailOrder(String index,Integer jumlah){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(FIELD_JUMLAH,jumlah);
        long exc=sqLiteDatabase.update(TABLE_NAME,cv,"id = ?", new String[]{index});
        return exc;
    }
    public void deleteAllRecord(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from "+ TABLE_NAME);
    }

}
