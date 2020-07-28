package com.example.srv_spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


/*
    This class is responsible to play or save a song that user selected
 */
public class listen_or_save_song extends AppCompatActivity {

    //Class Variables
    String Ip, Port, artist_name, song;
    public boolean saved = false;
    private TextView _artist;
    private TextView _song;
    private Button _play, _pause, _save;
    ArrayList<byte[]> chunks;
    Consumer c;
    MediaPlayer mediaPlayer;
    byte[] final_song;

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_or_save_song);

        //initializing consumer object and chunks that we will receive
        c = new Consumer();
        chunks = new ArrayList<>();

        //We receive the inputs from the previous intent
        Intent intent = getIntent();
        Ip = intent.getStringExtra("IP");
        Port = intent.getStringExtra("Port");
        artist_name = intent.getStringExtra("Artist");
        song = intent.getStringExtra("Song");


        //initializing buttons and textviews
        _artist = findViewById(R.id._artist);
        _song = findViewById(R.id._song);

        _play = findViewById(R.id._play_song);
        _play.setEnabled(false);

        _pause = findViewById(R.id._pause_song);
        _pause.setEnabled(false);

        _save = findViewById(R.id._save);
        _save.setEnabled(false);

        _artist.setText(artist_name);
        _song.setText(song);

        //The message that displays in logcat in order to verify that we want the right song-artist
        System.out.println("You want " + song + " of " + artist_name);


        Toast.makeText(this, "Waiting till songs loads", Toast.LENGTH_SHORT).show();

        //AsyncTask is called in order to receive the bytes of the song we're asking for
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();


    }


    /*
        This method is responsible to play the song that user selected
     */

    public void play(View v) throws IOException {

        // if player is not used
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, Uri.fromFile(file));
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer(); //when music is done stopPlayer method is called
                }
            });


        }

        //song starts playing here
        mediaPlayer.start();
        _play.setEnabled(false);
        _pause.setEnabled(true);

    }

    /*
        If user presses the button pause this method will be called
     */
    public void pause(View v) {
        if (mediaPlayer != null) {
            _play.setEnabled(true);
            _pause.setEnabled(false);
            mediaPlayer.pause();
        }
    }

    /*
        If user presses the button save this method will be called
     */

    public void save(View v) {

        saved = true;
        _save.setEnabled(false);

    }


    /*
        This method is used to clear media player
     */
    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;

            if (saved == false) {

                file.delete();
            }

            Toast.makeText(this, "Media player released", Toast.LENGTH_LONG).show();

        }
    }

    protected void onStop() {
        super.onStop();
        stopPlayer();
    }


    public class AsyncTaskRunner extends AsyncTask<Void, Void, Void> implements Serializable {

        //we use doInBackground() to get the bytes of the song

        @Override
        protected Void doInBackground(Void... voids) {

            //getting the song chunk by chunk
            chunks = c.get_Song(Ip, Port, artist_name, song); //chunks arraylist received bytes
            System.out.println("Inside task runner " + chunks.size());

            return null;
        }

        /* We use post execute to save and load the song from internal storage*/

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            FileOutputStream fos = null;

            try {
                //byte[] is written to ByteArrayOutputStream
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                for (byte[] tempChunk : chunks) {
                    bos.write(tempChunk);
                }//end of for


                //saving song to our device
                final_song = bos.toByteArray();

                fos = openFileOutput(song + ".mp3", MODE_PRIVATE);
                fos.write(final_song);

                file = new File(String.valueOf(getFilesDir() + "/" + song + ".mp3"));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            //play button is enabled only when bytes are received in order to avoid ecxeption error
            _play.setEnabled(true);
            _save.setEnabled(true);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
