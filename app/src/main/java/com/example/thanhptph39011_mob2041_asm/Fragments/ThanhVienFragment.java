package com.example.thanhptph39011_mob2041_asm.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thanhptph39011_mob2041_asm.R;


public class ThanhVienFragment extends Fragment {



    public ThanhVienFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        return view;
    }
}