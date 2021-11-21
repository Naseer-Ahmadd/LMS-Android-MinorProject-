package com.ghskhawajabagh.lms.StudentSection.Material.ViewMaterial;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ghskhawajabagh.lms.Common.Models.Material;
import com.ghskhawajabagh.lms.Common.Models.Result;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ViewMaterialPresenter {
    ViewMaterial view;
    Context context;
    FirebaseFirestore db;
    public ViewMaterialPresenter(ViewMaterial view, Context context) {
        this.view = view;
        this.context = context;
        db= FirebaseFirestore.getInstance();
    }


    public  void getMaterial(String subjectName, int sClass)
    {
        String cl=""+sClass;
        db.collection("material").whereEqualTo("sclass",cl).whereEqualTo("subjectName",subjectName.toLowerCase()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                List<Material> list =task.getResult().toObjects(Material.class);
                view.onMaterialLoaded(list);
               // view.onResultRecieved(list);
                // return;

                // view.onError("Something went wrong!");

            }
        });
    }
}
