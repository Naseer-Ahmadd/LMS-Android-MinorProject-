package com.ghskhawajabagh.lms.Common.Login;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginPresenter {
    ILogin view;
    Context context;
    private final FirebaseAuth auth;
    private final FirebaseFirestore db;

    public LoginPresenter(ILogin view, Context context) {
        this.view = view;
        this.context = context;
        auth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
    }

    public  void signIn(String email,String password)
    {
        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        // progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            getUserInformation(auth.getCurrentUser().getEmail());

                        } else {
                            //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            //startActivity(intent);
                            // finish();
                            view.onError("Failed to login: "+task.getException().getMessage());
                        }
                    }
                });

    }

    private  void getUserInformation(String email)
    {
         email=email.toLowerCase();
        db.collection("users").whereEqualTo("email",email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.getResult().size()>0)
                {
                    User user=task.getResult().getDocuments().get(0).toObject(User.class);
                    if(!user.isStudent)
                    {
                        view.onError("Account doest not exist");
                        return;
                    }

                    view.onLoginSuccesful(user);
                    return;
                }
                view.onError("Something went wrong!");

            }
        });
    }




}
