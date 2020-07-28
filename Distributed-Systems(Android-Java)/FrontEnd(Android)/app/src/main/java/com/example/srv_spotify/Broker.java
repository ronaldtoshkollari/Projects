package com.example.srv_spotify;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

public class Broker implements Serializable {

    private String ip;
    private String port;

    private JSONArray topicsP1;     //This list contains the topics of pub_1
    private JSONArray topicsP2;     //This list contains the topics of pub_2
    private JSONArray topicsB1;     //This list contains the topics of broker_1
    private JSONArray topicsB2;     //This list contains the topics of broker_2



    //constructors

    public Broker(String ip, String port, JSONArray topicsB1, JSONArray topicsB2, JSONArray topicsP1, JSONArray topicsP2) {
        this.ip = ip;
        this.port = port;
        this.topicsB1 = topicsB1;
        this.topicsB2 = topicsB2;
        this.topicsP1 = topicsP1;
        this.topicsP2 = topicsP2;
    }

    public Broker() {
    }

    //getters and setters
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public JSONArray getTopicsB1() {
        return topicsB1;
    }

    public void setTopicsB1(JSONArray topicsB1) {
        this.topicsB1 = topicsB1;
    }

    public JSONArray getTopicsB2() {
        return topicsB2;
    }

    public void setTopicsB2(JSONArray topicsB2) {
        this.topicsB2 = topicsB2;
    }

    public JSONArray getTopicsP1() {
        return topicsP1;
    }

    public void setTopicsP1(JSONArray topicsP1) {
        this.topicsP1 = topicsP1;
    }

    public JSONArray getTopicsP2() {
        return topicsP2;
    }

    public void setTopicsP2(JSONArray topicsP2) {
        this.topicsP2 = topicsP2;
    }


}

