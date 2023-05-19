package com.game.mp4.activity;

import android.net.Uri;

import com.game.mp4.BaseBindingActivity;
import com.game.mp4.databinding.ActivityPlayBinding;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;


public class PlayActivity extends BaseBindingActivity<ActivityPlayBinding> {

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        initializePlayer();

    }

    ExoPlayer player;

    private void initializePlayer() {
        player = new ExoPlayer.Builder(this).build();
        viewBinder.playView.setPlayer(player);
        String path = getIntent().getStringExtra("path");
//        String title = getIntent().getStringExtra("title");
//        viewBinder.title.setText(title);
        Uri parse = Uri.parse(path);
        MediaItem item = MediaItem.fromUri(parse);
        player.setMediaItem(item);
        player.setPlayWhenReady(true);
        player.seekTo(0, 0);
        player.prepare();
        player.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }
}