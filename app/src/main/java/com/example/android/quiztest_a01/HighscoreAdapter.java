package com.example.android.quiztest_a01;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HighscoreAdapter extends ArrayAdapter<HighscoreMessage> {
    public HighscoreAdapter(@NonNull Context context, int resource, List<HighscoreMessage> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.highscore_message_layout, parent, false);
        }
        ImageView photoImageView = convertView.findViewById(R.id.user_pic_imageview);
        TextView usernameTextView = convertView.findViewById(R.id.username_textview);
        TextView highscoreTextView = convertView.findViewById(R.id.highscore_text_view);

        HighscoreMessage highscore = getItem(position);

        if (highscore.getUsername() != null) {
            usernameTextView.setText(highscore.getUsername());
        }

        highscoreTextView.setText("" + highscore.getHighscore());

        boolean isPhoto = highscore.getPhotoUrl() != null;

        if (isPhoto) {
            /* use the profile pic
             *  need to find out how to do this
             */
            //photoImageView.setImageResource(highscore.getPhotoUrl());
        } else {
            // use the default pic
            photoImageView.setImageResource(R.drawable.fui_idp_button_background_github);
        }

        return convertView;

    }
}
