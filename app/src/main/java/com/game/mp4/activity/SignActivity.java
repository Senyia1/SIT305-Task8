package com.game.mp4.activity;

import com.game.mp4.BaseBindingActivity;
import com.game.mp4.DBHelper;
import com.game.mp4.databinding.ActivitySignBinding;

public class SignActivity extends BaseBindingActivity<ActivitySignBinding> {
    @Override
    protected void initListener() {

    }


    @Override
    protected void initData() {
        viewBinder.tvCrateAccount.setOnClickListener(view -> {
            String fullName = viewBinder.etFullName.getText().toString().trim();
            String username = viewBinder.etUsername.getText().toString().trim();
            String password = viewBinder.etPassword.getText().toString().trim();
            String confirmPassword = viewBinder.etConfirmPassword.getText().toString().trim();
            if (fullName.isEmpty()) {
                toast("full name is empty");
                return;
            }
            if (username.isEmpty()) {
                toast("username is empty");
                return;
            }
            if (password.isEmpty()) {
                toast("password is empty");
                return;
            }
            if (confirmPassword.isEmpty()) {
                toast("confirm password is empty");
                return;
            }
            if (!password.equals(confirmPassword)) {
                toast("confirm password error");
                return;
            }
            if (DBHelper.getHelper().signUp(fullName, username, password)) {
                toast("registration success");
            } else {
                toast("Username or mobile number already in use");
            }
        });
    }



}