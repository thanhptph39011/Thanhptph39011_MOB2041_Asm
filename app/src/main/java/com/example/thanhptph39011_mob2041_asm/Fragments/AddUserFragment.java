package com.example.thanhptph39011_mob2041_asm.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanhptph39011_mob2041_asm.DAO.ThuThuDAO;
import com.example.thanhptph39011_mob2041_asm.Home_Activity;
import com.example.thanhptph39011_mob2041_asm.LoginActivity;
import com.example.thanhptph39011_mob2041_asm.Model.ThuThu;
import com.example.thanhptph39011_mob2041_asm.R;

import java.util.ArrayList;


public class AddUserFragment extends Fragment {
    EditText userName, hoTen, matKhau, nhapLaiMk;
    Button btnSave, btnHuy;
    ThuThuDAO thuThuDAO;


    public AddUserFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        userName = view.findViewById(R.id.tvTenDangNhap_AddUser);
        hoTen = view.findViewById(R.id.tvHoTen_AddUser);
        matKhau = view.findViewById(R.id.tvMatKhau_AddUser);
        nhapLaiMk = view.findViewById(R.id.tvNhapLaiMK_AddUser);
        btnSave = view.findViewById(R.id.btnSave_AddUser);
        btnHuy = view.findViewById(R.id.btnHuyLuu_AddUser);
        thuThuDAO = new ThuThuDAO(getActivity());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = userName.getText().toString();
                String Ten = hoTen.getText().toString();
                String Pass = matKhau.getText().toString();
                String NhapLai = nhapLaiMk.getText().toString();
                if (TextUtils.isEmpty(User) || TextUtils.isEmpty(Ten) || TextUtils.isEmpty(Pass) || TextUtils.isEmpty(NhapLai)) {
                    Toast.makeText(getActivity(), "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Pass.equals(NhapLai)) {
                    Toast.makeText(getActivity(), "Mật khẩu k trùng khớp", Toast.LENGTH_SHORT).show();
                    nhapLaiMk.requestFocus();
                    return;
                }
                ThuThu thuThu = new ThuThu(User, Ten, Pass);
                if (thuThuDAO.insertThuThu(thuThu)) {
                    Toast.makeText(getActivity(), "Add Succ", Toast.LENGTH_SHORT).show();
                    userName.setText("");
                    hoTen.setText("");
                    matKhau.setText("");
                    nhapLaiMk.setText("");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getActivity(), " Add Fail", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Huỷ Add", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Home_Activity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}