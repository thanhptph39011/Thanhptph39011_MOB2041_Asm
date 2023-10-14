package com.example.thanhptph39011_mob2041_asm.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thanhptph39011_mob2041_asm.DAO.ThongKeDAO;
import com.example.thanhptph39011_mob2041_asm.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DoanhThuFragment extends Fragment {
    EditText edtStartDate, edtEnDate;
    Button btnDoanhThu,btnStartDate,btnEndDate;
    TextView tvDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;


    public DoanhThuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DatePickerDialog.OnDateSetListener mStartDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                edtStartDate.setText(sdf.format(c.getTime()));
            }
        };
        DatePickerDialog.OnDateSetListener mEndDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                edtEnDate.setText(sdf.format(c.getTime()));
            }
        };
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        edtStartDate = view.findViewById(R.id.txtDateStart);
        edtEnDate = view.findViewById(R.id.txtDateEnd);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu_Fragment);
        btnStartDate=view.findViewById(R.id.btnStartDate);
        btnEndDate=view.findViewById(R.id.btnEndDate);
        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mStartDate, mYear, mMonth, mDay);
                d.show();
            }
        });
        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mEndDate, mYear, mMonth, mDay);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDate = edtStartDate.getText().toString();
                String endDate = edtEnDate.getText().toString();
                ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
                int doanhThu = thongKeDAO.getDoanhThu(startDate, endDate);
                tvDoanhThu.setText("Doanh thu: " + doanhThu + " VND");
            }
        });
        return view;
    }
}