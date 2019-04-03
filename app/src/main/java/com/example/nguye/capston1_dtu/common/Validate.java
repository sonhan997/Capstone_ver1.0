package com.example.nguye.capston1_dtu.common;

import android.support.design.widget.TextInputLayout;

import java.util.regex.Pattern;


public class Validate {
    public static final Pattern EMAIL_ADDRESS =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
                    +"\\@"+
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
                    "("+
                    "\\."+
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+")+");
    public static final Pattern USERNAME = Pattern.compile("^([a-zA-Z]+[\\'\\,\\.\\-]?[a-zA-Z ]*)+[ ]([a-zA-Z]+[\\'\\,\\.\\-]?[a-zA-Z ]+)+$");
    public  static  final Pattern PASSWORD_PATTERN1 = Pattern.compile("^.{6,15}$"
);
    public boolean validateUsername(String usernameInput, final TextInputLayout textInputNAME ) {


        if (usernameInput.length()==0) {
            textInputNAME.setError("TÊN CỦA BẠN KHÔNG ĐƯỢC ĐỂ TRỐNG\n");

            return false;
        }else if(usernameInput.startsWith(" ")){
            textInputNAME.setError("Not Start Space");
            return false;
        }
        else if (usernameInput.length() > 30) {
            textInputNAME.setError("KHÔNG QUÁ 30 KÍ TỰ\n" + "VÍ DỤ: NGUYEN VAN AN");
            return false;
        } else if(!USERNAME.matcher(usernameInput).matches()){
            textInputNAME.setError("TÊN BẠN NHẬP KHÔNG ĐÚNG ĐỊNH DẠNG\n" + "VÍ DỤ: NGUYEN VAN AN");
            return false;
        }
        else {
            textInputNAME.setError(null);
            return true;
        }


    }
    public boolean validateEmail(String emailInput, TextInputLayout textInputEmail) {
        if (emailInput.isEmpty()) {
            textInputEmail.setError("ĐỊA CHỈ EMAIL KHÔNG ĐƯỢC ĐỂ TRỐNG");
            return false;
        } else if (!EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("EMAIL BẠN NHẬP KHÔNG ĐÚNG ĐỊNH DẠNG\n"+"VÍ DỤ: email12@gmail.com");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    public boolean validatePassword(String passwordInput, TextInputLayout textInputPassword) {
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("MẬT KHẨU KHÔNG ĐƯỢC ĐỂ TRỐNG");
            return false;
        }else if(passwordInput.length()<=5){
            textInputPassword.setError("MẬT KHẨU QUÁ NGẮN, MẬT KHẨU CHỨA ÍT NHẤT 6 KÍ TỰ\n"+"VÍ DỤ: 123456 HOẶC MATKHAU123");
            return false;
        }
        else if (!PASSWORD_PATTERN1.matcher(passwordInput).matches()) {
            textInputPassword.setError("MẬT KHẨU SAI ĐỊNH DẠNG\n"+"VÍ DỤ: 123456 HOẶC MATKHAU123");
            return false;
        }
        else {
            textInputPassword.setError(null);
            return true;
        }
    }
    public boolean validateConfirmPassword(String passwordInput, String confirmPassword, TextInputLayout textInputConfirmPassword ) {
        if (confirmPassword.isEmpty()) {
            textInputConfirmPassword.setError("MẬT KHẨU KHÔNG ĐƯỢC ĐỂ TRỐNG");
            return false;
        }else if(confirmPassword.length()<=5){
            textInputConfirmPassword.setError("MẬT KHẨU QUÁ NGẮN, MẬT KHẨU CHỨA ÍT NHẤT 6 KÍ TỰ\\n\"+\"VÍ DỤ: 123456 HOẶC MATKHAU123");
            return false;
        }
        else if (!PASSWORD_PATTERN1.matcher(confirmPassword).matches()) {
            textInputConfirmPassword.setError("MẬT KHẨU SAI ĐỊNH DẠNG\\n\"+\"VÍ DỤ: 123456 HOẶC MATKHAU123");
            return false;
        }
        else if(!confirmPassword.equals(passwordInput)){
            textInputConfirmPassword.setError("MẬT KHẨU KHÔNG TRÙNG, NHẬP LẠI");
            return  false;
        }
        else {
            textInputConfirmPassword.setError(null);
            return true;
        }
    }

}
