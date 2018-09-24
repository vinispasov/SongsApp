package com.wasp.songapp.views.mysongslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wasp.songapp.R;
import com.wasp.songapp.models.Song;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SongsArrayAdapter extends ArrayAdapter<Song> {
    private static final String AUTHOR_NAME_FIELD = "Author";
    private static final String TILE_FIELD = "Title";

    @BindView(R.id.iv_song_item_image)
    ImageView mSongImageView;

    @BindView(R.id.tv_song_item_author_name_field)
    TextView mSongAuthorNameFieldTextView;

    @BindView(R.id.tv_song_item_author_name)
    TextView mSongAuthorNameTextView;

    @BindView(R.id.tv_song_item_title_field)
    TextView mSongTitleFieldTextView;

    @BindView(R.id.tv_song_item_title)
    TextView mSongTitleTextView;

    @Inject
    public SongsArrayAdapter(@NonNull Context context) {
        super(context, -1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (Objects.equals(view, null)) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.song_item_arrayadapter_layout, parent, false);
        }

        ButterKnife.bind(this, view);
        Song song = getItem(position);

        Picasso.get()
                .load(song.getImageUrl())
                .resize(150, 150)
                .into(mSongImageView);

        mSongAuthorNameFieldTextView
                .setText(AUTHOR_NAME_FIELD);
        mSongAuthorNameTextView
                .setText(song.getAuthorName());
        mSongTitleFieldTextView
                .setText(TILE_FIELD);
        mSongTitleTextView
                .setText(song.getSongTitle());

        return view;
    }
}
