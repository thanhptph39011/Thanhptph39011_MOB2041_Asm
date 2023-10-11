package com.example.thanhptph39011_mob2041_asm.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thanhptph39011_mob2041_asm.DAO.LoaiSachDAO;
import com.example.thanhptph39011_mob2041_asm.Model.LoaiSach;
import com.example.thanhptph39011_mob2041_asm.Model.ThanhVien;
import com.example.thanhptph39011_mob2041_asm.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.viewholer> {
    private final Context context;
    private final ArrayList<LoaiSach> listls;
    LoaiSachDAO loaiSachDAO;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> listls) {
        this.context = context;
        this.listls = listls;
        loaiSachDAO = new LoaiSachDAO(context);
    }

    @NonNull
    @Override
    public viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach, null);
        return new viewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholer holder, int position) {
        holder.maLoai.setText(String.valueOf(listls.get(position).getMaLoai()));
        holder.tenLoai.setText(listls.get(position).getTenLoai());
        LoaiSach ls = listls.get(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");//set tiêu đề
                builder.setIcon(R.drawable.baseline_warning_24);//set icon
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (loaiSachDAO.deleteLoaiSach(ls.getMaLoai())) {
                            listls.clear();
                            listls.addAll(loaiSachDAO.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Delete Succ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Huỷ xoá", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLogUpdate(ls);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listls.size();
    }

    public class viewholer extends RecyclerView.ViewHolder {
        TextView maLoai, tenLoai;
        ImageView btnDelete, btnUpdate;

        public viewholer(@NonNull View itemView) {
            super(itemView);
            maLoai = itemView.findViewById(R.id.tvMaLoai_itemLoaiSach);
            tenLoai = itemView.findViewById(R.id.tvTenLoai_itemLoaiSach);
            btnUpdate = itemView.findViewById(R.id.btnUpdate_LoaiSach);
            btnDelete = itemView.findViewById(R.id.btnDelete_LoaiSach);
        }
    }

    public void openDiaLogUpdate(LoaiSach ls) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_loaisach, null);
        builder.setView(view);//gán view vào hôp thoại
        Dialog dialog = builder.create();//tạo hộp thoại
        dialog.show();
        EditText maLoai = view.findViewById(R.id.edtMaLS_itemUpLoaiSach);
        EditText tenLoai = view.findViewById(R.id.edtTenLoai_itemUpLoaiSach);
        Button btnSave = view.findViewById(R.id.btnSave_itemUpLoaiSach);
        Button btnHuy = view.findViewById(R.id.btnHuy_itemUpLoaiSach);
//gán dl
        maLoai.setText(String.valueOf(ls.getMaLoai()));
        tenLoai.setText(ls.getTenLoai());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tenLoai.getText().toString();
                if (ten.equals("")) {
                    Toast.makeText(context, "Nhập tên loại", Toast.LENGTH_SHORT).show();
                    return;
                }
                ls.setTenLoai(tenLoai.getText().toString());
                if (loaiSachDAO.updateLoaiSach(ls)) {
                    listls.clear();
                    listls.addAll(loaiSachDAO.getAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Update Succ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Huỷ Update", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
