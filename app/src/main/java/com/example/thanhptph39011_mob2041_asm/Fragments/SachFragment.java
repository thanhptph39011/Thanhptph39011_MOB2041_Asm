package com.example.thanhptph39011_mob2041_asm.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.thanhptph39011_mob2041_asm.Adapter.LoaiSachAdapter;
import com.example.thanhptph39011_mob2041_asm.Adapter.SachAdapter;
import com.example.thanhptph39011_mob2041_asm.DAO.LoaiSachDAO;
import com.example.thanhptph39011_mob2041_asm.DAO.SachDAO;
import com.example.thanhptph39011_mob2041_asm.Model.LoaiSach;
import com.example.thanhptph39011_mob2041_asm.Model.Sach;
import com.example.thanhptph39011_mob2041_asm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SachFragment extends Fragment {
    RecyclerView rycSach;
    FloatingActionButton fltAdd;
    SachDAO sachDAO;
    SachAdapter adapter;
    LoaiSachDAO loaiSachDAO;

    private ArrayList<Sach> listSach = new ArrayList<>();


    public SachFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        rycSach = view.findViewById(R.id.rycSach);
        fltAdd = view.findViewById(R.id.fltAddSach);
        sachDAO = new SachDAO(getActivity());
        listSach = sachDAO.GetAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rycSach.setLayoutManager(layoutManager);
        adapter = new SachAdapter(getActivity(), listSach);
        rycSach.setAdapter(adapter);
        loaiSachDAO = new LoaiSachDAO(getActivity());

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLogAdd();
            }
        });
        return view;
    }

    public void openDiaLogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_sach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText maSach = view.findViewById(R.id.edtMaSach_itemAddSach);
        EditText tenSach = view.findViewById(R.id.edtTenSach_itemAddSach);
        EditText giaThue = view.findViewById(R.id.edtGiaThue_itemAddSach);
        Spinner spLoaiSach = view.findViewById(R.id.spLoaiSach_itemAddSach);
        Button btnSave = view.findViewById(R.id.btnSave_itemAddSach);
        Button btnHuy = view.findViewById(R.id.btnHuy_itemAddSach);
        //Đưa dl lên spiner
        List<String> list = new ArrayList<>();
        list = loaiSachDAO.getLoaiSach();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLoaiSach.setAdapter(arrayAdapter);
        spLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tenSach.getText().toString();
                String gia = giaThue.getText().toString();
                if (TextUtils.isEmpty(ten)) {
                    Toast.makeText(getActivity(), "Nhập tên sách", Toast.LENGTH_SHORT).show();
                    tenSach.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(gia)) {
                    Toast.makeText(getActivity(), "Nhập giá", Toast.LENGTH_SHORT).show();
                    giaThue.requestFocus();
                    return;
                }
                try {
                    int giaa = Integer.parseInt(gia);
                    if (giaa < 0) {
                        Toast.makeText(getActivity(), "Giá >0", Toast.LENGTH_SHORT).show();
                        giaThue.requestFocus();
                        return;
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Giá là số", Toast.LENGTH_SHORT).show();
                    giaThue.requestFocus();
                    return;
                }

            }
        });

    }


}

