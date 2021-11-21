package com.ghskhawajabagh.lms.StudentSection.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghskhawajabagh.lms.Common.Models.User;
import com.ghskhawajabagh.lms.MainScreen.ActionBar;
import com.ghskhawajabagh.lms.MainScreen.IMainScreen;
import com.ghskhawajabagh.lms.R;

public class StudentDashboard extends Fragment {

   View root;
   View noticeb;
   ActionBar actionBar;
   TextView userInfo;
   User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_student_dashboard, container, false);
       noticeb= root.findViewById(R.id.notices);
       userInfo=root.findViewById(R.id.userInfo);
        actionBar=((IMainScreen)getActivity()).getMainActionBar();
        actionBar.actionBarText.setText("Student Dashboard");
        actionBar.logo.setVisibility(View.VISIBLE);
        actionBar.backIcon.setVisibility(View.INVISIBLE);
        user=((IMainScreen)getActivity()).getApplicationPrefs().getStoredUser();
        userInfo.setText("Name: "+user.name+"\nEmail:"+user.email+"\nPhone: "+user.phone+"\nClass:"+user.studentClass+"th");

      actionBar.backIcon.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ((IMainScreen)getActivity()).loadStudentDashboardFragment();
          }
      });
       noticeb .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CL","CLIKED");
                ((IMainScreen)getActivity()).loadStudentNoticeBoard();
            }
        });

       root.findViewById(R.id.resultcard).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ((IMainScreen)getActivity()).loadresultScreen();
           }
       });

        root.findViewById(R.id.attencard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IMainScreen)getActivity()).loadAttendenceScreen();
            }
        });

        root.findViewById(R.id.logoutcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IMainScreen)getActivity()).logout();
            }
        });

        root.findViewById(R.id.aboutschoolcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IMainScreen)getActivity()).loadAboutScreen();
            }
        });

        root.findViewById(R.id.studyMatrialcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IMainScreen)getActivity()).loadMaterialScreen();
            }
        });

        return  root;
    }
}