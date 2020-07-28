package com.example.srv_spotify;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class Consumer implements Serializable {

    //Consumer variables

    private ArrayList<String> listOfBrokers;
    private ArrayList<Broker> listOfBrokers2;
    private HashMap<String, Broker> brokerMap;
    private ArrayList<String> songs;


    Map<String, BigInteger> artists_hash_map;    //{artist,hash_value}

    String artists_hash_string;

    //Socket Variables
    transient Socket requestSocket;
    transient ObjectOutputStream out;
    transient ObjectInputStream in;

    //constructor
    public Consumer() {
        listOfBrokers = new ArrayList<>();
        listOfBrokers2 = new ArrayList<>();
        artists_hash_map = new HashMap<>();
        brokerMap = new HashMap<>();
        songs = new ArrayList<>();

    }//end of constructor



    //Convert from JSON String to Broker
    public void fromJson_to_Broker() throws JSONException {

        //iterate brokers list and convert every json string broker to broker object
        for (String s : getListOfBrokers()) {

            JSONObject jsonObject = new JSONObject(s);

            Log.d("json", jsonObject.toString());

            System.out.println(jsonObject.getJSONArray("topicsB2"));

            Broker br = new Broker(jsonObject.getString("IP"), jsonObject.getString("Port"), jsonObject.getJSONArray("topicsB1"), jsonObject.getJSONArray("topicsB2"), jsonObject.getJSONArray("topicsP1"), jsonObject.getJSONArray("topicsP2"));

            //adding broker objects to list of brokers
            listOfBrokers2.add(br);
        }

        //Just to ensure that data we passed is ok, making some prints
        for (Broker b : listOfBrokers2) {

            for (int i = 0; i < b.getTopicsP1().length(); i++) {
                System.out.println("Topics_1_hel " + b.getTopicsP1().get(i));
            }

            for (int i = 0; i < b.getTopicsB2().length(); i++) {
                System.out.println("Topics_B2_hel " + b.getTopicsB2().getJSONObject(i).getString("artistName"));
            }

        }
    }


    /*We use Gson library to get the Json String and
    convert into into a hashmap that contains each artist
    and his hash value  */

    public void from_Json_to_Artist_HashMap(String artists_hash) throws JSONException {


        Gson gson = new Gson();

        //convert json string hash map to hash map list
        artists_hash_map = gson.fromJson(artists_hash, HashMap.class);

        Log.d("Done", "Line 78");

        for (String obj : artists_hash_map.keySet()) {
            System.out.println(obj + " " + artists_hash_map.get(obj));
        }


    }

    /* getters and setters */

    public HashMap<String, Broker> getBrokerMap() {
        return brokerMap;
    }

    public void setBrokerMap() throws JSONException {

        try {
            //updating hash map key: artistName value: broker
            for (int i = 0; i < getListOfBrokers2().get(0).getTopicsB1().length(); i++) {


                brokerMap.put(getListOfBrokers2().get(0).getTopicsB1().getJSONObject(i).getString("artistName"), getListOfBrokers2().get(0));
                System.out.println("Artist-Name_1 " + getListOfBrokers2().get(0).getTopicsB1().getJSONObject(i).getString("artistName"));
            }


            for (int i = 0; i < getListOfBrokers2().get(1).getTopicsB2().length(); i++) {

                brokerMap.put(getListOfBrokers2().get(1).getTopicsB2().getJSONObject(i).getString("artistName"), getListOfBrokers2().get(1));
                System.out.println("Artist-Name_2 " + getListOfBrokers2().get(1).getTopicsB2().getJSONObject(i).getString("artistName"));
            }
        }catch (Exception e){
            return;
        }


    }

    public ArrayList<String> getListOfBrokers() {
        return listOfBrokers;
    }

    public ArrayList<Broker> getListOfBrokers2() {
        return listOfBrokers2;
    }

    public void setListOfBrokers(ArrayList<String> listOfBrokers) {
        this.listOfBrokers = listOfBrokers;
    }

    public void setListOfBrokers2(ArrayList<Broker> listOfBrokers2) {
        this.listOfBrokers2 = listOfBrokers2;
    }

    public void setSongsList(ArrayList<String> songs) {
        this.songs = songs;
    }

    public ArrayList<String> getSongsList() {
        return songs;
    }

    //initial connection with default broker in order to get available brokers
    public void getBrokersList() {

        try {

            InetAddress ip;
            ip = InetAddress.getLocalHost();
            String pos = ip.getHostAddress();

            requestSocket = new Socket("192.168.1.2", 3333);
            in = new ObjectInputStream(requestSocket.getInputStream());
            out = new ObjectOutputStream(requestSocket.getOutputStream());

            //telling the server that i am consumer
            out.writeObject("consumer");

            //broker's reply
            String server = (String) in.readObject();

            Log.d("Am I Okay? ", server);

            if (server.trim().equalsIgnoreCase("consumer_ok")) {

                //asking broker for the list of brokers
                out.writeObject("broker_list");
                Log.d("broker_list ", "Asking for List of Brokers");
                //initialize json list of brokers
                setListOfBrokers((ArrayList) in.readObject());
                //converting json list of broker to array list of brokers which contains broker objects
                fromJson_to_Broker();
                //initialize json hash map
                artists_hash_string = (String) in.readObject();
                Log.d("Value", artists_hash_string);
                //converting json hash map to hash map with key: artistName value: broker
                from_Json_to_Artist_HashMap(artists_hash_string);

                Log.d("Convert", "Succesfully converted");


            }

        } catch (IOException | ClassNotFoundException | JSONException e) {
            e.printStackTrace();
        }


    }

    /* Registering to the responsible broker

        for the artist and asking for list of his songs */

    public void register(String Ip, String Port, String artist_Name) {

        try {

            //connects with the broker which is responsible for artist with artist name artist_Name
            requestSocket = new Socket(Ip, Integer.parseInt(Port));
            in = new ObjectInputStream(requestSocket.getInputStream());
            out = new ObjectOutputStream(requestSocket.getOutputStream());

            out.writeObject("consumer");

            //broker's reply
            String server = (String) in.readObject();

            if (server.trim().equalsIgnoreCase("consumer_ok")) {

                //ask broker the list of songs of our artist
                out.writeObject("artist_list");
                out.writeObject(artist_Name);

                //getting the list of songs of artist_Name
                ArrayList<String> artist_Songs = (ArrayList) in.readObject();
                //initialize the list of songs
                setSongsList(artist_Songs);

                for (String s : getSongsList()) {
                    System.out.println("Songs of artist " + s);
                }

            }

        } catch (IOException | ClassNotFoundException e) {


            e.printStackTrace();

        }

    } // end of register


    /* Returns the bytes of the song that user asked */

    public ArrayList<byte[]> get_Song(String Ip, String Port, String Artist_Name, String song) {

        //initialize an array list which will contain the mp3 file
        ArrayList<byte[]> chunks = new ArrayList<>();

        try {

            //connects with the appropriate broker to receive the mpe file chunk by chunk
            requestSocket = new Socket(Ip, Integer.parseInt(Port));
            in = new ObjectInputStream(requestSocket.getInputStream());
            out = new ObjectOutputStream(requestSocket.getOutputStream());

            out.writeObject("consumer");

            //broker's reply
            String server = (String) in.readObject();

            if (server.trim().equalsIgnoreCase("consumer_ok")) {

                //asking for song with name song_name
                out.writeObject("song_name");

                //broker's reply
                server = (String) in.readObject();

                if (server.trim().equalsIgnoreCase("song_ok")) {

                    out.writeObject(Artist_Name);//sending the artist name to appropriate broker
                    out.writeObject(song);//sending the song name to appropriate broker

                    //receiving the mp3 file chunk by chunk
                    byte[] chunk = (byte[]) in.readObject();
                    while (chunk.length > 0) {
                        chunks.add(chunk);
                        if (chunk.length < 1024) break;
                        chunk = (byte[]) in.readObject();
                    }//end of while

                    System.out.println(chunks.size() + " last one: " + chunks.get(chunks.size() - 1).length);

                }
            }


        } catch (IOException | ClassNotFoundException e) {


            e.printStackTrace();

        }

        return chunks;

    }

}









