package com.example.thanhptph39011_mob2041_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.thanhptph39011_mob2041_asm.DataBase.DbHelper;
import com.example.thanhptph39011_mob2041_asm.Model.Sach;
import com.example.thanhptph39011_mob2041_asm.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private final DbHelper dbHelper;

    public SachDAO(Context context) {
dbHelper = new DbHelper(context);
    }
    public ArrayList<Sach> GetAll(){
ArrayList<Sach> list = new ArrayList<Sach>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM Sach", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                  Sach sach = new Sach();
                   sach.setMaSach(cursor.getInt(0));
                   sach.setTenSach(cursor.getString(1));
                   sach.setGiaThue(cursor.getInt(2));
                   sach.setMaLoai(cursor.getInt(3));
                   list.add(sach);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.e("zzzzzzzzzzzzzzzzzzzz", "Lỗiiiiii");
        }
return list;
    }
    public boolean insertSach(Sach sach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
       values.put("tenSach",sach.getTenSach());
       values.put("giaThue",sach.getGiaThue());
       values.put("maLoai",sach.getMaLoai());
        long row = db.insert("Sach", null, values);
        return (row > 0);
    }
    public boolean updateSach(Sach sach){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSach",sach.getTenSach());
        values.put("giaThue",sach.getGiaThue());
        values.put("maLoai",sach.getMaLoai());
        long row = db.update("Sach",values,"maSach=?",new String[]{String.valueOf(sach.getMaSach())});
        return (row>0);
    }
    public boolean delete(int maSach){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("Sach", "maSach=?", new String[]{String.valueOf(maSach)});
        return (row > 0);
    }
    private List<Sach> getData(String sql, String...selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Sach> list = new ArrayList<Sach>();
        Cursor c =db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            Sach obj = new Sach();
            obj.setMaSach(Integer.parseInt(c.getString(0)));
            obj.setTenSach(c.getString(1));
            obj.setGiaThue(c.getInt(2));
            obj.setMaLoai(c.getInt(3));

            list.add(obj);
        }
        return list;
    }
    public Sach getID(String id){
        String sql ="select * from sach where maSach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }

}
