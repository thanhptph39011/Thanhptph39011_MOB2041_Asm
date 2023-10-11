package com.example.thanhptph39011_mob2041_asm.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thanhptph39011_mob2041_asm.DAO.LoaiSachDAO;
import com.example.thanhptph39011_mob2041_asm.DAO.SachDAO;
import com.example.thanhptph39011_mob2041_asm.Fragments.SachFragment;
import com.example.thanhptph39011_mob2041_asm.Model.LoaiSach;
import com.example.thanhptph39011_mob2041_asm.Model.Sach;
import com.example.thanhptph39011_mob2041_asm.Model.ThanhVien;
import com.example.thanhptph39011_mob2041_asm.R;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
    SachFragment fragment;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvMaLoai;
    ImageView btnDelete;

    public SachAdapter(@NonNull Context context, SachFragment fragment, ArrayList<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_sach, null);
        }
        Sach item = list.get(position);
        if (item != null) {
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
            tvMaSach = v.findViewById(R.id.tvMaSach_itemSach);
            tvTenSach = v.findViewById(R.id.tvTenSach_itemSach);
            tvGiaThue = v.findViewById(R.id.tvGiaThue_itemSach);
            tvMaLoai = v.findViewById(R.id.tvLoaiSach_itemSach);
            btnDelete = v.findViewById(R.id.btnDelete_Sach);
            //
            tvMaSach.setText(item.getMaSach() + "");
            tvTenSach.setText(item.getTenSach());
            tvGiaThue.setText(item.getGiaThue() + "");
            if (loaiSach != null) {
                tvMaLoai.setText(loaiSach.getTenLoai());
            } else {
                tvMaLoai.setText("Không xác định");
            }
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(item.getMaSach());
            }
        });
        return v;
    }
}

