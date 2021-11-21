package com.ghskhawajabagh.lms.StudentSection.NoticeBoard;

import com.ghskhawajabagh.lms.Common.Models.Notice;
import com.ghskhawajabagh.lms.MainScreen.IAPP;

import java.util.List;

public interface INoticeBoard extends IAPP{
    void onNotificationsRecieved(List<Notice> noticeList);

}
