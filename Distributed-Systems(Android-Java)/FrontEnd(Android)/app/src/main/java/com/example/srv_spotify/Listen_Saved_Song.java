package com.example.srv_spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class Listen_Saved_Song extends AppCompatActivity {

    private TextView _song_name;
    private Button _play, _pause, _delete;

    MediaPlayer mediaPlayer;

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen__saved__song);

        //initializing buttons and textviews
        _song_name = findViewById(R.id._song_name);
        _play = findViewById(R.id._play_song);
        _pause = findViewById(R.id._pause_song);
        _delete = findViewById(R.id._delete_song);

        //receiving the inputs from the previous intent
        Intent intent = getIntent();
        String song = intent.getStringExtra("Song");
        Log.d("saved_song", song);

        _song_name.setText(song);

        //creating a new file for the song the user asked for
        file = new File(String.valueOf(getFilesDir() + "/" + song));

    }

    /*
        This method is responsible to play the song that user selected
     */
    public void play(View v) throws IOException {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, Uri.fromFile(file));
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                    _delete.setEnabled(true);
                }
            });


        }

        //song starts playing here
        mediaPlayer.start();
        _play.setEnabled(false);
        _pause.setEnabled(true);
        _delete.setEnabled(false);
    }

    /*
        if user presses the button delete this method will be called
     */
    public void delete(View v) {

        file.delete();
        Toast.makeText(this, "Delete song " + file.getName(), Toast.LENGTH_LONG).show();
        stopPlayer();
        finish();

    }

    /*
        If user presses the button pause this method will be called
     */
    public void pause(View v) {
        if (mediaPlayer != null) {
            _play.setEnabled(true);
            _pause.setEnabled(false);
            _delete.setEnabled(true);
            mediaPlayer.pause();
        }
    }

    /*
       This method is used to clear media player
    */
    private void stopPlayer() {

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(this, "Media player released", Toast.LENGTH_LONG).show();

        }
    }

    protected void onStop() {
        super.onStop();
        stopPlayer();
    }


}
