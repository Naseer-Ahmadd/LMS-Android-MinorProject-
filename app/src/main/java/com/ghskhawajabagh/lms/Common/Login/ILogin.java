package com.ghskhawajabagh.lms.Common.Login;

import com.ghskhawajabagh.lms.Common.Models.User;
import com.ghskhawajabagh.lms.MainScreen.IAPP;

public interface ILogin extends IAPP {
    void onLoginSuccesful(User user);

}
