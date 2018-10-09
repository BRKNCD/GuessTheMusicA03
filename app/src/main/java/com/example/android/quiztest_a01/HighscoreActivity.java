package com.example.android.quiztest_a01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HighscoreActivity extends AppCompatActivity {

    // Views
    Button fakeHighscoreButton;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mHighscoreDatabaseReference;
    private ChildEventListener mChildEventListener;

    String mUsername;

    // for display all the highscore views
    private ListView mHighscoreListView;
    private HighscoreAdapter mHighscoreAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_layout);

        /* Getting the database and the reference child we want to refer
         * in this case highscore
         */
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mHighscoreDatabaseReference = mFirebaseDatabase.getReference().child("highscore");

        // this Button will retrieve the user's name and generate a random highscore
        fakeHighscoreButton = findViewById(R.id.fake_highscore_button);

        Intent mIntent = getIntent();
        mUsername = mIntent.getStringExtra("username");

        // when clicked it generates a fake highscore
        fakeHighscoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // generating a fake highscore points
                int newHighscore = (int) (Math.random() * 101);

                // create a new highscore messagge with fake points and user's name
                HighscoreMessage highscoreMessage = new HighscoreMessage(mUsername, newHighscore);

                // push it to the database
                mHighscoreDatabaseReference.push().setValue(highscoreMessage);

                Toast.makeText(HighscoreActivity.this, mUsername + " " + newHighscore, Toast.LENGTH_SHORT).show();
            }
        });

        // display highscore views
        mHighscoreListView = findViewById(R.id.highscore_listview);
        // Initialize message ListView and its adapter
        List<HighscoreMessage> highscoreMessages = new ArrayList<>();
        mHighscoreAdapter = new HighscoreAdapter(this, R.layout.highscore_message_layout, highscoreMessages);
        mHighscoreListView.setAdapter(mHighscoreAdapter);


        /** This is a listener to retrieve data from FireBase
         *  every time a new child is added (a new highscore is sended) it update the
         *  text views for display the highscore and username
         */
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HighscoreMessage highScoreMessage = dataSnapshot.getValue(HighscoreMessage.class);
                mHighscoreAdapter.add(highScoreMessage);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mHighscoreDatabaseReference.addChildEventListener(mChildEventListener);

    }
}