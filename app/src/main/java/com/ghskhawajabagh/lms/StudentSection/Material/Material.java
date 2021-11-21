package com.ghskhawajabagh.lms.StudentSection.Material;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghskhawajabagh.lms.Common.UTIL.ApplicationPrefrences;
import com.ghskhawajabagh.lms.MainScreen.ActionBar;
import com.ghskhawajabagh.lms.MainScreen.IMainScreen;
import com.ghskhawajabagh.lms.R;


public class Material extends Fragment {

    View root;
    RecyclerView rv;
    ApplicationPrefrences prefrences;
    ActionBar actionBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_material, container, false);
      rv=root.findViewById(R.id.rv_subjects);
      prefrences=((IMainScreen)getActivity()).getApplicationPrefs();
      actionBar=((IMainScreen)getActivity()).getMainActionBar();
      SubjectAdapter adapter=new SubjectAdapter(getActivity(),this,prefrences);
      rv.setLayoutManager(new LinearLayoutManager(getActivity()));
      rv.setAdapter(adapter);

      actionBar.backIcon.setVisibility(View.VISIBLE);
      actionBar.logo.setVisibility(View.INVISIBLE);
      actionBar.actionBarText.setText("Study Material");
      actionBar.backIcon.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ((IMainScreen)getActivity()).loadStudentDashboardFragment();
          }
      });



       return  root;
    }


    public  void onSubjectCliked(String subjectName,int className)
    {
        ((IMainScreen)getActivity()).loadMaterialViewScreen(subjectName,className);
    }
}