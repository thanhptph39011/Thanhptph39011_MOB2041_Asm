package com.example.thanhptph39011_mob2041_asm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thanhptph39011_mob2041_asm.Model.Sach;
import com.example.thanhptph39011_mob2041_asm.Model.ThanhVien;
import com.example.thanhptph39011_mob2041_asm.R;

import java.util.ArrayList;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView tvMaTv, tvTenTv;
    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_thanhvien, null);
        }
        final ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTv = v.findViewById(R.id.tvMaTv_spinner);
            tvMaTv.setText(item.getMaTV() + ". ");
            tvTenTv = v.findViewById(R.id.tvTenTv_spinner);
            tvTenTv.setText(item.getHoTen());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_thanhvien, null);
        }ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTv = v.findViewById(R.id.tvMaTv_spinner);
            tvMaTv.setText(item.getMaTV() + ". ");
            tvTenTv = v.findViewById(R.id.tvTenTv_spinner);
            tvTenTv.setText(item.getHoTen());
        }
        return v;
    }
}
