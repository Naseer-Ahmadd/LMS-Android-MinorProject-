package com.ghskhawajabagh.lms.StudentSection.Material.ViewMaterial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghskhawajabagh.lms.Common.Models.Material;
import com.ghskhawajabagh.lms.Common.Models.Subject;
import com.ghskhawajabagh.lms.MainScreen.ActionBar;
import com.ghskhawajabagh.lms.MainScreen.IMainScreen;
import com.ghskhawajabagh.lms.R;

import java.util.List;

public class ViewMaterial extends Fragment {


    View root;
    RecyclerView rv;
    ActionBar actionBar;
    String subjectName="";
    ViewMaterialPresenter  presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_view_material, container, false);
        rv=root.findViewById(R.id.rv_material);
        Bundle args = getArguments();
        int sclass = args.getInt("sClass", 0);
        subjectName=args.getString("sName","");
        actionBar=((IMainScreen)getActivity()).getMainActionBar();
        actionBar.backIcon.setVisibility(View.VISIBLE);
        actionBar.logo.setVisibility(View.GONE);
        actionBar.actionBarText.setText(subjectName);
        presenter=new ViewMaterialPresenter(this,getActivity());
        if(sclass==0)
        {
           ((IMainScreen) getActivity()).logout();
            return null;
        }
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.getMaterial(subjectName,sclass);

        actionBar.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IMainScreen)getActivity()).loadMaterialScreen();
            }
        });



        return root;
    }


    public  void onMaterialLoaded(List<Material> list)
    {
        if(list.size()==0)
        {
            onError("Subject Study material not added yet");
            return;
        }
        MaterialAdapter adapter=new MaterialAdapter(getActivity(),list,this);
        rv.setAdapter(adapter);

    }

    void onError(String msg)
    {
        ((IMainScreen)getActivity()).showMessage(msg,null,null,false,false);
    }


    public  void viewPdf(String url)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}