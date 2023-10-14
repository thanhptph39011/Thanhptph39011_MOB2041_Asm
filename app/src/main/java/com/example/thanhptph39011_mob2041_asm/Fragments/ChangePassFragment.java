package com.example.thanhptph39011_mob2041_asm.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.thanhptph39011_mob2041_asm.Model.ThuThu;
import com.example.thanhptph39011_mob2041_asm.R;


public class ChangePassFragment extends Fragment {
    EditText matKhau, newMk, nhapLaiMk;
    Button btnSave, btnHuy;
    ThuThuDAO thuThuDAO;

    public ChangePassFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        matKhau = view.findViewById(R.id.tvMatKhau_ChangePass);
        newMk = view.findViewById(R.id.tvMkMoi_ChangePass);
        nhapLaiMk = view.findViewById(R.id.tvNhapLaiMkMoi_ChangePass);
        btnSave = view.findViewById(R.id.btnSave_ChangePass);
        btnHuy = view.findViewById(R.id.btnHuy_ChangePass);
        thuThuDAO = new ThuThuDAO(getActivity());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
                String user = preferences.getString("userName", "");
                String passOld = preferences.getString("passWord", "");
                if (validate() > 0) {
                    ThuThu thuThu = thuThuDAO.getID(user);
                    thuThu.setMatKhau(matKhau.getText().toString());
                    if (thuThuDAO.updateThuThu(thuThu)) {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        matKhau.setText("");
                        newMk.setText("");
                        nhapLaiMk.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matKhau.setText("");
                newMk.setText("");
                nhapLaiMk.setText("");
            }
        });

        return view;
    }

    public int validate() {
        int check = 1;
        if (matKhau.getText().length() == 0 || newMk.getText().length() == 0 || nhapLaiMk.getText().length() == 0) {
            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
            ;
        } else {
            SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
            String passOld = preferences.getString("passWord", "");
            String pass = newMk.getText().toString();
            String rePass = nhapLaiMk.getText().toString();
            if (!passOld.equals(matKhau.getText().toString())) {
                Toast.makeText(getActivity(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(getActivity(), "Mật khẩu k trùng kớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }


}