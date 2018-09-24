package com.wasp.songapp.views.addnewsong;

import android.content.Intent;
import android.os.Bundle;
import com.wasp.songapp.R;
import com.wasp.songapp.views.BaseDrawerActivity;
import com.wasp.songapp.views.mysongslist.MySongsListActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SongCreateActivity extends BaseDrawerActivity implements SongCreateContracts.Navigator {
    //fields
    public static final long DRAWER_IDENTIFIER = 298;
    @Inject
    SongCreateFragment mSongCreateFragment;

    @Inject
    SongCreateContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_create);
        ButterKnife.bind(this);

        mSongCreateFragment.setPresenter(mPresenter);
        mSongCreateFragment.setNavigator(this);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_song_create, mSongCreateFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, MySongsListActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}




