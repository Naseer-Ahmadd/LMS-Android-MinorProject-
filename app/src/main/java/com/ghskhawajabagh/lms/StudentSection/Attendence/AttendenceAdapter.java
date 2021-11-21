package com.ghskhawajabagh.lms.StudentSection.Attendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ghskhawajabagh.lms.Common.Models.Attendence;
import com.ghskhawajabagh.lms.R;

import java.util.List;

public class AttendenceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
     List<Attendence> list;

    public AttendenceAdapter(Context context, List<Attendence> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.result_item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;
     //   viewHolder.planDetail.setText("Plan Name: "+plans.get(position).name+"\nSpeed: "+plans.get(position).speed+"th/s\nAlgorithm: "+plans.get(position).planAlgorithm+"\nPrice: $"+plans.get(position).price);
       //  viewHolder.noticeTitle.setText(notices.get(position).title);

        viewHolder.rName.setText(list.get(position).month.toUpperCase());
        viewHolder.rbody.setVisibility(View.GONE);
        viewHolder.line.setVisibility(View.GONE);
        viewHolder.rp.setText("Percentage: "+list.get(position).percentage+"%");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
           TextView rName,rbody,rp;
           View line;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
           //noticeTitle=itemView.findViewById(R.id.ntitle);
            rName=itemView.findViewById(R.id.rName);
            rbody=itemView.findViewById(R.id.resultBody);
            rp=itemView.findViewById(R.id.resultPercentage);
            line=itemView.findViewById(R.id.line);
          //  noticedate=itemView.findViewById(R.id.ndate);



        }
    }
}
