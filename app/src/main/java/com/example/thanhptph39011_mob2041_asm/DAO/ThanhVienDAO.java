package com.example.thanhptph39011_mob2041_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.thanhptph39011_mob2041_asm.DataBase.DbHelper;
import com.example.thanhptph39011_mob2041_asm.Model.ThanhVien;

import java.util.ArrayList;

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
      values.put("hoTen",tv.getHoTen());
      values.put("namSinh",tv.getNamSinh());
        long row = db.insert("ThanhVien", null, values);
        return (row > 0);
    }
public boolean updateTv(ThanhVien tv){
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("hoTen",tv.getHoTen());
    values.put("namSinh",tv.getNamSinh());
    long row = db.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(tv.getMaTV())});
    return (row>0);
}
public boolean deleteTv(int maTV){
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    long row = db.delete("ThanhVien", "maTV=?", new String[]{String.valueOf(maTV)});
    return (row > 0);
}

}
