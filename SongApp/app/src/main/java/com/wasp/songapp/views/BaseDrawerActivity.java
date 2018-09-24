package com.wasp.songapp.views;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.wasp.songapp.R;
import com.wasp.songapp.views.addnewsong.SongCreateActivity;
import com.wasp.songapp.views.favouritessongslist.FavouriteSongsListActivity;
import com.wasp.songapp.views.mysongslist.MySongsListActivity;

import butterknife.BindView;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseDrawerActivity extends DaggerAppCompatActivity {

    private static final String MY_SONGS_DRAWER_NAME = "My songs";
    private static final String FAVORITE_SONGS_DRAWER_NAME = "My favourites";
    private static final String ADD_NEW_SONG_DRAWER_NAME = "Add new song";


    @BindView(R.id.tb_drawer_toolbar)
    Toolbar mToolbar;

    private Drawer mDrawer;

    public BaseDrawerActivity() {
        //empty constructor required
    }

    public void setupDrawer() {
        PrimaryDrawerItem mySongsListItem = new PrimaryDrawerItem()
                .withIdentifier(MySongsListActivity.DRAWER_IDENTIFIER)
                .withName(MY_SONGS_DRAWER_NAME);

        SecondaryDrawerItem favoriteSongsListItem = new SecondaryDrawerItem()
                .withIdentifier(FavouriteSongsListActivity.DRAWER_IDENTIFIER)
                .withName(FAVORITE_SONGS_DRAWER_NAME);

        SecondaryDrawerItem addNewSongItem = new SecondaryDrawerItem()
                .withIdentifier(SongCreateActivity.DRAWER_IDENTIFIER)
                .withName(ADD_NEW_SONG_DRAWER_NAME);


        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getToolbar())
                .withCloseOnClick(true)
                .addDrawerItems(
                        mySongsListItem
                                .withIcon(R.drawable.drawermysongsicon),
                        new DividerDrawerItem(),
                        favoriteSongsListItem
                                .withIcon(R.drawable.ic_favourites_icon),
                        new DividerDrawerItem(),
                        addNewSongItem
                                .withIcon(R.drawable.draweraddsongicon),
                        new DividerDrawerItem()

                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    long identifier = drawerItem.getIdentifier();

                    if (getIdentifier() == identifier) {
                        return false;
                    }

                    Intent intent = getNextIntent(identifier);
                    if (intent == null) {
                        return false;
                    }
                    startActivity(intent);
                    return true;
                })
                .build();
    }


    private Intent getNextIntent(long identifier) {

        Intent nextIntent;

        if (identifier == MySongsListActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, MySongsListActivity.class);
            return nextIntent;
        } else if (identifier == SongCreateActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, SongCreateActivity.class);
            return nextIntent;
        } else if (identifier == FavouriteSongsListActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, FavouriteSongsListActivity.class);
            return nextIntent;
        } else {
            return null;
        }
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    protected abstract long getIdentifier();

    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDrawer.closeDrawer();
    }
}


