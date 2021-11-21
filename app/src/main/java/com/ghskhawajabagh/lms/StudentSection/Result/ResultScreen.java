package com.ghskhawajabagh.lms.StudentSection.Result;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghskhawajabagh.lms.Common.Models.Result;
import com.ghskhawajabagh.lms.Common.UTIL.ApplicationPrefrences;
import com.ghskhawajabagh.lms.MainScreen.ActionBar;
import com.ghskhawajabagh.lms.MainScreen.IMainScreen;
import com.ghskhawajabagh.lms.R;

import java.util.List;


public class ResultScreen extends Fragment {

     View root;
    ActionBar actionBar;
    RecyclerView rv;
    ResultPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      root= inflater.inflate(R.layout.fragment_result, container, false);
      rv=root.findViewById(R.id.rv_result);
      presenter=new ResultPresenter(getActivity(),this);
      presenter.getResult(((IMainScreen)getActivity()).getApplicationPrefs().getStoredUser());
     actionBar=((IMainScreen)getActivity()).getMainActionBar();
     actionBar.backIcon.setVisibility(View.VISIBLE);
     actionBar.backIcon.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             ((IMainScreen)getActivity()).loadStudentDashboardFragment();
         }
     });

     actionBar.logo.setVisibility(View.INVISIBLE);
     actionBar.actionBarText.setText("Result");

      return  root;
    }


    public  void onResultRecieved(List<Result> list)
    {
        if(list.size()==0)
        {
          onError("Your result is not uploaded yet");
          return;
        }
        ResultAdapter adapter=new ResultAdapter(getActivity(),list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
    }

    public  void onError(String msg)
    {
        ((IMainScreen)getActivity()).showMessage(msg,null,null,false,false);

    }

}