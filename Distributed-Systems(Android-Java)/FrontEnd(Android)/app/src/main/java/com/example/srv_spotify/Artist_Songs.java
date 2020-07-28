package com.example.srv_spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Artist_Songs extends AppCompatActivity {

    Consumer c;
    String Ip, Port, artist_name;

    private TextView artist;
    private ListView artist_songs;
    private ArrayList<String> songs; //holds the list of the artist songs

    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist__songs);

        //initialize an array list which will contain the list of songs
        songs = new ArrayList<>();

        //Here we receive inputs from the previous intent
        Intent intent = getIntent();
        Ip = intent.getStringExtra("IP");
        Port = intent.getStringExtra("Port");
        artist_name = intent.getStringExtra("Artist_Name");

        //initializing textview and listview
        artist = (TextView) findViewById(R.id.name_textView);
        artist_songs = (ListView) findViewById(R.id.listview);

        System.out.println("Broker: " + Ip + " " + Port + " is responsible for " + artist_name);

        c = new Consumer();

        //AsyncTasks receives the list of songs
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();

        artist.setText(artist_name);

        //adding song to our arrayAdapter
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songs);

        //Here we define what happens when user clicks on a song of the list
        artist_songs.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("Artist_Songs_Activity", "onItemClick: " + songs.get(i));
                Toast.makeText(Artist_Songs.this, "You clicked on: " + songs.get(i), Toast.LENGTH_SHORT).show();

                //When user selects a song and a new intent starts to play the song
                Intent intent = new Intent(Artist_Songs.this, listen_or_save_song.class);
                intent.putExtra("IP", Ip);
                intent.putExtra("Port", Port);
                intent.putExtra("Artist", artist_name);
                intent.putExtra("Song", songs.get(i));

                startActivity(intent);

            }
        });


    }

    public class AsyncTaskRunner extends AsyncTask<Void, Void, Void> implements Serializable {

        @Override
        protected Void doInBackground(Void... voids) {
            c.register(Ip, Port, artist_name); //gets the list of songs of the specific artist

            for (String s : c.getSongsList()) {
                System.out.println("Songs in background " + s);
                songs.add(s); //adds the songs of the artist to an ArrayList
            }


            return null;
        }

        //We fill listview with the songs we received
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            artist_songs.setAdapter(arrayAdapter);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


}
