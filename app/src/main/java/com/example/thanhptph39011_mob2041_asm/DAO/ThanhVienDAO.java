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

public class ThanhVienDAO {
    private final DbHelper dbHelper;

    public ThanhVienDAO(Context context) {
        dbHelper = new DbHelper(context);

    }

    public ArrayList<ThanhVien> getAll() {
        ArrayList<ThanhVien> listTv = new ArrayList<ThanhVien>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM ThanhVien", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    ThanhVien tv = new ThanhVien();
                    tv.setMaTV(cursor.getInt(0));
                    tv.setHoTen(cursor.getString(1));
                    tv.setNamSinh(cursor.getString(2));
                    listTv.add(tv);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.e("zzzzzzzzzzzzzzzzzzzz", "Lỗiiiiii");
        }
        return listTv;
    }

    public boolean insertTv(ThanhVien tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen", tv.getHoTen());
        values.put("namSinh", tv.getNamSinh());
        long row = db.insert("ThanhVien", null, values);
        return (row > 0);
    }

    public boolean updateTv(ThanhVien tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen", tv.getHoTen());
        values.put("namSinh", tv.getNamSinh());
        long row = db.update("ThanhVien", values, "maTV=?", new String[]{String.valueOf(tv.getMaTV())});
        return (row > 0);
    }

    public boolean deleteTv(int maTV) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("ThanhVien", "maTV=?", new String[]{String.valueOf(maTV)});
        return (row > 0);
    }
    private List<ThanhVien> getData(String sql, String...selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<ThanhVien> list = new ArrayList<ThanhVien>();
        Cursor c =db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.setMaTV(c.getInt(0));
            obj.setHoTen(c.getString(1));
            obj.setNamSinh(c.getString(2));
            list.add(obj);
        }
        return list;
    }
    public ThanhVien getID(String id){
        String sql ="select * from thanhvien where maTV=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }
}
