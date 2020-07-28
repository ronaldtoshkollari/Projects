import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class HandlerP extends Thread {

    //Class Variables
    ObjectOutputStream out;
    ObjectInputStream in;
    transient Publisher publisher;


    ArrayList<byte[]> chunks;

    //constructor
    public HandlerP(Socket s, Publisher publisher) {
        this.publisher = publisher;
        chunks = new ArrayList<>();

        try {
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }//end of try/catch


    }//end of constructor

    public void run() {
        String user;

        try {

            //receiving user's request
            user = (String) in.readObject();

            //handling user's request
            if (user.trim().equalsIgnoreCase("artist_list")) {

                //reading the artist name that broker asked for his song
                String userRequest = (String) in.readObject();
                System.out.println("Pub is asked to give songs of " + userRequest);

                //creating an array list which will hold the songs of userRequest(artist name)
                ArrayList<String> artist_songs = new ArrayList<>();

                //iterate every publisher's list of songs to find each and every song
                //which belongs to userRequest(artist name) and added to artist_songs list
                for (MusicFile music : publisher.getMusicP1()) {
                    if (userRequest.trim().equalsIgnoreCase(music.getArtistName().trim())) {
                        artist_songs.add(music.getTrackName());
                    }//end of if
                }//end of for

                for (MusicFile music : publisher.getMusicP2()) {
                    if (userRequest.trim().equalsIgnoreCase(music.getArtistName().trim())) {
                        artist_songs.add(music.getTrackName());
                    }//end of if
                }//end of for

                System.out.println(userRequest + " has " + artist_songs.size() + " songs.");

                //sending the list of songs back to broker
                out.writeObject(artist_songs);

            } else if (user.trim().equalsIgnoreCase("song")) {

                String artist = (String) in.readObject();//getting the artist name from broker
                String song = (String) in.readObject();//getting the song name from broker

                System.out.println("Pub is searching for " + song + " of " + artist);

                //updating the music file object of the song the broker asked for
                publisher.readByteStream(song, artist);

                //searching the song the broker asked for in each publisher list
                //and sending back to broker chunk by chunk
                for (MusicFile musicFile : publisher.getMusicP1()) {
                    if (musicFile.getTrackName() == null) continue;
                    if (musicFile.getTrackName().trim().equalsIgnoreCase(song)) {
                        chunk(musicFile);
                        System.out.println(chunks.size());

                        for (byte[] chunk : chunks) {
                            out.writeObject(chunk);
                        }
                    }//end of if
                }//end of for

                for (MusicFile musicFile : publisher.getMusicP2()) {
                    if (musicFile.getTrackName() == null) continue;
                    if (musicFile.getTrackName().trim().equalsIgnoreCase(song)) {
                        chunk(musicFile);

                        for (byte[] chunk : chunks) {
                            out.writeObject(chunk);
                        }
                    }//end of if
                }//end of for


            }//end if


        } catch (IOException | ClassNotFoundException e) {
            disconnect();
            e.printStackTrace();
        } finally {
            disconnect();
        }//end of try/catch/finally


    }//end of run

    public void chunk(MusicFile musicFile) {

        byte[] s = musicFile.getMusicFileExtract();
        chunks = new ArrayList<>();
        int len = s.length;//getting the length of music file bytes array
        int count = 1024;
        int off = 0;
        int i = 1;

        //checking if there is any chunk left to send
        while (len > 0) {

            byte[] chunk;

            //checking if there is a whole chunk remaining to send
            if (len - 1024 >= 0) {
                chunk = new byte[1024];//initialize an array which will hold the chunk
                System.arraycopy(s, off, chunk, 0, chunk.length); //splitting s byte array to chucks of size 1024, and adding each chuck to chucks array list
                chunks.add(chunk);//adding the chunk to array chunks
                off = count * i + 1;
                i++;
            } else {
                chunk = new byte[len];//initialize an array which will hold the last chunk
                System.arraycopy(s, off - 1, chunk, 0, len);//splitting s byte array to chucks of size 1024, and adding each chuck to chucks array list
                chunks.add(chunk);//adding the chunk to array chunks
                off = len;
            }//end of if/else

            len -= count;
        }//end of while

    }//end of chunk


    /*
        Closing Streams
     */
    public void disconnect() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//end try/catch
    }//end of disconnect

}
