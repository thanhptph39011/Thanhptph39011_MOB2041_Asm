package com.example.thanhptph39011_mob2041_asm.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thanhptph39011_mob2041_asm.DAO.SachDAO;
import com.example.thanhptph39011_mob2041_asm.Model.Sach;
import com.example.thanhptph39011_mob2041_asm.R;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.viewholer> {
    private final Context context;
    private final ArrayList<Sach> list;
    SachDAO sachDAO;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
        sachDAO = new SachDAO(context);
    }

    @NonNull
    @Override
    public viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, null);
        return new viewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholer holder, int position) {
holder.maSach.setText(String.valueOf(list.get(position).getMaSach()));
holder.tenSach.setText(list.get(position).getTenSach());
holder.giaThue.setText(String.valueOf(list.get(position).getGiaThue()));
holder.loaiSach.setText(list.get(position).getMaLoai());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholer extends RecyclerView.ViewHolder {
TextView maSach,tenSach,giaThue,loaiSach;
Button btnUpdate, btnDelete;
    public viewholer(@NonNull View itemView) {
        super(itemView);
        maSach = itemView.findViewById(R.id.tvMaSach_itemSach);
        tenSach = itemView.findViewById(R.id.tvTenSach_itemSach);
        giaThue = itemView.findViewById(R.id.tvGiaThue_itemSach);
        loaiSach = itemView.findViewById(R.id.tvLoaiSach_itemSach);
        btnUpdate=itemView.findViewById(R.id.btnUpdate_Sach);
        btnDelete=itemView.findViewById(R.id.btnDelete_Sach);
    }
}
}
