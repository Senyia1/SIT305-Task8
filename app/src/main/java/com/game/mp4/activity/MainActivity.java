package com.game.mp4.activity;

import com.game.mp4.BaseBindingActivity;
import com.game.mp4.DBHelper;
import com.game.mp4.databinding.ActivityMainBinding;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        viewBinder.tvPlay.setOnClickListener(view -> {
            String url = viewBinder.etUrl.getText().toString().trim();
            if (url.isEmpty()) {
                return;
            }
            startActivity(PlayActivity.class, intent -> intent.putExtra("path", url));
        });

        viewBinder.tvAdd.setOnClickListener(view -> {
            String url = viewBinder.etUrl.getText().toString().trim();
            if (url.isEmpty()) {
                return;
            }
            DBHelper.getHelper().addVideo(url);
            toast("success");
        });

        viewBinder.tvList.setOnClickListener(view -> {
            startActivity(ListActivity.class);
        });

    }


}