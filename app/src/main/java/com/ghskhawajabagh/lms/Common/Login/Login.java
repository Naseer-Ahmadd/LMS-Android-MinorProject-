package com.ghskhawajabagh.lms.Common.Login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ghskhawajabagh.lms.Common.Models.User;
import com.ghskhawajabagh.lms.Common.UTIL.ApplicationPrefrences;
import com.ghskhawajabagh.lms.Common.UTIL.UTIL;
import com.ghskhawajabagh.lms.MainScreen.IMainScreen;
import com.ghskhawajabagh.lms.R;


public class Login extends Fragment implements ILogin {


    View roort;
    EditText etUserName;
    EditText etPassword;
    TextView signup,login;
    LoginPresenter presenter;
    TextView teaherLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      roort=inflater.inflate(R.layout.fragment_login, container, false);

      signup=roort.findViewById(R.id.signup);
      etUserName=roort.findViewById(R.id.editTextEmail);
      etPassword=roort.findViewById(R.id.editTextPassword);
      login=roort.findViewById(R.id.login);
      teaherLogin=roort.findViewById(R.id.teacher_login);
       presenter=new LoginPresenter(this,getActivity());
      signup.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ((IMainScreen)getActivity()).loadSignUpFragment();
          }
      });


      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(validate())
              {
               presenter.signIn(etUserName.getText().toString(),etPassword.getText().toString());
               showLoader("Signing In");
              }
          }
      });
         User usernew=((IMainScreen)getActivity()).getApplicationPrefs().getStoredUser();
        if(usernew!=null)
        {
         onLoginSuccesful(usernew);
        }

        teaherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IMainScreen)getActivity()).loadTeacherDashboardFragment();
            }
        });
      return  roort;
    }

    @Override
    public void onLoginSuccesful(User user) {
        ((IMainScreen)getActivity()).getApplicationPrefs().storeUser(user);
        hideLoader();
     //  onError("Login SUccesful");
        ((IMainScreen)getActivity()).loadStudentDashboardFragment();
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


    public  boolean validate()
    {
        if(etUserName.getText().toString().isEmpty())
        {
            onError("Please enter yout email");

            return false;
        }
         if(!UTIL.Validators.validateEmail(etUserName.getText().toString()))
         {
           onError("Please enter an valid email addreess");
           return  false;
         }

         if(etPassword.getText().toString().isEmpty())
         {
             onError("Please enter your password");
             return  false;
         }

         if(!UTIL.Validators.validatePassword(etPassword.getText().toString()))
         {
             onError("Password is incorrect");
         }


        return  true;

    }

}