package com.example.thanhptph39011_mob2041_asm.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thanhptph39011_mob2041_asm.DAO.SachDAO;
import com.example.thanhptph39011_mob2041_asm.DAO.ThanhVienDAO;
import com.example.thanhptph39011_mob2041_asm.Fragments.PhieuMuonFragment;
import com.example.thanhptph39011_mob2041_asm.Fragments.SachFragment;
import com.example.thanhptph39011_mob2041_asm.Model.PhieuMuon;
import com.example.thanhptph39011_mob2041_asm.Model.Sach;
import com.example.thanhptph39011_mob2041_asm.Model.ThanhVien;
import com.example.thanhptph39011_mob2041_asm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    private ArrayList<PhieuMuon> list;
    PhieuMuonFragment fragment;
    TextView tvMaPm, tvTenTv, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView btnDelete;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> list) {
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
            v = inflater.inflate(R.layout.item_home, null);
        }
        final PhieuMuon item = list.get(position);
        if (item != null) {
            tvMaPm = v.findViewById(R.id.tvmaPhieuMuon);
            tvTenTv = v.findViewById(R.id.tvThanhVien);
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvNgay = v.findViewById(R.id.tvNgayThue);
            tvTraSach = v.findViewById(R.id.tvTrangThai);
            btnDelete = v.findViewById(R.id.btnDeletePhieuMuon);
            //
            tvMaPm.setText(item.getMaPM() + "");
            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
            tvTenSach.setText(sach.getTenSach());
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaTV()));
            tvTenTv.setText(thanhVien.getHoTen());
            tvTienThue.setText(item.getTienThue() + "");
            try {
                tvNgay.setText(sfd.format(item.getNgay()));
            }catch (Exception e){
                e.printStackTrace();
            }

//
            if (item.getTraSach() == 1) {
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            } else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    fragment.xoa(String.valueOf(item.getMaPM()));
                }
            });
        }
        return v;
    }
}
