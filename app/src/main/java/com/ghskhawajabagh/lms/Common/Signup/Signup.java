package com.ghskhawajabagh.lms.Common.Signup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ghskhawajabagh.lms.Common.Models.User;
import com.ghskhawajabagh.lms.Common.UTIL.UTIL;
import com.ghskhawajabagh.lms.MainScreen.IMainScreen;
import com.ghskhawajabagh.lms.R;


public class Signup extends Fragment implements ISignup {

    View root;
    EditText etemail,etPassword,etusername,etclass,etphone;
    TextView signup;
    SignupPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_signup, container, false);
        etemail=root.findViewById(R.id.editTextEmail);
        etPassword=root.findViewById(R.id.editTextPassword);
        etusername=root.findViewById(R.id.editTextName);
        etphone=root.findViewById(R.id.editTextMobile);
        etclass=root.findViewById(R.id.editTextClass);
        signup=root.findViewById(R.id.signup);
        presenter=new SignupPresenter(this,getActivity());
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  if(validate())
                  {
                      User user=new User();
                      user.name=etusername.getText().toString();
                      user.email=etemail.getText().toString().toLowerCase();
                      user.isStudent=true;
                      user.isActive=false;
                      user.studentClass=Integer.parseInt(etclass.getText().toString());
                      user.password=etPassword.getText().toString();
                      user.phone=etphone.getText().toString();
                      presenter.createUser(user,user.password);
                      showLoader("We are creating your account");
                  }
            }
        });



        return  root;
    }

    @Override
    public void onSignupSuccesful() {
            hideLoader();
           onSuccess("Your account has been created please login");
          ((IMainScreen)getActivity()).loadSignInFragment();
    }

    @Override
    public void onError(String msg) {
        hideLoader();
        ((IMainScreen)getActivity()).showMessage(msg,null,null,true,false);
    }

    @Override
    public void showLoader(String msg) {
        ((IMainScreen)getActivity()).showLoader("Please wait",msg);
    }

    @Override
    public void hideLoader() {
        ((IMainScreen)getActivity()).hideLoader();
    }
    void onSuccess(String msg)
    {
        ((IMainScreen)getActivity()).showMessage(msg,null,null,true,true);
    }



    boolean validate()
    {
        if(etusername.getText().toString().isEmpty())
        {
            onError("Please enter your name");

            return false ;
        }


        if(etemail.getText().toString().isEmpty())
        {
            onError("Please enter your email");

            return false ;
        }

        if(!UTIL.Validators.validateEmail(etemail.getText().toString()))
        {
            onError("Please enter an valid email address");

            return  false;
        }



        if(etphone.getText().toString().isEmpty())
        {
            onError("Please enter your phone number");

            return false ;
        }

        if(etphone.getText().toString().length()>10 ||etphone.getText().toString().length()>10)
        {
            onError("Phone number should be 10 digits only");
            return  false;
        }



        if(etclass.getText().toString().isEmpty())
        {
            onError("Please enter your present class");

            return false ;
        }

        if(Integer.parseInt(etclass.getText().toString())>12 || Integer.parseInt(etclass.getText().toString())<1)
        {

            onError("Present class should be between 1 and 12");

            return false;
        }

        if(etPassword.getText().toString().isEmpty())
        {
            onError("Please set a password");
            return  false;
        }


        return  true;
    }
}