package com.ghskhawajabagh.lms.StudentSection.NoticeBoard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghskhawajabagh.lms.Common.Models.Notice;
import com.ghskhawajabagh.lms.R;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Notice> notices;

    public NoticeAdapter(Context context, List<Notice> notices) {
        this.context = context;
        this.notices = notices;
    }

    @NonNull
    @Override


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.notice_item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;
     //   viewHolder.planDetail.setText("Plan Name: "+plans.get(position).name+"\nSpeed: "+plans.get(position).speed+"th/s\nAlgorithm: "+plans.get(position).planAlgorithm+"\nPrice: $"+plans.get(position).price);
         viewHolder.noticeTitle.setText(notices.get(position).title);
         viewHolder.noticebody.setText(notices.get(position).body);
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }


    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
           TextView noticeTitle,noticebody;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            noticeTitle=itemView.findViewById(R.id.ntitle);
            noticebody=itemView.findViewById(R.id.body);



        }
    }
}
