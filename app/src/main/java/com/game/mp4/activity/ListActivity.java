package com.game.mp4.activity;

import android.view.ViewGroup;



import com.game.mp4.App;
import com.game.mp4.BaseBindingActivity;
import com.game.mp4.DBHelper;
import com.game.mp4.adapter.BindAdapter;
import com.game.mp4.bean.Video;
import com.game.mp4.databinding.ActivityListBinding;
import com.game.mp4.databinding.ItemLinkBinding;

public class ListActivity extends BaseBindingActivity<ActivityListBinding> {
    private BindAdapter<ItemLinkBinding, Video> adapter = new BindAdapter<ItemLinkBinding, Video>() {
        @Override
        public ItemLinkBinding createHolder(ViewGroup parent) {
            return ItemLinkBinding.inflate(getLayoutInflater(), parent, false);
        }

        @Override
        public void bind(ItemLinkBinding itemLinkBinding, Video video, int position) {
            itemLinkBinding.getRoot().setText(video.link);
        }
    };

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        adapter.getData().addAll(DBHelper.getHelper().queryMyVideo(App.user.id));
        viewBinder.rvLink.setAdapter(adapter);
    }
}