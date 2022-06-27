package com.sonisuciadi.simorp.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sonisuciadi.simorp.Model.mUsers;

import java.util.List;

public class UsersDataBaseHelper extends SQLiteOpenHelper {
    Context context;
    private static final String TABLE_NAME = "tbl_user";
    private static final int DATABASE_VERSION = 1;
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAMA = "nama";
    private static final String FIELD_ALAMAT = "alamat";
    private static final String FIELD_PONSEL = "ponsel";
    private static final String FIELD_WORKPLACES= "workplaces";
    private static final String FIELD_JABATAN = "jabatan";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_USER_UPDATE = "userupdate";
    private static final String FIELD_UPDATE_AT = "updateat";
    private static final String FIELD_NAMA_CABANG = "namacabang";

    public UsersDataBaseHelper(Context context) {
        super(context,TABLE_NAME,null,DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+" ("+
                FIELD_ID+" INTEGER, "+
                FIELD_NAMA+ " TEXT, " +
                FIELD_ALAMAT+ " TEXT, " +
                FIELD_PONSEL+ " TEXT, " +
                FIELD_WORKPLACES+ " INTEGER, " +
                FIELD_JABATAN+ " TEXT, " +
                FIELD_PASSWORD+ " TEXT, " +
                FIELD_USER_UPDATE+ " INTEGER, " +
                FIELD_UPDATE_AT+ " TEXT, " +
                FIELD_NAMA_CABANG+ " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public long insertUser(List<mUsers> user){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(FIELD_ID,user.get(0).getId());
        cv.put(FIELD_NAMA,user.get(0).getNama());
        cv.put(FIELD_ALAMAT,user.get(0).getAlamat());
        cv.put(FIELD_PONSEL,user.get(0).getPonsel());
        cv.put(FIELD_WORKPLACES,user.get(0).getWorkplaces());
        cv.put(FIELD_JABATAN,user.get(0).getJabatan());
        cv.put(FIELD_PASSWORD,user.get(0).getPassword());
        cv.put(FIELD_USER_UPDATE,user.get(0).getUser_update());
        cv.put(FIELD_UPDATE_AT,user.get(0).getUpdate_at());
        cv.put(FIELD_NAMA_CABANG,user.get(0).getNama_cabang());

        long exc=db.insert(TABLE_NAME,null,cv);
        return exc;
    }
    public Cursor getUser(){
        String querry="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(querry,null);
        if (cursor==null){
            cursor=sqLiteDatabase.rawQuery(querry,null);
        }
        return cursor;
    }
    public long deleteUser(String index){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long exc=sqLiteDatabase.delete(TABLE_NAME,"id= ?",new String[]{index});
        return exc;
    }

}
