package com.example.thanhptph39011_mob2041_asm.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanhptph39011_mob2041_asm.Adapter.ThanhVienAdapter;
import com.example.thanhptph39011_mob2041_asm.DAO.ThanhVienDAO;
import com.example.thanhptph39011_mob2041_asm.Model.ThanhVien;
import com.example.thanhptph39011_mob2041_asm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ThanhVienFragment extends Fragment {

    RecyclerView rycThanhVien;
    FloatingActionButton fltAddThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVienAdapter adapter;
    private ArrayList<ThanhVien> list = new ArrayList<>();

    public ThanhVienFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        rycThanhVien = view.findViewById(R.id.rycThanhVien);
        fltAddThanhVien = view.findViewById(R.id.fltAddThanhVien);
        thanhVienDAO = new ThanhVienDAO(getActivity());
        list = thanhVienDAO.getAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rycThanhVien.setLayoutManager(layoutManager);
        adapter = new ThanhVienAdapter(getActivity(), list);
        rycThanhVien.setAdapter(adapter);
        fltAddThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogAdd();
            }
        });
        return view;
    }

    private void opendialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_thanhvien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtMaTV_itemAddThanhVien = view.findViewById(R.id.edtMaTV_itemAddThanhVien);
        EditText edtTenTV_itemAddThanhVien = view.findViewById(R.id.edtTenTV_itemAddThanhVien);
        EditText edtNamSinh_itemAddThanhVien = view.findViewById(R.id.edtNamSinh_itemAddThanhVien);
        Button btnSave_itemAddThanhVien = view.findViewById(R.id.btnSave_itemAddThanhVien);
        Button btnHuy_itemAddThanhVien = view.findViewById(R.id.btnHuy_itemAddThanhVien);
        btnSave_itemAddThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenTv = edtTenTV_itemAddThanhVien.getText().toString();
                String namSinh = edtNamSinh_itemAddThanhVien.getText().toString();
                if (tenTv.isEmpty() || namSinh.isEmpty()) {
                    Toast.makeText(getActivity(), "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                ThanhVien tv = new ThanhVien(tenTv, namSinh);
                if (thanhVienDAO.insertTv(tv)) {
                    list.clear();
                    list.addAll(thanhVienDAO.getAll());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Add Thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Add Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy_itemAddThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thoát Add", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}