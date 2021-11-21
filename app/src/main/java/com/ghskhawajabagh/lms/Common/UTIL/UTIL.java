package com.ghskhawajabagh.lms.Common.UTIL;

import java.util.regex.Pattern;

public  class UTIL {

  public   static class  Validators{

        public  static  boolean validateEmail(String email)
        {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
            if (email == null)
                return false;
            return pat.matcher(email).matches();
        }



        public  static  boolean validatePassword(String etPassword)
        {
            return etPassword.length() <= 8 && etPassword.length() >= 6;
        }
    }


}
