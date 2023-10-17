package com.example.thanhptph39011_mob2041_asm.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thanhptph39011_mob2041_asm.DataBase.DbHelper;
import com.example.thanhptph39011_mob2041_asm.Model.Sach;
import com.example.thanhptph39011_mob2041_asm.Model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public ThongKeDAO(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db= dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Top> getTop(){
        String  sqlTop = "Select maSach,count(maSach) as soLuong From PhieuMuon group by maSach order by soLuong Desc Limit 10";
        List<Top> list = new ArrayList<Top>();
        SachDAO sachDao = new SachDAO(context);
        Cursor c = db.rawQuery(sqlTop,null);
        while (c.moveToNext()){
            Top top = new Top();
            Sach sach = sachDao.getID(c.getString(c.getColumnIndex("maSach")));
           top.setMaSach((sach.getTenSach()));
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }
//    @SuppressLint("Range")
//    public int getDoanhThu(String tuNgay, String denNgay){
//        String sqlDoanhThu = "SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
//        List<Integer> list = new ArrayList<>();
//        Cursor c = db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
//        while(c.moveToNext()){
//            try {
//                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
//            }catch (Exception e){
//                list.add(0);
//            }
//        }
//        return list.get(0);
//
//    }
@SuppressLint("Range")
public int getDoanhThu(String tuNgay, String denNgay){
    String sqlDoanhThu = "Select Sum(tienThue) as doanhThu From PhieuMuon Where ngay between ? and ?";
    List<Integer> list = new ArrayList<>();
    Cursor c = db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
    while(c.moveToNext()){
        try {
            list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
        }catch (Exception e){
            list.add(0);
        }
    }
    return list.get(0);

}


}
