import com.mpatric.mp3agic.*;

import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class Publisher implements Serializable {

    //publisher variables
    static final String PATH = "Dataset";

    private int port; //the port of publisher
    private String ip; // the ip of publisher

    private ArrayList<ArtistName> artistsB1; //it contains the artists that Broker 1 is responsible for
    private ArrayList<ArtistName> artistsB2; //it contains the artists that Broker 2 is responsible for
    private ArrayList<String> artistsP1; // it contains the artists that pub 1 is responsible for
    private ArrayList<String> artistsP2; // it contains the artists tha pub 2 is responsible for
    private ArrayList<MusicFile> musicP1; // it contains the data of songs for pub 1
    private ArrayList<MusicFile> musicP2; // it contains the data of songs for pub 2
    private ArrayList<Broker> brokersList; //it contains the list of available brokers
    private HashMap<String, BigInteger> artistHash; // it contains a pair of artist name and it's hash value


    //Socket Variables
    transient ServerSocket providerSocket;
    transient Socket requestSocket;
    transient ObjectOutputStream out;
    transient ObjectInputStream in;


    public static void main(String[] args) {

        //Creating publisher object with ip and port
        Publisher publisher = new Publisher(args[0], Integer.parseInt(args[1]));
        //Getting the list of available brokers, connecting publisher to default broker
        publisher.getBrokerList(args[2], Integer.parseInt(args[3]));

        //Reading songs from Dataset folder
        publisher.readMusicInfo(PATH);

        //Hashing artist name of publisher1 array list, and distribute the names to the appropriate broker
        //publisher1 is responsible for name A-M
        for (String artist : publisher.artistsP1) {
            publisher.hashTopic(artist);
        }

        //Hashing artist name of publisher2 array list, and distribute the names to the appropriate broker
        for (String artist : publisher.artistsP2) {
            publisher.hashTopic(artist);
        }

        //Initializing lists of each broker
        publisher.init(args[2], Integer.parseInt(args[3]));
        publisher.init(args[4], Integer.parseInt(args[5]));

        //Server side of publisher is starting here. Each request is handled by a thread.
        publisher.connect(args[1]);


    }

    //default constructor
    public Publisher(String ip, int port) {
        this.ip = ip;
        this.port = port;
        artistsP1 = new ArrayList<>();
        artistsP2 = new ArrayList<>();
        artistsB1 = new ArrayList<>();
        artistsB2 = new ArrayList<>();
        brokersList = new ArrayList<>();
        musicP1 = new ArrayList<>();
        musicP2 = new ArrayList<>();
        artistHash = new HashMap<>();
    }//end of constructor

    /* Getters and Setters */

    public ArrayList<ArtistName> getArtistsB1() {
        return artistsB1;
    }

    public void setArtistsB1(ArrayList<ArtistName> artistsB1) {
        this.artistsB1 = artistsB1;
    }

    public ArrayList<ArtistName> getArtistsB2() {
        return artistsB2;
    }

    public void setArtistsB2(ArrayList<ArtistName> artistsB2) {
        this.artistsB2 = artistsB2;
    }

    public ArrayList<String> getArtistsP1() {
        return artistsP1;
    }

    public void setArtistsP1(ArrayList<String> artistsP1) {
        this.artistsP1 = artistsP1;
    }

    public ArrayList<String> getArtistsP2() {
        return artistsP2;
    }

    public void setArtistsP2(ArrayList<String> artistsP2) {
        this.artistsP2 = artistsP2;
    }

    public ArrayList<MusicFile> getMusicP1() {
        return musicP1;
    }

    public void setMusicP1(ArrayList<MusicFile> musicP1) {
        this.musicP1 = musicP1;
    }

    public ArrayList<MusicFile> getMusicP2() {
        return musicP2;
    }

    public void setMusicP2(ArrayList<MusicFile> musicP2) {
        this.musicP2 = musicP2;
    }

    public ArrayList<Broker> getBrokersList() {
        return brokersList;
    }

    public void setBrokersList(ArrayList<Broker> brokersList) {
        this.brokersList = brokersList;
    }

    public HashMap<String, BigInteger> getArtistHash() {
        return artistHash;
    }

    public void setArtistHash(HashMap<String, BigInteger> artistHash) {
        this.artistHash = artistHash;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public void getBrokerList(String ip, int port) {

        try {
            //creating connection and streams
            requestSocket = new Socket(ip, port);
            in = new ObjectInputStream(requestSocket.getInputStream());
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            System.out.println("Connected");

            //informing broker server that this request is made by publisher
            out.writeObject("publisher");

            System.out.println("Give me the broker list");

            //Getting server reply to continue.
            String server = (String) in.readObject();
            if (server.trim().equalsIgnoreCase("publisher_ok")) {
                //asking server to give us all available brokers(list)
                out.writeObject("broker_list");
                brokersList = (ArrayList) in.readObject();
            }//end of if


            System.out.println("Pub got brokers");
            for (Broker b : brokersList) {
                System.out.println(b.getIp() + " " + b.getPort());
            }


        } catch (IOException | ClassNotFoundException e) {
            disconnect();
            e.printStackTrace();
        } finally {
            disconnect();
        }//end of try/catch/finally

    }//end BrokerList

     /*
        Initializing all the lists of publishers and
        informing broker that we are active.
     */

    public void init(String ip, int port) {
        try {
            //creating connection and streams
            requestSocket = new Socket(ip, port);
            in = new ObjectInputStream(requestSocket.getInputStream());
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            System.out.println("Connected");

            //informing broker server that this request is made by publisher
            out.writeObject("publisher");

            //Getting server reply to continue.
            String server = (String) in.readObject();
            if (server.trim().equalsIgnoreCase("publisher_ok")) {

                //sending to brokers the topics that each broker is responsible, and topics that each publisher is responsible
                out.writeObject("topics");
                out.writeObject(artistsB1);
                out.writeObject(artistsB2);
                out.writeObject(artistsP1);
                out.writeObject(artistsP2);
                out.writeObject(artistHash);
                out.writeObject(this);
                System.out.println("Sent");
                System.out.println(artistsB1.size());
                System.out.println(artistsB2.size());
                System.out.println(artistsP1.size());
                System.out.println(artistsP2.size());
                System.out.println(artistHash.size());


            }//end of if

        } catch (IOException | ClassNotFoundException e) {
            disconnect();
            e.printStackTrace();
        } finally {
            disconnect();
        }//end of try/catch/finally

    }//end of init





    /*
        This method is responsible for reading
        the music file metadata and initializing
        our ArrayLists
     */

    public void readMusicInfo(String path) {

        //Reading mp3 files
        File dir = new File(path);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {

            //ignore every file which name starts with . or _
            if (dir_contents[i].getName().startsWith(".") || dir_contents[i].getName().startsWith("_")) continue;

            try {

                //Reading each mp3 file info with our library mp3agic
                Mp3File mp3 = new Mp3File(dir_contents[i]);

                //reading mp3 files that has Id3v2 tags
                if (mp3.hasId3v2Tag()) {
                    ID3v2 id3v2Tag = mp3.getId3v2Tag();
                    String track = id3v2Tag.getTitle();
                    String artist = id3v2Tag.getArtist();
                    String album = id3v2Tag.getAlbum();
                    String genre = id3v2Tag.getGenreDescription();


                    //if artist name is not valid continue to the next song
                    if (artist == null || artist.equalsIgnoreCase("")) {
                        //this.artistsP1.add(artist);
                        continue;
                    }//end of if

                    //if track name is not valid continue to the next song
                    if (track == null || track.equalsIgnoreCase("")) {
                        track = "Unknown_" + i;
                    }//end of if

                    //creating Music File object, on each object is not included bytes[] yet.
                    MusicFile mf = new MusicFile(track, artist, album, genre);

                    //Separating songs based on their artist name
                    if (artist.toUpperCase().charAt(0) >= 'A' && artist.toUpperCase().charAt(0) <= 'M') {
                        musicP1.add(mf);
                        if (artistsP1.contains(artist))
                            continue;
                        else
                            artistsP1.add(artist);
                    } else {
                        this.musicP2.add(mf);
                        if (artistsP2.contains(artist))
                            continue;
                        else
                            artistsP2.add(artist);
                    }//end of if/else

                } else if (mp3.hasId3v1Tag()) { //reading mp3 files that has Id3v1 tags
                    ID3v1 id3v1Tag = mp3.getId3v1Tag();
                    String track = id3v1Tag.getTitle();
                    String artist = id3v1Tag.getArtist();
                    String album = id3v1Tag.getAlbum();
                    String genre = id3v1Tag.getGenreDescription();


                    //if artist name is not valid continue to the next song
                    if (artist == null || artist.equalsIgnoreCase("")) {
                        //this.artistsP1.add(artist);
                        continue;
                    }//end if

                    //if track name is not valid continue to the next song
                    if (track == null || track.equalsIgnoreCase("")) {
                        track = "Unknown_" + i;
                    }//end of if

                    //creating Music File object, on each object is not included bytes[] yet.
                    MusicFile mf = new MusicFile(track, artist, album, genre);

                    //Separating songs based on their artist name
                    if (artist.toUpperCase().charAt(0) >= 'A' && artist.toUpperCase().charAt(0) <= 'M') {
                        musicP1.add(mf);
                        if (artistsP1.contains(artist))
                            continue;
                        else
                            artistsP1.add(artist);
                    } else {
                        this.musicP2.add(mf);
                        if (artistsP2.contains(artist))
                            continue;
                        else
                            artistsP2.add(artist);
                    }//end if/else
                }//end if/else if

            } catch (IOException | UnsupportedTagException | InvalidDataException e) {
                e.printStackTrace();
            }//end of try/catch
        }//end of for

    }//end of readMusicInfo

    /*
        This method hashes artistName string and each one
        of them is distributed to the appropriate
        broker depending on the hash ip+port
     */

    public void hashTopic(String artistName) {


        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }//end try/catch

        byte[] messageDigest = md.digest(artistName.getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        artistHash.put(artistName, no);

        BigInteger b1 = brokersList.get(0).getHash();
        BigInteger b2 = brokersList.get(1).getHash();

        System.out.println("Name: " + artistName);
        System.out.println("Broker_1 " + b1);
        System.out.println("Broker_2 " + b2);
        System.out.println("ArtistHash " + no);

        if (b1.compareTo(b2) < 0) {//b1 is less
            if (no.compareTo(b1) <= 0) {
                artistsB1.add(new ArtistName(artistName));
            } else if (no.compareTo(b2) <= 0) {
                artistsB2.add(new ArtistName(artistName));
            } else {//mod
                if (no.mod(b2).compareTo(b1) <= 0) {
                    artistsB1.add(new ArtistName(artistName));
                } else {
                    artistsB2.add(new ArtistName(artistName));
                }//end of if/else
            }//end of if/else if/else
        } else if (b2.compareTo(b1) < 0) {//b2 is less
            if (no.compareTo(b2) <= 0) {
                artistsB2.add(new ArtistName(artistName));
            } else if (no.compareTo(b1) <= 0) {
                artistsB1.add(new ArtistName(artistName));
            } else {//mod
                if (no.mod(b1).compareTo(b2) <= 0) {
                    artistsB2.add(new ArtistName(artistName));
                } else {
                    artistsB1.add(new ArtistName(artistName));
                }//end of if/else
            }//end of if/else if/else
        }//end of if/else if
    }//end of hashTopic

    /*
        This method is setting up server and waits for broker
        to connect and then creates HandlerP(Thread) for every request.
     */

    public void connect(String port) {
        try {

            //initializing server
            this.providerSocket = new ServerSocket(Integer.parseInt(port));
            this.requestSocket = null;


            while (true) {

                //Waiting for clients to connect
                System.out.println("Server with port " + getPort() + " is waiting for requests");
                this.requestSocket = this.providerSocket.accept();
                System.out.println("Server with port " + getPort() + ": Client connected");
                System.out.println("-------------------------------------------------------------");

                //Server gives to the handler thread the list
                HandlerP handler = new HandlerP(this.requestSocket, this);
                //thread is starting
                handler.start();

            }//end of while
        } catch (IOException e) {
            disconnect();
            e.printStackTrace();
        } finally {
            disconnect();
        }//end of try/catch/finally
    }//end of connect

     /*
        This method is responsible for
        updating the array list's byte stream
     */

    public void readByteStream(String song, String artist) {

        //Reading the mp3 songs
        File dir = new File(PATH);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            //for each song make a fileInputStream
            FileInputStream fis = null;
            try {
                Mp3File mp3 = new Mp3File(dir_contents[i]);

                //if the songs has Id3v2Tag
                if (mp3.hasId3v2Tag()) {
                    ID3v2 id3v2Tag = mp3.getId3v2Tag();
                    String track = id3v2Tag.getTitle();
                    String singer = id3v2Tag.getArtist();

                    //if artist name does not exist
                    if (singer == null) continue;


                    //if track does not have name, update name to Unknown_i
                    if (track == null || track.equalsIgnoreCase("")) {
                        track = "Unknown_" + i;
                    }//end of if

                    //if song name and singer exist continue to read the song bytes
                    if (track.trim().equalsIgnoreCase(song.trim()) && singer.trim().equalsIgnoreCase(artist.trim())) {

                        fis = new FileInputStream(dir_contents[i]);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        byte[] buf = new byte[1024]; //each chuck is 1KB
                        try {
                            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                                bos.write(buf, 0, readNum);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }//end of try/catch

                        byte[] bytes = bos.toByteArray();


                        //searching the publisher's lists to update the appropriate music file byte array
                        for (MusicFile musicFile : getMusicP1()) {
                            if (musicFile.getTrackName() == null) continue;
                            if (musicFile.getArtistName().trim().equalsIgnoreCase(artist.trim()) && musicFile.getTrackName().trim().equalsIgnoreCase(song.trim())) {
                                musicFile.setMusicFileExtract(bytes);
                                return;
                            }//end of if
                        }//end of for

                        for (MusicFile musicFile : getMusicP2()) {
                            if (musicFile.getTrackName() == null) continue;
                            if (musicFile.getArtistName().trim().equalsIgnoreCase(artist.trim()) && musicFile.getTrackName().trim().equalsIgnoreCase(song.trim())) {
                                musicFile.setMusicFileExtract(bytes);
                                return;
                            }//end of if
                        }//end of for
                    }//end of if


                }//end of if

            } catch (IOException | UnsupportedTagException | InvalidDataException e) {
                e.printStackTrace();
            }//end of try/catch

        }//end of for
    }//end of readByteStream



     /*
        Closing Streams
     */

    public void disconnect() {
        try {
            requestSocket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//end of try/catch
    }//end of disconnect


}
