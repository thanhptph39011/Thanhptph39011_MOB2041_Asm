package com.example.thanhptph39011_mob2041_asm.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.thanhptph39011_mob2041_asm.Adapter.TopAdapter;
import com.example.thanhptph39011_mob2041_asm.DAO.PhieuMuonDAO;
import com.example.thanhptph39011_mob2041_asm.DAO.ThongKeDAO;
import com.example.thanhptph39011_mob2041_asm.Model.Top;
import com.example.thanhptph39011_mob2041_asm.R;

import java.util.ArrayList;

public class Top10Fragment extends Fragment {
ListView lvTop;
ArrayList<Top> list;
TopAdapter adapter;

    public Top10Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top10, container, false);
        lvTop=view.findViewById(R.id.lvTop10);
      ThongKeDAO thongKeDAO= new ThongKeDAO(getActivity());
        list=(ArrayList<Top>) thongKeDAO.getTop();
        adapter = new TopAdapter(getActivity(),this,list);
        lvTop.setAdapter(adapter);
        return view;
    }
}