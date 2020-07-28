package com.example.srv_spotify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class Saved_Songs extends AppCompatActivity {

    private ListView downloaded_songs_list;
    ArrayAdapter arrayAdapter;
    private ArrayList<String> downloaded_songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_songs);

        //initializing a list view
        downloaded_songs_list = findViewById(R.id.saved_songs_list);

        //initializing an array list which will contain the downloaded songs
        downloaded_songs = new ArrayList<>();

        //adding each and every song in the array list downloaded_songs
        for (File f : getFilesDir().listFiles()) {
            downloaded_songs.add(f.getName());
        }

        //adding song to our arrayAdapter
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, downloaded_songs);
        downloaded_songs_list.setAdapter(arrayAdapter);

        //the code below will be executed when the user clicks on a saved song
        downloaded_songs_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //creating a new intent in order to listen a saved song
                Intent intent = new Intent(Saved_Songs.this, Listen_Saved_Song.class);
                intent.putExtra("Song", downloaded_songs.get(position));

                startActivity(intent);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        //initializing an array list which will contain the downloaded songs
        downloaded_songs = new ArrayList<>();

        //adding each and every song in the array list downloaded_songs
        for (File f : getFilesDir().listFiles()) {
            downloaded_songs.add(f.getName());
        }

        //adding song to our arrayAdapter
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, downloaded_songs);
        downloaded_songs_list.setAdapter(arrayAdapter);

    }
}
