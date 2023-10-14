package com.example.thanhptph39011_mob2041_asm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thanhptph39011_mob2041_asm.Fragments.Top10Fragment;
import com.example.thanhptph39011_mob2041_asm.Model.Top;
import com.example.thanhptph39011_mob2041_asm.R;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    Top10Fragment top10Fragment;
    ArrayList<Top> list;
    TextView tvSach,tvSoLuong;
    public TopAdapter(@NonNull Context context,Top10Fragment fragment, ArrayList<Top> list) {
        super(context, 0,list);
        this.context=context;
        this.top10Fragment=fragment;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.item_top10, null);
            }
            final Top item  = list.get(position);
            if(item!=null){
                tvSach=v.findViewById(R.id.tvSach_itemTop10);
                tvSoLuong=v.findViewById(R.id.tvSoLuong_itemTop10);
                //
                tvSach.setText(item.getMaSach());
                tvSoLuong.setText(item.getSoLuong()+"");
            }
        return v;
    }
}
