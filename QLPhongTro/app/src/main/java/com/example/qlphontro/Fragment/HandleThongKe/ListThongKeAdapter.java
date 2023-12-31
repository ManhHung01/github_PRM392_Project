package com.example.qlphontro.Fragment.HandleThongKe;

import static com.example.qlphontro.LoginActivity.sqlHelper;
import android.content.Context;
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

public class ListThongKeAdapter extends RecyclerView.Adapter<ListThongKeAdapter.ViewHolder> {
    List<ThanhToan> listThanhToan;
    List<String> listThang;
    Context context;
    double tien=0;

    public ListThongKeAdapter(List<String> listThang, List<ThanhToan> thanhToanList, Context context) {
        this.listThang = listThang;
        this.listThanhToan = thanhToanList;
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
        holder.tvThangThongKe.setText("Tháng " + listThang.get(position));
        listThanhToan = sqlHelper.getAllThanhToanTheoThang(listThang.get(position));
        for (int i=0;  i<listThanhToan.size(); i++){
            tien+=listThanhToan.get(i).getTongTien();
        }
        holder.tvTongTienThang.setText("Tổng tiền: " + tien + "VNĐ");
        tien = 0;
        ThongKeAdapter thongKeAdapter= new ThongKeAdapter(listThang.get(position), context);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.rcvListThongKe.setLayoutManager(layoutManager);
        holder.rcvListThongKe.setAdapter(thongKeAdapter);
    }

    @Override
    public int getItemCount() {
        return listThang == null ? 0 : listThang.size();
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
