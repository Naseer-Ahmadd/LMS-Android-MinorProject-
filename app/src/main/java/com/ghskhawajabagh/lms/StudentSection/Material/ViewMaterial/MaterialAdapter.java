package com.ghskhawajabagh.lms.StudentSection.Material.ViewMaterial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ghskhawajabagh.lms.Common.Models.Subject;
import com.ghskhawajabagh.lms.Common.Models.User;
import com.ghskhawajabagh.lms.Common.UTIL.ApplicationPrefrences;
import com.ghskhawajabagh.lms.R;
import com.ghskhawajabagh.lms.Common.Models.Material;


import java.util.ArrayList;
import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

     Context context;
     List<Material> list;
     ViewMaterial mainview;
   //  User user;

    public MaterialAdapter(Context context, List<Material> list, ViewMaterial mainview) {
        this.context = context;
        this.list = list;
        this.mainview = mainview;
    }

    @NonNull
    @Override


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.material_item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;
        int p=position+1;
         viewHolder.mName.setText("["+p+"] "+list.get(position).title);
         viewHolder.mbody.setText(list.get(position).body);
        if(list.get(position).fileUrl==null)
        {
            viewHolder.file.setVisibility(View.GONE);
        }
         viewHolder.file.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                  mainview.viewPdf(list.get(position).fileUrl);
             }
         });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
           TextView mName,mbody,rp;
           View root,line,file;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
           //noticeTitle=itemView.findViewById(R.id.ntitle);
            line=itemView.findViewById(R.id.line);
            mName=itemView.findViewById(R.id.mName);
            mbody=itemView.findViewById(R.id.mtBody);
            file=itemView.findViewById(R.id.fileholder);

          //  noticedate=itemView.findViewById(R.id.ndate);



        }
    }
}
