package com.ghskhawajabagh.lms.StudentSection.Attendence;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ghskhawajabagh.lms.Common.Models.Attendence;
import com.ghskhawajabagh.lms.Common.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class AttendencePresenter {
     AttendenceScreen view;
     Context context;
    FirebaseFirestore db;
    public AttendencePresenter(AttendenceScreen view, Context context) {
        this.view = view;
        this.context = context;
        db= FirebaseFirestore.getInstance();
    }

    public  void getResult(User user)
    { db.collection("attendence").whereEqualTo("studentEmail",user.email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {

            List<Attendence> list =task.getResult().toObjects(Attendence.class);
            view.onAttendenceRecived(list);
            // return;

            // view.onError("Something went wrong!");

        }
    });

    }
}
