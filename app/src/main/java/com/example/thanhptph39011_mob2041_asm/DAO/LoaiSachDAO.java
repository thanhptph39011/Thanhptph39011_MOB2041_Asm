package com.example.thanhptph39011_mob2041_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.thanhptph39011_mob2041_asm.DataBase.DbHelper;
import com.example.thanhptph39011_mob2041_asm.Model.LoaiSach;
import com.example.thanhptph39011_mob2041_asm.Model.ThanhVien;

import java.util.ArrayList;

public class LoaiSachDAO {
    private final DbHelper dbhelper;

    public LoaiSachDAO(Context context) {
        dbhelper = new DbHelper(context);
    }
    public ArrayList<LoaiSach> getAll() {
        ArrayList<LoaiSach> listLs = new ArrayList<LoaiSach>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM LoaiSach", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    LoaiSach loaiSach = new LoaiSach();
                   loaiSach.setMaLoai(cursor.getInt(0));
                   loaiSach.setTenLoai(cursor.getString(1));
                    listLs.add(loaiSach);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.e("zzzzzzzzzzzzzzzzzzzz", "Lỗiiiiii");
        }
        return listLs;
    }
    public boolean insertLoaiSach(LoaiSach ls) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
       values.put("tenLoai",ls.getTenLoai());
        long row = db.insert("LoaiSach", null, values);
        return (row > 0);
    }
    public boolean updateLoaiSach(LoaiSach ls){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoai",ls.getTenLoai());
        long row = db.update("LoaiSach",values,"maLoai=?",new String[]{String.valueOf(ls.getMaLoai())});
        return (row>0);
    }
    public boolean deleteLoaiSach(int maLoai){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long row = db.delete("LoaiSach", "maLoai=?", new String[]{String.valueOf(maLoai)});
        return (row > 0);
    }
}
