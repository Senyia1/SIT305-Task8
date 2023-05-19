package com.game.mp4.activity;

import com.game.mp4.BaseBindingActivity;
import com.game.mp4.DBHelper;
import com.game.mp4.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseBindingActivity<ActivityLoginBinding> {

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        viewBinder.tvLogin.setOnClickListener(view -> {
            String username = viewBinder.etUsername.getText().toString().trim();
            String password = viewBinder.etPassword.getText().toString().trim();
            if (username.isEmpty()){
                toast("username is empty");
                return;
            } if (password.isEmpty()){
                toast("password is empty");
                return;
            }
            if (DBHelper.getHelper().login(username,password)){
                startActivity(MainActivity.class);
                toast("login successful");
                finish();
            }else {
                toast("wrong user name or password");
            }
        });
        viewBinder.tvSignUp.setOnClickListener(view -> {
            startActivity(SignActivity.class);
        });
    }
}