package com.ghskhawajabagh.lms.StudentSection.NoticeBoard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghskhawajabagh.lms.Common.Models.Notice;
import com.ghskhawajabagh.lms.MainScreen.ActionBar;
import com.ghskhawajabagh.lms.MainScreen.IMainScreen;
import com.ghskhawajabagh.lms.R;

import java.util.List;

public class NotiticeBoard extends Fragment implements INoticeBoard {

    View root;
    NoticeBoardPresenter presenter;
    RecyclerView rvNotices;
    NoticeAdapter adapter;
    ActionBar actionBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_notitice_board, container, false);
        actionBar=((IMainScreen)getActivity()).getMainActionBar();
        actionBar.actionBarText.setText("Notice Board");
        actionBar.logo.setVisibility(View.INVISIBLE);
        actionBar.backIcon.setVisibility(View.VISIBLE);
        presenter=new NoticeBoardPresenter(this,getActivity());
        rvNotices=root.findViewById(R.id.rv_notices);

        presenter.getNotices();
        showLoader();
        return  root;
    }

    void showLoader()
    {
        ((IMainScreen)getActivity()).showLoader("Loading Notifications");
    }

  public   void hideLoader()
    {

        ((IMainScreen)getActivity()).hideLoader();
    }


    @Override
    public void onNotificationsRecieved(List<Notice> noticeList) {
          hideLoader();
          adapter=new NoticeAdapter(getActivity(),noticeList);
          rvNotices.setLayoutManager(new LinearLayoutManager(getActivity()));
          rvNotices.setAdapter(adapter);
    }

    @Override
    public void onError(String msg) {
        ((IMainScreen)getActivity()).showMessage(msg,null,null,true,false);
    }

    @Override
    public void showLoader(String msg) {

    }


}