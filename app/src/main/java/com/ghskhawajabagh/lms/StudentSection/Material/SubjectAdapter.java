package com.ghskhawajabagh.lms.StudentSection.Material;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ghskhawajabagh.lms.Common.Models.Result;
import com.ghskhawajabagh.lms.Common.Models.Subject;
import com.ghskhawajabagh.lms.Common.Models.User;
import com.ghskhawajabagh.lms.Common.UTIL.ApplicationPrefrences;
import com.ghskhawajabagh.lms.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
     List<Subject> list;
     Material mainview;
    User user;
    public SubjectAdapter(Context context, Material view, ApplicationPrefrences prefrences) {
        this.context = context;
        this.mainview = view;
        this.list=new ArrayList<>();
      user=  prefrences.getStoredUser();
        list.add(new Subject("ENGLISH"));
        list.add(new Subject("MATH"));
        list.add(new Subject("SCIENCE"));
        list.add(new Subject("SOCIAL SCIENCE"));
        list.add(new Subject("URDU"));
        list.add(new Subject("KASHMIRI"));

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
        viewHolder.rName.setText(list.get(position).subjectName);
        viewHolder.line.setVisibility(View.GONE);
        viewHolder.rbody.setVisibility(View.GONE);
        viewHolder.rp.setText(user.studentClass+"th");
        viewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mainview.onSubjectCliked(list.get(position).subjectName.toLowerCase(),user.studentClass);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
           TextView rName,rbody,rp;
           View root,line;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
           //noticeTitle=itemView.findViewById(R.id.ntitle);
            root=itemView.findViewById(R.id.root);
            line=itemView.findViewById(R.id.line);
            rName=itemView.findViewById(R.id.rName);
            rbody=itemView.findViewById(R.id.resultBody);
            rp=itemView.findViewById(R.id.resultPercentage);
          //  noticedate=itemView.findViewById(R.id.ndate);



        }
    }
}
