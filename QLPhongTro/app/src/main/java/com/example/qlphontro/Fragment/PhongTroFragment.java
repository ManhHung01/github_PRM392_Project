package com.example.qlphontro.Fragment;

import static com.codeandroid.qlphongtro.LoginActivity.sqlHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.qlphongtro.Fragment.HandlePhongTro.IOnClickPhongTro;
import com.codeandroid.qlphongtro.Fragment.HandleYeuCau.IOnClickYeuCau;
import com.codeandroid.qlphongtro.Fragment.HandleYeuCau.YeuCauAdapter;
import com.codeandroid.qlphongtro.Model.PhongTro;
import com.codeandroid.qlphongtro.Model.YeuCau;
import com.codeandroid.qlphongtro.R;
import com.codeandroid.qlphongtro.databinding.FragmentPhongTroBinding;

import java.util.List;


public class PhongTroFragment extends Fragment {

    FragmentPhongTroBinding binding;
    PhongTroAdapter phongTroAdapter;
    List<PhongTro> phongTroList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_phong_tro, container, false);
        phongTroList = sqlHelper.getAllPhongTro();
        phongTroAdapter = new PhongTroAdapter(phongTroList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.revPhongTro.setLayoutManager(layoutManager);
        binding.revPhongTro.setAdapter(phongTroAdapter);
        phongTroAdapter.setiOnClickPhongTro(new IOnClickPhongTro() {
            @Override
            public void iOnClickSua(PhongTro phongTro, int poss) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_update_phong_tro);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAtributes = window.getAttributes();
                windowAtributes.gravity = Gravity.CENTER_VERTICAL;
                window.setAttributes(windowAtributes);

                dialog.setCancelable(true);

                EditText edtTD = dialog.findViewById(R.id.edtTD);
                EditText edtSP = dialog.findViewById(R.id.edtSp);
                EditText edtTP = dialog.findViewById(R.id.edtTP);
                EditText edtTN = dialog.findViewById(R.id.edtTN);
                EditText edtSDT = dialog.findViewById(R.id.edtSDT);
                EditText edtNT = dialog.findViewById(R.id.edtNt);
                Button btnSua = dialog.findViewById(R.id.btnSua);

                edtTD.setText(phongTro.getTienDien() + "");
                edtSP.setText(phongTro.getSoPhong() + "");
                edtTN.setText(phongTro.getTienNuoc() + "");
                edtTP.setText(phongTro.getTienPhong() + "");
                edtSDT.setText(phongTro.getTaiKhoan() + "");
                edtNT.setText(phongTro.getNgayThue() + "");

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ngayThue = edtNT.getText().toString().trim();
                        int soPhong = Integer.parseInt(edtSP.getText().toString().trim());
                        double tienPhong = Double.parseDouble(edtTP.getText().toString().trim());
                        double tienDien = Double.parseDouble(edtTD.getText().toString().trim());
                        double tienNuoc = Double.parseDouble(edtTN.getText().toString().trim());
                        String sdt = edtSDT.getText().toString().trim();
                        PhongTro phongTro1 = new PhongTro(0, ngayThue, tienPhong, tienNuoc, tienDien, soPhong, sdt);
                        sqlHelper.updatePhongTro(phongTro1);
                        phongTroList.get(poss).setTienPhong(tienPhong);
                        phongTroList.get(poss).setTienDien(tienDien);
                        phongTroList.get(poss).setTienNuoc(tienNuoc);
                        phongTroList.get(poss).setNgayThue(ngayThue);
                        phongTroList.get(poss).setSoPhong(soPhong);
                        phongTroList.get(poss).setTaiKhoan(sdt);
                        phongTroAdapter.setList(phongTroList);
                        dialog.cancel();
                        Toast.makeText(getContext(), "Successful fix", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }




        return binding.getRoot();
    }
}
