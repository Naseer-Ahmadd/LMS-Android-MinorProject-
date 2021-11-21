package com.ghskhawajabagh.lms.StudentSection.NoticeBoard;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ghskhawajabagh.lms.Common.Models.Notice;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class NoticeBoardPresenter {

    INoticeBoard view;
    Context context;
    private final FirebaseFirestore db;
    public NoticeBoardPresenter(INoticeBoard view, Context context) {
        this.view = view;
        this.context = context;
        db = FirebaseFirestore.getInstance();
    }




    public void getNotices()
    {
        CollectionReference dbNotices = db.collection("notifications");

        dbNotices.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                  if(task.isSuccessful())
                  {
                      view.onNotificationsRecieved(task.getResult().toObjects(Notice.class));
                      return;
                  }
                  view.onError("Something went wrong");
            }
        });


    }
}
