package com.example.thanhptph39011_mob2041_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.thanhptph39011_mob2041_asm.DataBase.DbHelper;
import com.example.thanhptph39011_mob2041_asm.Model.LoaiSach;
import com.example.thanhptph39011_mob2041_asm.Model.ThuThu;

import java.util.ArrayList;

public class ThuThuDAO {
    private final DbHelper dbHelper;

    public ThuThuDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<ThuThu> getAll() {
        ArrayList<ThuThu> listTT = new ArrayList<ThuThu>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM ThuThu", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    ThuThu tt = new ThuThu();
                    tt.setMaTT(cursor.getString(0));
                    tt.setHoTen(cursor.getString(1));
                    tt.setMatKhau(cursor.getString(2));
                    listTT.add(tt);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.e("zzzzzzzzzzzzzzzzzzzz", "Lỗiiiiii");
        }
        return listTT;
    }

    public boolean insertThuThu(ThuThu thuThu) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTT", thuThu.getMaTT());
        values.put("hoTen", thuThu.getHoTen());
        values.put("matKhau", thuThu.getMatKhau());
        long row = db.insert("ThuThu", null, values);
        return (row > 0);
    }

    public boolean updateThuThu(ThuThu thuThu) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTT", thuThu.getMaTT());
        values.put("hoTen", thuThu.getHoTen());
        values.put("matKhau", thuThu.getMatKhau());
        long row = db.update("ThuThu", values, "maTT=?", new String[]{String.valueOf(thuThu.getMaTT())});
        return (row > 0);
    }

    public boolean deleteThuThu(String maTT) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("ThuThu", "maTT=?", new String[]{String.valueOf(maTT)});
        return (row > 0);
    }
    public boolean checklogin(String username, String pass) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from ThuThu where maTT=? and matKhau=?", new String[]{username, pass});
        int row = cursor.getCount();
        return (row > 0);
    }
    public boolean updatePassword(ThuThu thuThu) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("matKhau", thuThu.getMatKhau());

        long row = database.update("ThuThu", values, "maTT = ?",
                new String[]{thuThu.getMaTT()});
        return row != -1;
    }
    public boolean checkMk(String matKhau){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor =db.rawQuery("Select * from ThuThu where maTT=?",new String[]{matKhau});
        int row = cursor.getCount();
        return (row>0);
    }
}
