package com.ghskhawajabagh.lms.StudentSection.Attendence;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghskhawajabagh.lms.Common.Models.Attendence;
import com.ghskhawajabagh.lms.MainScreen.ActionBar;
import com.ghskhawajabagh.lms.MainScreen.IMainScreen;
import com.ghskhawajabagh.lms.R;

import java.util.List;


public class AttendenceScreen extends Fragment {


    RecyclerView rv;
    ActionBar actionBar;
    View root;
    AttendencePresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      root=inflater.inflate(R.layout.fragment_attendence, container, false);
      rv=root.findViewById(R.id.rv_attendence);
      actionBar=((IMainScreen)getActivity()).getMainActionBar();
      actionBar.backIcon.setVisibility(View.VISIBLE);
      actionBar.logo.setVisibility(View.GONE);
      actionBar.actionBarText.setText("Attendence");


      presenter=new AttendencePresenter(this,getActivity());
      presenter.getResult(((IMainScreen)getActivity()).getApplicationPrefs().getStoredUser());

        actionBar.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IMainScreen)getActivity()).loadStudentDashboardFragment();
            }
        });
      return  root;
    }

    public  void onAttendenceRecived(List<Attendence> list)
    {
        if(list.size()==0)
        {
            onError("Your attendence is not available.");
            return;
        }

        AttendenceAdapter attendenceAdapter=new AttendenceAdapter(getActivity(),list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(attendenceAdapter);

    }

    public  void onError(String msg)
    {
        ((IMainScreen)getActivity()).showMessage(msg,null,null,false,false);

    }
}