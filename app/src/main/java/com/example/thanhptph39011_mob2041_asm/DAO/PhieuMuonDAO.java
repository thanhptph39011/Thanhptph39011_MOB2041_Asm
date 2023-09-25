package com.example.thanhptph39011_mob2041_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.thanhptph39011_mob2041_asm.DataBase.DbHelper;
import com.example.thanhptph39011_mob2041_asm.Model.LoaiSach;
import com.example.thanhptph39011_mob2041_asm.Model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonDAO {
    private final DbHelper dbHelper;

    public PhieuMuonDAO(Context context) {
       dbHelper = new DbHelper(context);
    }
    public ArrayList<PhieuMuon> getAll() {
        ArrayList<PhieuMuon> listPm = new ArrayList<PhieuMuon>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM PhieuMuon", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                   PhieuMuon phieuMuon = new PhieuMuon();
                    phieuMuon.setMaPM(cursor.getInt(0));
                    phieuMuon.setMaTT(cursor.getString(1));
                    phieuMuon.setMaTV(cursor.getInt(2));
                    phieuMuon.setMaSach(cursor.getInt(3));
                    phieuMuon.setTienThue(cursor.getInt(4));
                    phieuMuon.setNgay(cursor.getString(5));
                    phieuMuon.setTraSach(cursor.getInt(6));
                    listPm.add(phieuMuon);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.e("zzzzzzzzzzzzzzzzzzzz", "Lỗiiiiii");
        }
        return listPm;
    }
    public boolean insertPhieuMuon(PhieuMuon pm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
   //chưa nghĩ ra
        long row = db.insert("LoaiSach", null, values);
        return (row > 0);
    }
    public boolean updatePhieuMuon(PhieuMuon pm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
       //Chưa nghĩ ra
        long row = db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(pm.getMaPM())});
        return (row>0);
    }
    public boolean deleteLoaiSach(int maPM){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("PhieuMuon", "maPM=?", new String[]{String.valueOf(maPM)});
        return (row > 0);
    }
}
