package com.practice.somap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.somap.domain.CarData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JisangYou on 2017-11-22.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder>{
    private List<CarData> data = new ArrayList<>();

    public void setDataAndRefresh(List<CarData> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        CarData carData = data.get(position);
        holder.textCarName.setText(carData.getCar_name());
        holder.textZoneName.setText(carData.getZone_name());
        holder.textOptions.setText(carData.getCar_option());
        holder.textDiscount.setText(carData.getMember_discount());
        holder.textFee.setText(carData.getDriving_fee());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textCarName;
        TextView textZoneName;
        TextView textOptions;
        TextView textDiscount;
        TextView textFee;
        public Holder(View itemView) {
            super(itemView);

            textCarName = itemView.findViewById(R.id.textCarName);
            textZoneName = itemView.findViewById(R.id.textZoneName);
            textOptions = itemView.findViewById(R.id.textOptions);
            textDiscount = itemView.findViewById(R.id.textDiscount);
            textFee = itemView.findViewById(R.id.textFee);
        }
    }
}
