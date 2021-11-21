package com.ghskhawajabagh.lms.StudentSection.Result;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ghskhawajabagh.lms.Common.Models.Result;
import com.ghskhawajabagh.lms.Common.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ResultPresenter {
    Context context;
    ResultScreen view;
    FirebaseFirestore db;

    public ResultPresenter(Context context, ResultScreen view) {
        this.context = context;
        this.view = view;
        db= FirebaseFirestore.getInstance();
    }

    public  void getResult(User user){
        db.collection("results").whereEqualTo("studentEmail",user.email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    List<Result> list =task.getResult().toObjects(Result.class);
                    view.onResultRecieved(list);
                   // return;

               // view.onError("Something went wrong!");

            }
        });
    }
}
