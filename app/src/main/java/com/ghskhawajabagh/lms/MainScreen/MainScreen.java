package com.ghskhawajabagh.lms.MainScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghskhawajabagh.lms.Common.Login.Login;
import com.ghskhawajabagh.lms.Common.Signup.Signup;
import com.ghskhawajabagh.lms.Common.UTIL.ApplicationPrefrences;
import com.ghskhawajabagh.lms.R;
import com.ghskhawajabagh.lms.StudentSection.Attendence.AttendenceScreen;
import com.ghskhawajabagh.lms.StudentSection.Dashboard.StudentDashboard;
import com.ghskhawajabagh.lms.StudentSection.Material.Material;
import com.ghskhawajabagh.lms.StudentSection.Material.ViewMaterial.ViewMaterial;
import com.ghskhawajabagh.lms.StudentSection.NoticeBoard.NotiticeBoard;
import com.ghskhawajabagh.lms.StudentSection.Result.ResultScreen;
import com.ghskhawajabagh.lms.TeacherSection.Dashboard.TeacherDash;
import com.ghskhawajabagh.lms.TeacherSection.Dashboard.TeacherDashboard;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;


public class MainScreen extends AppCompatActivity implements IMainScreen {
    FrameLayout fragmentHost;
    FragmentManager mFragmentManager;
    KProgressHUD loader;
    View actionBar;
    ApplicationPrefrences prefrences;
    ActionBar mainActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_host_screen);
        prefrences=new ApplicationPrefrences(this);
        loader=   KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        mFragmentManager=getSupportFragmentManager();
        actionBar=findViewById(R.id.actionBar);
        mainActionBar =new ActionBar();
        mainActionBar.actionBarText=findViewById(R.id.actionbartext);
        mainActionBar.backIcon=findViewById(R.id.back_icon);
        mainActionBar.logo=findViewById(R.id.logo);
        loadSignInFragment();

      //  loadStudentDashboardFragment();


    }

    @Override
    public void showErrorMessage(String msg, String detail) {

    }

    @Override
    public void showErrorMessage(String msg) {
        loader.setLabel(msg)

                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    public  void showMessage(String msg , View.OnClickListener listener, String btnText, boolean showIcon, boolean isSuccess)
    {

        // create an instance of the snackbar
        View v=findViewById(R.id.snack_host);
        v.bringToFront();
        final Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_LONG);
        // inflate the custom_snackbar_view created previously
        View customSnackView = getLayoutInflater().inflate(R.layout.snackbar_view, null);
        if(showIcon)
        {
            ImageView imgIcon =  customSnackView.findViewById(R.id.snackimageicon);
            imgIcon.setVisibility(View.VISIBLE);
            imgIcon.setImageResource(R.drawable.failure_icon);
            if(isSuccess)
            {
                customSnackView.findViewById(R.id.snackimageicon);
                imgIcon.setImageResource(R.drawable.success_icon);
            }
        }


        // set the background of the default snackbar as transparent
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        // now change the layout of the snackbar
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        // set padding of the all corners as 0
        snackbarLayout.setPadding(0, 0, 0, 0);
        // register the button from the custom_snackbar_view layout file
        Button s_btn = customSnackView.findViewById(R.id.s_btn);
        TextView sText=customSnackView.findViewById(R.id.s_text);
        sText.setText(msg);
        s_btn.setVisibility(View.INVISIBLE);
        if(listener!=null)
        {
            s_btn.setVisibility(View.VISIBLE);
            s_btn.setText(btnText);
            s_btn.setOnClickListener(listener);
        }

        // add the custom snack bar layout to snackbar layout
        snackbarLayout.addView(customSnackView, 0);

        snackbar.show();

    }
    @Override
    public void showLoader(String msg, String detail) {
        loader.setLabel(msg)
                .setDetailsLabel(detail)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoader(String msg) {
        loader.setLabel(msg)

                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    @Override
    public void hideLoader() {
        this.loader.dismiss();
    }

    @Override
    public void loadSignInFragment() {
          hideActionBar();
         loadFragment(new Login());
    }

    @Override
    public void loadSignUpFragment() {
        hideActionBar();
        loadFragment(new Signup());
    }

    @Override
    public void loadStudentDashboardFragment() {
        showActionBar();
       loadFragment(new StudentDashboard());
    }

    @Override
    public void loadTeacherDashboardFragment() {
        hideActionBar();
        loadFragment(new TeacherDashboard());
        startActivity(new Intent(this, TeacherDash.class));
        this.finish();
    }

    @Override
    public void loadStudentNoticeBoard() {
        showActionBar();
        loadFragment(new NotiticeBoard());
    }

    @Override
    public void hideActionBar() {
       actionBar.setVisibility(View.GONE);
    }

    @Override
    public void showActionBar() {
        actionBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadresultScreen() {
        showActionBar();
        loadFragment(new ResultScreen());
    }

    @Override
    public void loadAttendenceScreen() {
        showActionBar();
        loadFragment(new AttendenceScreen());
    }

    @Override
    public void loadAdminSection() {
        hideActionBar();
        loadFragment(new TeacherDashboard());
    }

    @Override
    public void loadAboutScreen() {
        hideActionBar();
        loadFragment(new TeacherDashboard());
    }

    @Override
    public void logout() {
        getApplicationPrefs().clearUserData();
        loadSignInFragment();
    }

    @Override
    public void loadMaterialScreen() {
        showActionBar();
        loadFragment(new Material());
    }

    @Override
    public void loadMaterialViewScreen(String subjectName, int studentClass) {
        showActionBar();
        Bundle args = new Bundle();
        args.putString("sName", subjectName.toUpperCase());
        args.putInt("sClass",studentClass);
        Fragment f=new ViewMaterial();
        f.setArguments(args);
        loadFragment(f);
    }

    @Override
    public ApplicationPrefrences getApplicationPrefs() {
        return this.prefrences;
    }
    @Override
   public ActionBar getMainActionBar(){
        return  this.mainActionBar;
    }
    public void loadFragment(Fragment fragmenttoChange) {
        //   hideEmptyView();// IF IT WAS VISIBLE BECAUSE OF ANY ERROR
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                fragmentTransaction.replace(R.id.fragment_host, fragmenttoChange);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
    }


}