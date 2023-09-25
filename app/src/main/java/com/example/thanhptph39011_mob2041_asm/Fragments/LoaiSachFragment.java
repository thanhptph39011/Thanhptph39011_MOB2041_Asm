package com.example.thanhptph39011_mob2041_asm.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanhptph39011_mob2041_asm.Adapter.LoaiSachAdapter;
import com.example.thanhptph39011_mob2041_asm.DAO.LoaiSachDAO;
import com.example.thanhptph39011_mob2041_asm.Model.LoaiSach;
import com.example.thanhptph39011_mob2041_asm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class LoaiSachFragment extends Fragment {
RecyclerView rycLoaiSach;
FloatingActionButton fltAddLoaiSach;
LoaiSachDAO loaiSachDAO;
LoaiSachAdapter adapter;
private ArrayList<LoaiSach> listls = new ArrayList<LoaiSach>();



    public LoaiSachFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_loai_sach, container, false);
rycLoaiSach = view.findViewById(R.id.rycLoaiSach);
fltAddLoaiSach = view.findViewById(R.id.fltAddLoaiSach);
loaiSachDAO = new LoaiSachDAO(getActivity());
listls = loaiSachDAO.getAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rycLoaiSach.setLayoutManager(layoutManager);
        adapter = new LoaiSachAdapter(getActivity(),listls);
        rycLoaiSach.setAdapter(adapter );
        fltAddLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogadd();
            }
        });
        return view;
    }
    private void opendialogadd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_loaisach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtTenLoai = view.findViewById(R.id.edtTenLoai_itemAddLoaiSach);
        Button btnSave = view.findViewById(R.id.btnSave_itemAddLoaiSach);
        Button btnHuy = view.findViewById(R.id.btnHuy_itemAddLoaiSach);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoai = edtTenLoai.getText().toString();
                if(tenLoai.equals("")){
                    Toast.makeText(getActivity(), "Nhập tên loại", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoaiSach ls = new LoaiSach(tenLoai);
                if(loaiSachDAO.insertLoaiSach(ls)){
                    listls.clear();
                    listls.addAll(loaiSachDAO.getAll());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Add Succ", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else{
                    Toast.makeText(getActivity(), "Add Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Huỷ Add", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}