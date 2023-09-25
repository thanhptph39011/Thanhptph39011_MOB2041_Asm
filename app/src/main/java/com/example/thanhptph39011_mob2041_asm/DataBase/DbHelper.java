package com.example.thanhptph39011_mob2041_asm.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name = "PNLIB";

    public DbHelper(@Nullable Context context) {
        super(context, Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //bảng thủ thư
        String createTableThuThu = "create table ThuThu(maTT text primary key," +
                "hoTen text not null," +
                "matKhau text not null)";
        db.execSQL(createTableThuThu);
        //bảng thành viên
        String createTableThanhVien = "create table ThanhVien(maTV integer primary key autoincrement," +
                "hoTen text not null," +
                " namSinh text not null)";
        db.execSQL(createTableThanhVien);
        //bảng loại sách
        String createTableLoaiSach = "create table LoaiSach(maLoai integer primary key autoincrement," +
                " tenLoai text not null)";
        db.execSQL(createTableLoaiSach);
        //bảng sách
        String createTableSach = "create table Sach(maSach integer primary key autoincrement, " +
                "tenSach text not null, " +
                "giaThue integer not null," +
                " maLoai integer references LoaiSach(maLoai))";
        db.execSQL(createTableSach);
        //Bảng phiếu mượn
        String createTablePhieuMuon = "create table PhieuMuon(maPM integer primary key autoincrement," +
                "maTT text references ThuThu(maTT)," +
                "maTV integer references ThanhVien(maTV)," +
                "maSach integer references Sach(maSach)," +
                "tienThue integer not null," +
                "ngay date not null," +
                "traSach integer not null)";
        db.execSQL(createTablePhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableLoaiThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableLoaiThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
