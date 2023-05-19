package com.game.mp4;

import android.app.Application;

import com.game.mp4.bean.User;

public class App extends Application {
    public static App app;
    public static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

    }

    public static void login(User log) {
        user = log;
    }
    public static User getUser() {
        return user;
    }
}
