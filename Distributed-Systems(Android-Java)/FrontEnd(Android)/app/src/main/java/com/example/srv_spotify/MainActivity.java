package com.example.srv_spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Edits;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Consumer c;

    private TextView final_result;
    private TextView final_result2;
    private TextView broker_1;
    private TextView broker_2;
    private ListView listView;
    private ArrayList<String> singers;
    ArrayAdapter arrayTypeAdapter;

    private Button songs_in_memory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing a Consumer object
        c = new Consumer();

        //initializing the array list singers
        singers = new ArrayList<>();

        //initializing button, textviews and listview
        final_result = (TextView) findViewById(R.id.final_result);

        final_result.setText("Getting Brokers");

        broker_1 = (TextView) findViewById(R.id.broker_1);
        broker_1.setText("Awaiting");

        broker_2 = (TextView) findViewById(R.id.broker_2);
        broker_2.setText("Awaiting");

        final_result2 = (TextView) findViewById(R.id.final_result2);
        final_result2.setText("Awaiting");

        listView = (ListView) findViewById(R.id.listview);

        songs_in_memory = findViewById(R.id.saved_songs);

        songs_in_memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating a new intent for the saved songs
                Intent intent = new Intent(MainActivity.this, Saved_Songs.class);
                startActivity(intent);
            }
        });

        //AsyncTask is called in order to receive the list of available brokers
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();

        //initializing the list of artists
        arrayTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, singers);


        //we move to a new intent when we click on an artist

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("MainActivity", "onItemClick: " + singers.get(i));
                Toast.makeText(MainActivity.this, "You clicked on: " + singers.get(i), Toast.LENGTH_SHORT).show();

                Iterator broker_map = c.getBrokerMap().entrySet().iterator();

                //iterate the artist list in order to find the artist the user asked for
                while (broker_map.hasNext()) {

                    Map.Entry mapElement = (Map.Entry) broker_map.next();
                    String key = (String) mapElement.getKey();

                    //if the singer is in the position i of the list
                    if (key == singers.get(i)) {

                        //getting the broker which is responsible for the artist
                        Broker value = (Broker) mapElement.getValue();

                        //passing Ip,Port and the name of the artist to the new intent

                        Intent intent = new Intent(MainActivity.this, Artist_Songs.class);
                        intent.putExtra("IP", value.getIp());
                        intent.putExtra("Port", value.getPort());
                        intent.putExtra("Artist_Name", singers.get(i));

                        System.out.println("Passing Info to intent " + value.getIp() + " " + value.getPort() + " " + singers.get(i));

                        startActivity(intent);

                        break;
                    }
                }
            }
        });

    }

    private class AsyncTaskRunner extends AsyncTask<Void, Void, Void> implements Serializable {

        @Override
        protected Void doInBackground(Void... voids) {

            //initializing the list of available brokers
            c.getBrokersList();



            try {
                //initializing the hash map with key: artistName and value: broker
                c.setBrokerMap();
                Log.d("Broker_map", "Done");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Iterator broker_map = c.getBrokerMap().entrySet().iterator();

            //iterating the hash map in order to update the array list singers
            while (broker_map.hasNext()) {

                Map.Entry mapElement = (Map.Entry) broker_map.next();
                String key = (String) mapElement.getKey();
                Broker value = (Broker) mapElement.getValue();
                System.out.println(key + " map_set " + value.getPort());
                singers.add(key);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(c.getListOfBrokers2().size() == 0)
                return;

            if(c.getListOfBrokers2().size() == 0)
                return;


            broker_1.setText("IP:" + c.getListOfBrokers2().get(0).getIp() + " Port:" + c.getListOfBrokers2().get(0).getPort());
            broker_2.setText("IP:" + c.getListOfBrokers2().get(1).getIp() + " Port:" + c.getListOfBrokers2().get(1).getPort());
            final_result2.setText("Got brokers");

            listView.setAdapter(arrayTypeAdapter);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);


        }
    }
}
