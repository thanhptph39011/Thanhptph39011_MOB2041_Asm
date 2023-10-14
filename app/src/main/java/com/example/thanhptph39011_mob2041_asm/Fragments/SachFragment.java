package com.example.thanhptph39011_mob2041_asm.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.thanhptph39011_mob2041_asm.Adapter.LoaiSachAdapter;
import com.example.thanhptph39011_mob2041_asm.Adapter.LoaiSachSpinerAdapter;
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
    ListView lvSach;
    ArrayList<Sach> list;
    Dialog dialog;
    FloatingActionButton fab;
    EditText edtMaSach, edtTenSach, edtGiaThue;
    Spinner spinner;
    Button btnSave, btnHuy;
    static SachDAO dao;
    SachAdapter adapter;
    Sach item;
    LoaiSachSpinerAdapter spinerAdapter;
    ArrayList<LoaiSach> listls;
    LoaiSachDAO loaiSachDAO;
    int maLoaiSach, position;

    public SachFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        lvSach = view.findViewById(R.id.lvSach);
        fab = view.findViewById(R.id.fltAddSach);
        dao = new SachDAO(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return view;
    }

    public void openDialog(Context context, int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_add_sach);
        edtMaSach = dialog.findViewById(R.id.edtMaSach_itemAddSach);
        edtTenSach = dialog.findViewById(R.id.edtTenSach_itemAddSach);
        edtGiaThue = dialog.findViewById(R.id.edtGiaThue_itemAddSach);
        spinner = dialog.findViewById(R.id.spLoaiSach_itemAddSach);
        btnSave = dialog.findViewById(R.id.btnSave_itemAddSach);
        btnHuy = dialog.findViewById(R.id.btnHuy_itemAddSach);
        listls = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listls = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        spinerAdapter = new LoaiSachSpinerAdapter(context, listls);
        spinner.setAdapter(spinerAdapter);
        //
        if(listls.isEmpty()){
            Toast.makeText(context, "Vui lòng thêm loại sách trước", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
//lấy mã loại sách
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listls.get(position).getMaLoai();
                Toast.makeText(context, "Chọn " + listls.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //kiểm tra type insert 0 hay update 1
        edtMaSach.setEnabled(false);
        if (type != 0) {
            edtMaSach.setText(String.valueOf(item.getMaSach()));
            edtTenSach.setText(item.getTenSach());
            edtGiaThue.setText(String.valueOf(item.getGiaThue()));
            for (int i = 0; i < listls.size(); i++) {
                if (item.getMaLoai() == (listls.get(i).getMaLoai())) {
                    position = i;
                }
                Log.i("demo", "posSach" + position);
                spinner.setSelection(position);
            }
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtTenSach.getText().toString()) || TextUtils.isEmpty(edtGiaThue.getText().toString())) {
                    Toast.makeText(context, "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else
                    try {
                        item = new Sach();
                        item.setTenSach(edtTenSach.getText().toString());
                        item.setGiaThue(Integer.parseInt(edtGiaThue.getText().toString()));
                        item.setMaLoai(maLoaiSach);
                        if (validate() > 0) {
                            if (type == 0) {
                                //insert
                                if (dao.insertSach(item)) {
                                    Toast.makeText(context, "Add Succ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Add fail", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                item.setMaSach(Integer.parseInt(edtMaSach.getText().toString()));
                                if (dao.updateSach(item)) {
                                    Toast.makeText(context, "Update Succ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                            capNhatLv();
                            dialog.dismiss();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "Giá là số", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        dialog.show();

    }

    void capNhatLv() {
        list = (ArrayList<Sach>) dao.GetAll();
        adapter = new SachAdapter(getActivity(), this, list);
        lvSach.setAdapter(adapter);

    }

    public int validate() {
        int check = 1;
        if (edtTenSach.getText().length() == 0 || edtGiaThue.getText().length() == 0) {
            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            try {
                int gia = Integer.parseInt(edtGiaThue.getText().toString());
                if (gia < 0) {
                    Toast.makeText(getActivity(), "Giá >0", Toast.LENGTH_SHORT).show();
                    edtGiaThue.requestFocus();
                    check=-1;
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Giá thuê phải là số nguyên", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }

    public void xoa(int Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }


}


