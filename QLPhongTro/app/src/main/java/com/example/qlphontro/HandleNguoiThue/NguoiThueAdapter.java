package com.example.qlphontro.HandleNguoiThue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.qlphongtro.Model.TaiKhoan;
import com.codeandroid.qlphongtro.R;
import com.example.qlphontro.Model.TaiKhoan;

import java.util.List;

public class NguoiThueAdapter extends RecyclerView.Adapter<NguoiThueAdapter.ViewHolder> {
    List<TaiKhoan> list;
    Context context;

    public List<TaiKhoan> getList() {
        return list;
    }

    public void setList(List<TaiKhoan> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public NguoiThueAdapter(List<TaiKhoan> list, Context context) {
        this.list = list;
        this.context = context;
    }

    IOnClickTaiKhoan iOnClickTaiKhoan;

    public IOnClickTaiKhoan getiOnClickTaiKhoan() {
        return iOnClickTaiKhoan;
    }

    public void setiOnClickTaiKhoan(IOnClickTaiKhoan iOnClickTaiKhoan) {
        this.iOnClickTaiKhoan = iOnClickTaiKhoan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_nguoi_thue, parent, false);
        ViewHolder viewHolders = new ViewHolder(view);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TaiKhoan taiKhoan= list.get(position);
        holder.tvTen.setText("Name: "+taiKhoan.getFullname());
        holder.tvPhone.setText("Phone: "+taiKhoan.getPhone());
        holder.tvQue.setText("Address: "+taiKhoan.getAddress());
        holder.tvNS.setText("Date of birth: "+taiKhoan.getDob());
        holder.tvTenDN.setText("Username: "+taiKhoan.getUsername());
        holder.lnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickTaiKhoan.iOnClickTaiKhoan(taiKhoan, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen, tvPhone, tvQue, tvNS, tvTenDN;
        LinearLayout lnTaiKhoan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lnTaiKhoan= itemView.findViewById(R.id.lnTaiKhoan);
            tvTen= itemView.findViewById(R.id.tvTenNguoiThue);
            tvPhone= itemView.findViewById(R.id.tvSDTNguoiThue);
            tvQue= itemView.findViewById(R.id.tvQueQuan);
            tvNS= itemView.findViewById(R.id.tvNgaySinh);
            tvTenDN= itemView.findViewById(R.id.tvTenDN);
        }
    }
}
