package com.example.qlphontro.Fragment.HandleThongKe;

import static com.example.qlphontro.LoginActivity.sqlHelper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlphontro.Model.ThanhToan;
import com.example.qlphontro.R;

import java.util.List;

public class ListThongKePhongAdapter extends RecyclerView.Adapter<ListThongKePhongAdapter.ViewHolder> {
    List<ThanhToan> listThanhToan;
    List<Integer> listSoPhong;
    Context context;
    long tien=0;

    public ListThongKePhongAdapter(List<Integer> listSoPhong, List<ThanhToan> listThanhToan, Context context) {
        this.listThanhToan = listThanhToan;
        this.listSoPhong = listSoPhong;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_thong_ke, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvThangThongKe.setText("Phòng số " + listSoPhong.get(position));
        listThanhToan = sqlHelper.getAllThanhToanTheoSoPhong(listSoPhong.get(position));
        for (int i=0;  i<listThanhToan.size(); i++){
            Log.d("ff", "onBindViewHolder: "+ tien);
            tien+=(long)listThanhToan.get(i).getTongTien();
        }
        Log.d("ff", "onBindViewHolder: "+ tien);
        holder.tvTongTienThang.setText("Tổng tiền: " + tien + " VNĐ");
        tien = 0;
        ThongKePhongAdapter thongKeAdapter= new ThongKePhongAdapter(listSoPhong.get(position), context);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.rcvListThongKe.setLayoutManager(layoutManager);
        holder.rcvListThongKe.setAdapter(thongKeAdapter);
    }

    @Override
    public int getItemCount() {
        return listSoPhong == null ? 0 : listSoPhong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvThangThongKe, tvTongTienThang;
        RecyclerView rcvListThongKe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThangThongKe = itemView.findViewById(R.id.tvThangThongKe);
            rcvListThongKe = itemView.findViewById(R.id.rcvListThongKe);
            tvTongTienThang = itemView.findViewById(R.id.tvTongTienThang);
        }
    }
}
