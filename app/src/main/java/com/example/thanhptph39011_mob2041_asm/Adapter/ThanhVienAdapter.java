package com.example.thanhptph39011_mob2041_asm.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thanhptph39011_mob2041_asm.DAO.ThanhVienDAO;
import com.example.thanhptph39011_mob2041_asm.Model.ThanhVien;
import com.example.thanhptph39011_mob2041_asm.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.viewholer> {
    private final Context context;
    private final ArrayList<ThanhVien> listTv;
    ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> listTv) {
        this.context = context;
        this.listTv = listTv;
        thanhVienDAO = new ThanhVienDAO(context);
    }

    @NonNull
    @Override
    public viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, null);
        return new viewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholer holder, int position) {
        holder.tvMaTV_itemThanhVien.setText(String.valueOf(listTv.get(position).getMaTV()));
        holder.tvHoTen_itemThanhVien.setText(listTv.get(position).getHoTen());
        holder.tvNamSinh_itemThanhVien.setText(listTv.get(position).getNamSinh());
        ThanhVien tv = listTv.get(position);
        holder.btnDelete_ThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");//set tiêu đề
                builder.setIcon(R.drawable.baseline_warning_24);//set icon
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (thanhVienDAO.deleteTv(tv.getMaTV())) {
                            listTv.clear();
                            listTv.addAll(thanhVienDAO.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Delete Succ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Bạn đã thoát xoá", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    public class viewholer extends RecyclerView.ViewHolder {
        TextView tvMaTV_itemThanhVien, tvHoTen_itemThanhVien, tvNamSinh_itemThanhVien;
        Button btnDelete_ThanhVien;

        public viewholer(@NonNull View itemView) {
            super(itemView);
            tvMaTV_itemThanhVien = itemView.findViewById(R.id.tvMaTV_itemThanhVien);
            tvHoTen_itemThanhVien = itemView.findViewById(R.id.tvHoTen_itemThanhVien);
            tvNamSinh_itemThanhVien = itemView.findViewById(R.id.tvNamSinh_itemThanhVien);
            btnDelete_ThanhVien = itemView.findViewById(R.id.btnDelete_ThanhVien);
        }
    }
}
