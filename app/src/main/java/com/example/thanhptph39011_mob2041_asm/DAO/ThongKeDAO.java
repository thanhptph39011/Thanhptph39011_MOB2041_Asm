package com.example.thanhptph39011_mob2041_asm.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thanhptph39011_mob2041_asm.DataBase.DbHelper;
import com.example.thanhptph39011_mob2041_asm.Model.Sach;
import com.example.thanhptph39011_mob2041_asm.Model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public ThongKeDAO(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db= dbHelper.getWritableDatabase();
    }
public ArrayList<Top> getTop(){
        String sqlTop = "Select maSach, count(maSach) as soLuong from PhieuMuon group by  maSach order by soLuong desc limit 10 ";
        ArrayList<Top> list = new ArrayList<Top>();
        SachDAO sachDAO  = new SachDAO(context);
    Cursor c = db.rawQuery(sqlTop,null);
    while (c.moveToNext()){
        Top top = new Top();
        //getID();
    }
    return list;
}
//public int getDoanhThu(String startDate, String endDate){
//        String slqDoanhThu ="Select Sum(tienThue) as doanhThu from PhieuMuon Where ngay between ? and ?";
//
//}

}
