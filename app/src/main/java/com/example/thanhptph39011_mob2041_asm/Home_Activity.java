package com.example.thanhptph39011_mob2041_asm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanhptph39011_mob2041_asm.DAO.ThuThuDAO;
import com.example.thanhptph39011_mob2041_asm.Fragments.AddUserFragment;
import com.example.thanhptph39011_mob2041_asm.Fragments.ChangePassFragment;
import com.example.thanhptph39011_mob2041_asm.Fragments.DoanhThuFragment;
import com.example.thanhptph39011_mob2041_asm.Fragments.LoaiSachFragment;
import com.example.thanhptph39011_mob2041_asm.Fragments.PhieuMuonFragment;
import com.example.thanhptph39011_mob2041_asm.Fragments.SachFragment;
import com.example.thanhptph39011_mob2041_asm.Fragments.ThanhVienFragment;
import com.example.thanhptph39011_mob2041_asm.Fragments.Top10Fragment;
import com.example.thanhptph39011_mob2041_asm.Model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class Home_Activity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    View view;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        view= nav.getHeaderView(0);
        TextView tvWelcome = view.findViewById(R.id.tvWellcome);
        setSupportActionBar(toolbar);// gán toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setItemIconTintList(null);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        if(user.equalsIgnoreCase("admin")){
            nav.getMenu().findItem(R.id.ThemNguoiDung).setVisible(true);
            Toast.makeText(this, "Wellcome Admin", Toast.LENGTH_SHORT).show();
        }else{
          thuThuDAO = new ThuThuDAO(this);
            ThuThu tt = thuThuDAO.getID(user);
            String userName = tt.getHoTen();
            tvWelcome.setText("Wellcome: "+userName);
            Toast.makeText(this, "Wellcome thủ thư", Toast.LENGTH_SHORT).show();
        }
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.QuanLyPhieuMuon) {
                    PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                    replaceFrg(phieuMuonFragment);
                } else if (item.getItemId() == R.id.QuanLyLoaiSach) {
                    LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                    replaceFrg(loaiSachFragment);
                } else if (item.getItemId() == R.id.QuanLySach) {
                    SachFragment sachFragment = new SachFragment();
                    replaceFrg(sachFragment);

                } else if (item.getItemId() == R.id.top10SachMuonNhieuNhat) {
                    Top10Fragment top10Fragment = new Top10Fragment();
                    replaceFrg(top10Fragment);
                } else if (item.getItemId() == R.id.DoanhThu) {
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    replaceFrg(doanhThuFragment);
                } else if (item.getItemId() == R.id.ThemNguoiDung) {
                    AddUserFragment addUserFragment = new AddUserFragment();
                    replaceFrg(addUserFragment);
                } else if (item.getItemId() == R.id.DoiMk) {
                    ChangePassFragment changePassFragment = new ChangePassFragment();
                    replaceFrg(changePassFragment);
                } else if (item.getItemId() == R.id.QuanLyThanhVien) {
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    replaceFrg(thanhVienFragment);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Home_Activity.this);
                    builder.setTitle("Cảnh báo");
                    builder.setIcon(R.drawable.baseline_warning_24);
                    builder.setMessage("Bạn có muốn đăng xuất k?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Home_Activity.this, "Đăng xuất Succ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Home_Activity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
                getSupportActionBar().setTitle(item.getTitle()); //khi click vào item hiển thị tieu de lên toolbar
                drawerLayout.close(); //đóng nav
                return true;
            }
        });
    }

    public void replaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav, frg).commit();
    }
}