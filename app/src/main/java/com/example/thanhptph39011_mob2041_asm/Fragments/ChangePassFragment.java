package com.example.thanhptph39011_mob2041_asm.Fragments;

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
EditText matKhau,newMk,nhapLaiMk;
Button btnSave,btnHuy;
ThuThuDAO thuThuDAO;

    public ChangePassFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_change_pass, container, false);
        matKhau = view.findViewById(R.id.tvMatKhau_ChangePass);
        newMk = view.findViewById(R.id.tvMkMoi_ChangePass);
        nhapLaiMk = view.findViewById(R.id.tvNhapLaiMkMoi_ChangePass);
        btnSave=view.findViewById(R.id.btnSave_ChangePass);
        btnHuy=view.findViewById(R.id.btnHuy_ChangePass);
        thuThuDAO = new ThuThuDAO(getActivity());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mk = matKhau.getText().toString();
                String mkMoi = newMk.getText().toString();
                String mkNhapLai = nhapLaiMk.getText().toString();
                if(TextUtils.isEmpty(mk)||TextUtils.isEmpty(mkMoi)||TextUtils.isEmpty(mkNhapLai)){
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(thuThuDAO.checkMk(mk)){
                    Toast.makeText(getActivity(), "Nhập sai mật khẩu. Mời nhập lại", Toast.LENGTH_SHORT).show();
                    matKhau.requestFocus();
                    return;
                }
                if(!mkMoi.equals(mkNhapLai)){
                    Toast.makeText(getActivity(), "Mật khẩu k trùng khớp> Mời nhập lại", Toast.LENGTH_SHORT).show();
                    nhapLaiMk.requestFocus();
                    return;
                }


            }
        });
        return view;
    }
}