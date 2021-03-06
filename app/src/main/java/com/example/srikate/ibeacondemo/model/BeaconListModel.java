package com.example.srikate.ibeacondemo.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.srikate.ibeacondemo.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BeaconListModel extends RecyclerView.Adapter<BeaconListModel.BeaconViewHolder> {
    private ArrayList<BeaconDeviceModel> beaconList;
    private OnItemClickListener clickListener;
    private OnItemLongClickListner longClickListner;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public interface OnItemLongClickListner{
        void OnItemLongClick(int position);
    }

    public BeaconListModel(ArrayList<BeaconDeviceModel> list){
        beaconList = list;
    }

    public void setClickListener(OnItemClickListener listener){
        clickListener = listener;
    }

    public void setLongClickListner(OnItemLongClickListner listner){
        longClickListner = listner;
    }

    public static class BeaconViewHolder extends RecyclerView.ViewHolder{
        public TextView minor, major, range, average, frequent;

        public BeaconViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            minor = itemView.findViewById(R.id.cardMinorValue);
            major = itemView.findViewById(R.id.cardMajorValue);
            range = itemView.findViewById(R.id.cardRangeValue);
            average = itemView.findViewById(R.id.cardAverageValue);
            frequent = itemView.findViewById(R.id.cardFrequentValue);
        }
    }

    public BeaconListModel.BeaconViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beacon_card, parent, false);
        BeaconViewHolder vh = new BeaconViewHolder(v, clickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(BeaconViewHolder holder, int position){
        BeaconDeviceModel beacon = beaconList.get(position);
        holder.major.setText(Integer.toString(beacon.getMajor()));
        holder.minor.setText(Integer.toString(beacon.getMinor()));
        holder.frequent.setText(Integer.toString(beacon.getMostFrequent()));
        holder.average.setText(Integer.toString(beacon.getAverage()));
        holder.range.setText(beacon.getSmallest() + " - " + beacon.getLargest());
    }

    @Override
    public int getItemCount(){
        return beaconList.size();
    }

    public void PostResults(){
        for (BeaconDeviceModel b: beaconList){
            b.updateResults();

        }
        notifyDataSetChanged();
    }

    public void updateBeacon(int major, int minor, int signal){
        for (BeaconDeviceModel b: beaconList){
            if(b.getMajor() == major && b.getMinor() == minor){
                b.addToMeasures(signal);
            }
        }
    }

    public boolean checkUnique(int major, int minor){
        for (BeaconDeviceModel b: beaconList) {
            if(b.getMajor() == major && b.getMinor() == minor){
                return false;
            }
        }
        return true;
    }

    public void sortBySignal(){
        Collections.sort(beaconList);
        notifyDataSetChanged();
    }
}
