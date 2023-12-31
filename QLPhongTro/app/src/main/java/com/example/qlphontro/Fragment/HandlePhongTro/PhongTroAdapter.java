package com.example.qlphontro.Fragment.HandlePhongTro;

import static com.example.qlphontro.LoginActivity.sqlHelper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlphontro.Model.*;
import com.example.qlphontro.Model.PhongTro;
import com.example.qlphontro.Model.ThanhToan;
import com.example.qlphontro.R;

import java.util.List;

public class PhongTroAdapter extends RecyclerView.Adapter<PhongTroAdapter.ViewHolder>{
    List<PhongTro> list;
    IOnClickPhongTro iOnClickPhongTro;
    Context context;

    public PhongTroAdapter(List<PhongTro> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public List<PhongTro> getList() {
        return list;
    }

    public void setList(List<PhongTro> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public IOnClickPhongTro getiOnClickPhongTro() {
        return iOnClickPhongTro;
    }

    public void setiOnClickPhongTro(IOnClickPhongTro iOnClickPhongTro) {
        this.iOnClickPhongTro = iOnClickPhongTro;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_phong_tro, parent, false);
        ViewHolder viewHolders = new ViewHolder(view);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PhongTro phongTro= list.get(position);
        if(phongTro.getTaiKhoan().length() > 0)
            holder.ln_box.setBackgroundColor(Color.parseColor("#FFFFFF"));
        holder.tvDatPhong.setVisibility(View.GONE);
        holder.tvSoPhong.setText("Room Number: "+phongTro.getSoPhong());
        holder.tvNgayThue.setText("Date of hire: "+phongTro.getNgayThue());
        holder.tvTienPhong.setText("Fee: "+phongTro.getTienPhong());
        holder.tvTienDien.setText("Electric bill: "+phongTro.getTienDien());
        holder.tvTienNuoc.setText("Water bill: "+phongTro.getTienNuoc());
        holder.tvSDT.setText("Tenant phone number: "+phongTro.getTaiKhoan());
        holder.ln_DP.setWeightSum(2);
        if(phongTro.getTinhTrang() != 0 && phongTro.getTaiKhoan().length() == 0) {
            holder.tvYeuCauDatPhong.setVisibility(View.VISIBLE);
            holder.tvYeuCauDatPhong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iOnClickPhongTro.iOnClickYeuCau(phongTro.getSoPhong());
                }
            });
        }

        ThanhToan thanhToan = sqlHelper.getAllThanhToanTheoSoPhongVaTienCoc(phongTro.getSoPhong(), "Tiền cọc");
        if(thanhToan != null) {
            if (thanhToan.getTrangThai() == 0) {
                holder.lnBTN.setVisibility(View.GONE);
                holder.tvXacNhanTien.setVisibility(View.VISIBLE);
                holder.tvXacNhanTien.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Are you sure you have received the money?");
                        builder.setMessage("Received money?");
                        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                thanhToan.setTrangThai(1);
                                sqlHelper.updateThanhToan(thanhToan);
                                Toast.makeText(getContext(), "Successful confirmation", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.show();
                    }
                });
            }
        }
        else {
            holder.lnBTN.setVisibility(View.VISIBLE);
            holder.tvXacNhanTien.setVisibility(View.GONE);
        }
        holder.tvSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickPhongTro.iOnClickSua(phongTro, position);
            }
        });
        holder.tvXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickPhongTro.iOnClickXoa(phongTro, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSoPhong, tvNgayThue, tvTienPhong, tvTienDien, tvTienNuoc, tvSDT, tvSua, tvXoa, tvDatPhong, tvYeuCauDatPhong, tvXacNhanTien;
        LinearLayout ln_DP, ln_box, lnBTN;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoPhong= itemView.findViewById(R.id.tvSoPhong);
            tvNgayThue= itemView.findViewById(R.id.tvNgayThue);
            tvTienPhong= itemView.findViewById(R.id.tvTienPhong);
            tvTienDien= itemView.findViewById(R.id.tvTienDien);
            tvTienNuoc= itemView.findViewById(R.id.tvTienNuoc);
            tvSDT= itemView.findViewById(R.id.tvSDT);
            tvSua= itemView.findViewById(R.id.tvSuaPhongTro);
            tvXoa= itemView.findViewById(R.id.tvXoaPhongTro);
            tvDatPhong = itemView.findViewById(R.id.tvDatPhong);
            ln_DP = itemView.findViewById(R.id.ln_DP);
            ln_box = itemView.findViewById(R.id.ln_box);
            lnBTN = itemView.findViewById(R.id.lnBTN);
            tvYeuCauDatPhong = itemView.findViewById(R.id.tvYeuCauDatPhong);
            tvXacNhanTien = itemView.findViewById(R.id.tvXacNhanTien);
        }
    }
}
