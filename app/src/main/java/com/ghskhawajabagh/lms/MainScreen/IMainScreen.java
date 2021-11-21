package com.ghskhawajabagh.lms.MainScreen;

import android.view.View;

import com.ghskhawajabagh.lms.Common.UTIL.ApplicationPrefrences;

public interface IMainScreen  extends IAPP{
    void showErrorMessage(String msg,String detail);
    void showErrorMessage(String msg);
    void showMessage(String msg , View.OnClickListener listener, String btnText, boolean showIcon, boolean isSuccess);
    void showLoader(String msg,String detail);
    void showLoader(String msg);
    void loadSignInFragment();
    void loadSignUpFragment();
    void loadStudentDashboardFragment();
    void loadTeacherDashboardFragment();
    void loadStudentNoticeBoard();
    void hideActionBar();
    void showActionBar();
    void loadresultScreen();
    void loadAttendenceScreen();
    void loadAdminSection();
    void loadAboutScreen();
    void logout();
    void loadMaterialScreen();
    void loadMaterialViewScreen(String subjectName,int studentClass);

    ActionBar getMainActionBar();
    ApplicationPrefrences getApplicationPrefs();

}
