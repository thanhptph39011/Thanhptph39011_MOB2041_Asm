package com.example.thanhptph39011_mob2041_asm.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanhptph39011_mob2041_asm.Adapter.PhieuMuonAdapter;
import com.example.thanhptph39011_mob2041_asm.Adapter.SachSpinerAdapter;
import com.example.thanhptph39011_mob2041_asm.Adapter.ThanhVienSpinnerAdapter;
import com.example.thanhptph39011_mob2041_asm.DAO.PhieuMuonDAO;
import com.example.thanhptph39011_mob2041_asm.DAO.SachDAO;
import com.example.thanhptph39011_mob2041_asm.DAO.ThanhVienDAO;
import com.example.thanhptph39011_mob2041_asm.Model.PhieuMuon;
import com.example.thanhptph39011_mob2041_asm.Model.Sach;
import com.example.thanhptph39011_mob2041_asm.Model.ThanhVien;
import com.example.thanhptph39011_mob2041_asm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PhieuMuonFragment extends Fragment {
    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO phieuMuonDAO;
    SimpleDateFormat sdf;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPm;
    Spinner spTv, spTenSach;
    TextView edtNgay,tvTienThue;

    CheckBox chkTraSach;
    Button btnSave, btnHuy;

    ThanhVienSpinnerAdapter spinnerAdapter;
    ArrayList<ThanhVien> listTv;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maTv;
    SachSpinerAdapter sachSpinerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTv, positionSach;
    SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        fab = v.findViewById(R.id.fltAddPhieuMuon);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        capNhapLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLog(getActivity(), 0);
            }
        });
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item=list.get(position);
                openDiaLog(getActivity(),1); //update
                return false;
            }
        });
        return v;
    }

    public void capNhapLv() {
        list = (ArrayList<PhieuMuon>) phieuMuonDAO.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        lvPhieuMuon.setAdapter(adapter);
    }

    protected void openDiaLog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_add_phieumuon);
        edMaPm = dialog.findViewById(R.id.edtMaPM_itemAddPM);
        spTv = dialog.findViewById(R.id.spThanhVien_itemAddPM);
        spTenSach = dialog.findViewById(R.id.spTenSach_itemAddPM);
        edtNgay = dialog.findViewById(R.id.edtDate_itemAddPM);
        tvTienThue = dialog.findViewById(R.id.tvTienThue_itemAddPM);
        chkTraSach = dialog.findViewById(R.id.chkTrangThai_itemAddPM);
        btnSave = dialog.findViewById(R.id.btnSave_itemAddPM);
        btnHuy = dialog.findViewById(R.id.btnHuy_itemAddPM);
//set ngày
        sdf=new SimpleDateFormat("yyyy-MM-dd");
        edtNgay.setText("Ngày thuê: " + sdf.format(new Date()));

        //spinerThanhVien
        thanhVienDAO = new ThanhVienDAO(context);
        listTv = new ArrayList<ThanhVien>();
        listTv = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        spinnerAdapter = new ThanhVienSpinnerAdapter(context, listTv);
        spTv.setAdapter(spinnerAdapter);
        if(listTv.isEmpty()){
            Toast.makeText(context, "Vui lòng thêm thành viên trước", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
        //laays mã tv
        spTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTv = listTv.get(position).getMaTV();
                Toast.makeText(context, "Chọn:" + listTv.get(position).getHoTen(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.GetAll();
        sachSpinerAdapter = new SachSpinerAdapter(context, listSach);
        spTenSach.setAdapter(sachSpinerAdapter);
        if(listSach.isEmpty()){
            Toast.makeText(context, "Vui lòng thêm sách trước", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
        //lấy mã loại
        spTenSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTienThue.setText(String.valueOf(tienThue));
//                Toast.makeText(context, "Chọn: " + listSach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //edit set data lên from
        if(type!=0){
edMaPm.setText(String.valueOf(item.getMaPM()));
            for (int i = 0; i <listTv.size() ; i++) {
               if(item.getMaTV()==(listTv.get(i).getMaTV())){
                   positionTv=i;
               }
               spTv.setSelection(positionTv);
            }
            for (int i = 0; i < listSach.size(); i++) {
                if(item.getMaSach()==(listSach.get(i).getMaSach())){
                    positionSach=i;
                }
                spTenSach.setSelection(positionSach);
                tvTienThue.setText(item.getTienThue()+"");
                edtNgay.setText(sfd.format(item.getNgay()));
                if(item.getTraSach()==1){
                    chkTraSach.setChecked(true);
                }else{
                    chkTraSach.setChecked(false);
                }
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
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maTv);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if(chkTraSach.isChecked()){
                    item.setTraSach(1);
                }else{
                    item.setTraSach(0);
                }
                if(type==0){
                    //type==0 insert
                    if(phieuMuonDAO.insertPhieuMuon(item)){
                        Toast.makeText(context, "Add Succ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Add Fail", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //type ==1 Update
                    item.setMaPM(Integer.parseInt(edMaPm.getText().toString()));
                    if(phieuMuonDAO.updatePhieuMuon(item)){
                        Toast.makeText(context, "Update Succ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhapLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void xoa(final String id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phieuMuonDAO.deleteLoaiSach(Integer.parseInt(id));
                capNhapLv();
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