package com.example.thanhptph39011_mob2041_asm.Model;

public class Top {
    private String maSach;
    private int soLuong;

    public Top() {
    }

    public Top(String maSach, int soLuong) {
        this.maSach = maSach;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
