package com.ghskhawajabagh.lms.Common.Signup;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.ghskhawajabagh.lms.Common.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupPresenter {
       ISignup view;
       Context context;
    private final FirebaseAuth auth;
    private final FirebaseFirestore db;

    public SignupPresenter(ISignup view, Context context) {
        this.view = view;
        this.context = context;
        auth=FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public void createUser(final User user, String password)
    {
        auth.createUserWithEmailAndPassword(user.email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            addUser(user);

                            //  registerView;
                        }else{
                            //display some message here
                             view.onError("Failed to Register User: "+task.getException().getMessage());

                        }

                    }
                });


    }

    private   void addUser(User user){


        CollectionReference dbCourses = db.collection("users");

        dbCourses.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                //Toast.makeText(MainActivity.this, "Your Course has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                view.onSignupSuccesful();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                // Toast.makeText(MainActivity.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();

               // view.onError();
            }
        });
    }

}
