import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;


public class Broker implements Serializable {


    private String ip; // The IP of Broker
    private String port; // The Port of Broker listens to

    private ArrayList<String> topicsP1;     //This list contains the topics of pub_1
    private ArrayList<String> topicsP2;     //This list contains the topics of pub_2
    private ArrayList<ArtistName> topicsB1;     //This list contains the topics of broker_1
    private ArrayList<ArtistName> topicsB2;     //This list contains the topics of broker_2

    //private HashMap<Integer, Consumer> registeredUser;
    //int usersId;

    private ArrayList<Publisher> registeredPublisher; //this list contains the registered publishers
    private ArrayList<Broker> brokers; // The list that contains the existing Brokers
    private HashMap<String, BigInteger> artistHash; //Hashmap of artist name and his hash
    private BigInteger hash; //hash of ip+port of broker
    private Publisher p1 = null;
    private Publisher p2 = null;


    transient ServerSocket providerSocket;
    transient Socket connection;

    //The constructor of Broker Class
    public Broker(String ip, String port) {
        this.ip = ip;
        this.port = port;
        brokers = new ArrayList<>();
        registeredPublisher = new ArrayList<>();
        topicsB1 = new ArrayList<>();
        topicsB2 = new ArrayList<>();
        topicsP1 = new ArrayList<>();
        topicsP2 = new ArrayList<>();
        artistHash = new HashMap<>();

    }

    public static void main(String[] args) {

        //Initializing
        Broker broker = new Broker(args[0], args[1]);
        broker.getBrokers().add(broker);

        Broker broker_2 = new Broker(args[2], args[3]);
        broker.getBrokers().add(broker_2);


        broker.connect(Integer.parseInt(args[1]));


    }//end of main

    /* Getters and Setters */
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public ArrayList<Broker> getBrokers() {
        return brokers;
    }

    public void setBrokers(ArrayList<Broker> brokers) {
        this.brokers = brokers;
    }

    public ArrayList<Publisher> getRegisteredPublisher() {
        return registeredPublisher;
    }

    public void setRegisteredPublisher(ArrayList<Publisher> registeredPublisher) {
        this.registeredPublisher = registeredPublisher;
    }

    public ArrayList<ArtistName> getTopicsB1() {
        return topicsB1;
    }

    public void setTopicsB1(ArrayList<ArtistName> topicsB1) {
        this.topicsB1 = topicsB1;
    }

    public ArrayList<ArtistName> getTopicsB2() {
        return topicsB2;
    }

    public void setTopicsB2(ArrayList<ArtistName> topicsB2) {
        this.topicsB2 = topicsB2;
    }

    public ArrayList<String> getTopicsP1() {
        return topicsP1;
    }

    public void setTopicsP1(ArrayList<String> topicsP1) {
        this.topicsP1 = topicsP1;
    }

    public ArrayList<String> getTopicsP2() {
        return topicsP2;
    }

    public void setTopicsP2(ArrayList<String> topicsP2) {
        this.topicsP2 = topicsP2;
    }

    public HashMap<String, BigInteger> getArtistHash() {
        return artistHash;
    }

    public void setArtistHash(HashMap<String, BigInteger> artistHash) {
        this.artistHash = artistHash;
    }

    public BigInteger getHash() {
        return hash;
    }

    public void setHash(BigInteger hash) {
        this.hash = hash;
    }

    public Publisher getP1() {
        return p1;
    }

    public void setP1(Publisher p1) {
        this.p1 = p1;
    }

    public Publisher getP2() {
        return p2;
    }

    public void setP2(Publisher p2) {
        this.p2 = p2;
    }

    /* Method toJson() receives a Broker object and returns it as a JSON String */

    public String toJSON_Broker(Broker b) throws IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("IP", b.ip);
        jsonObject.put("Port", b.port);
        jsonObject.put("topicsB1", b.getTopicsB1());
        jsonObject.put("topicsB2", b.getTopicsB2());
        jsonObject.put("topicsP1", b.getTopicsP1());
        jsonObject.put("topicsP2", b.getTopicsP2());


        System.out.println(jsonObject.toString());

        return jsonObject.toString();

    }

    /* Method toJson() receives a HashMap object and returns it as a JSON String */

    public String toJSON_artistHash(HashMap<String, BigInteger> artistHash) {
        JSONObject jsonObject = new JSONObject(artistHash);
        System.out.println(jsonObject);
        return jsonObject.toString();
    }


    /* Method connect calls Handler class in order to handle the requests*/

    public void connect(int port) {

        try {
            //initializing server
            this.providerSocket = new ServerSocket(port);
            this.connection = null;

            //calculating the ip_port hash number for every broker
            calculateKeys();
            getBrokers().get(1).calculateKeys();

            while (true) {

                //Waiting for clients to connect
                System.out.println("Server with port " + this.getPort() + " is waiting for requests");
                this.connection = this.providerSocket.accept();
                System.out.println("Server with port " + this.getPort() + ": Client connected: " + this.connection.getPort());
                System.out.println("-------------------------------------------------------------");

                //Server gives to the handler thread the list
                Handler handler = new Handler(this.connection, this);
                handler.start();
            }


        } catch (IOException e) {
            disconnect();
            e.printStackTrace();
        } finally {
            disconnect();
        } //end of try//catch// finally

    } // end of connect

    /*
           calculating the hash(Ip+port) for every broker
        */
    public void calculateKeys() {

        //getting broker's ip
        String ip = this.getIp();
        //getting broker's port number
        int port = Integer.parseInt(this.getPort());
        //replacing every dot in ip address with an empty string
        String ip_str = ip.replace(".", "");
        //concatenating ip and port strings
        int ip_port = Integer.parseInt(ip_str) + port;
        String ip_port_str = String.valueOf(ip_port);

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }//end of try/catch

        byte[] messageDigest = md.digest(ip_port_str.getBytes());

        BigInteger no = new BigInteger(1, messageDigest);
        setHash(no);
    }//end of hashTopic

    /*
       closing the streams
    */
    public void disconnect() {
        try {
            providerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//end of try/catch
    }//end of disconnect


}
