package com.example.android.quiztest_a01;

public class HighscoreMessage {
    /* This class creates a message that contains username and highscore
     * for now message can be created writing those values in apposite TextView
     * later highscore will be calculated by playing games
     * username will be retrieved with user login ID
     */
    private String username;
    private int highscore;
    private String photoUrl;

    public HighscoreMessage() {
    }

    /* An highscore message must need an username and a score
     * I currently don't know if the highscore data should be String like now or change to int
     */
    public HighscoreMessage(String username, int highscore) {
        this.username = username;
        this.highscore = highscore;
    }

    /**
     * New constructor for use custom profile pictures.. right now I don't know ho to do it
     *
     * @param username
     * @param highscore
     * @param photoUrl  this will need to be add in the future
     */
    public HighscoreMessage(String username, int highscore, String photoUrl) {
        this.username = username;
        this.highscore = highscore;
        this.photoUrl = photoUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
