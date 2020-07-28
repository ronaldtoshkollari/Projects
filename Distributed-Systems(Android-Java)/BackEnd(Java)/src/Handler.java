
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Handler extends Thread {


    //Class Variables
    ObjectOutputStream out;
    ObjectInputStream in;
    transient Broker broker;

    //Socket Variables
    Socket publisherSocket;
    ObjectInputStream pIn;
    ObjectOutputStream pOut;

    ArrayList<String> broker_list_toJson;

    String _artistHash_String;

    //constructor
    public Handler(Socket s, Broker broker) {
        this.broker = broker;
        try {

            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
            broker_list_toJson = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }//end of try/catch
    }//end of constructor

    public void run() {
        String user;

        //1st action of user(consumer or publisher) is to give identity
        try {
            user = (String) in.readObject();

            System.out.println("User identity " + user);

            //handling consumer requests
            if (user.trim().equalsIgnoreCase("consumer")) {

                //Here thread will handle consumer requests

                System.out.println("The client was identified as a " + user);

                //responding to client
                out.writeObject("consumer_ok");

                user = (String) in.readObject();

                System.out.println("What do you want? " + user);

                //here we handle broker list request
                if (user.trim().equalsIgnoreCase("broker_list")) {

                    //convert Broker objects to JSON and add to the list
                    for (Broker b : broker.getBrokers()) {
                        broker_list_toJson.add(broker.toJSON_Broker(b));
                    }

                    //initializing hash map with key artist name and value broker that is responsible
                    // !!hash map is represented as string!!
                    _artistHash_String = broker.toJSON_artistHash(broker.getArtistHash());

                    System.out.println("Sending Brokers List");

                    //sending JSON objects at consumer's device
                    out.writeObject(broker_list_toJson);
                    out.writeObject(_artistHash_String);

                    System.out.println("End of Sending Brokers");

                } else if (user.trim().equalsIgnoreCase("artist_list")) { //handling user request for song list of a specific artistName

                    //getting request
                    String artistName = (String) in.readObject();
                    System.out.println("You're asking for " + artistName);

                    try {
                        //asking the artistName from the right publisher
                        if (broker.getTopicsP1().contains(artistName)) {

                            System.out.println("First pub is responsible for track");

                            //starting connection with the publisher, broker is client this time
                            publisherSocket = new Socket(broker.getRegisteredPublisher().get(0).getIp(), broker.getRegisteredPublisher().get(0).getPort());
                            pIn = new ObjectInputStream(publisherSocket.getInputStream());
                            pOut = new ObjectOutputStream(publisherSocket.getOutputStream());

                            //asking songs of artist with name artistName
                            pOut.writeObject("artist_list");
                            pOut.writeObject(artistName);

                            ArrayList<String> song = (ArrayList<String>) pIn.readObject();

                            //sending list to the user
                            out.writeObject(song); //sending list to consumer
                            System.out.println("Got songs list.There are " + song.size() + " songs");

                        } else { //if the publisher2 is responsible for that artistName

                            System.out.println("Second pub is responsible for track");

                            //starting connection with the publisher, broker is client this time
                            publisherSocket = new Socket(broker.getRegisteredPublisher().get(1).getIp(), broker.getRegisteredPublisher().get(1).getPort());
                            pIn = new ObjectInputStream(publisherSocket.getInputStream());
                            pOut = new ObjectOutputStream(publisherSocket.getOutputStream());

                            //asking songs of artist with name artistName
                            pOut.writeObject("artist_list");
                            pOut.writeObject(artistName);

                            ArrayList<String> songs = (ArrayList<String>) pIn.readObject();


                            //sending list to the user
                            out.writeObject(songs); //sending list to consumer
                            System.out.println("Got songs.There are " + songs.size() + " songs");
                        }

                    } catch (IOException e/*| ClassNotFoundException e */) {
                        disconnectPublisher();
                        e.printStackTrace();
                    } finally {
                        disconnectPublisher();
                    }//end of try/catch/finally

                } else if (user.trim().equalsIgnoreCase("song_name")) { //handling user request for a specific song

                    //replying to user
                    out.writeObject("song_ok");
                    //getting user artist request
                    user = (String) in.readObject();
                    //getting user song request
                    String song = (String) in.readObject();
                    System.out.print("User wants song: " + song + " of artist " + user + "\n");

                    try {

                        if (broker.getTopicsP1().contains(user)) { //starting connection with the appropriate publisher to get the song

                            //starting connection with the publisher, broker is client this time
                            publisherSocket = new Socket(broker.getRegisteredPublisher().get(0).getIp(), broker.getRegisteredPublisher().get(0).getPort());
                            pIn = new ObjectInputStream(publisherSocket.getInputStream());
                            pOut = new ObjectOutputStream(publisherSocket.getOutputStream());

                            System.out.println(" Pub 1 is responsible for returning chunks of song");

                            //sending request to publisher
                            pOut.writeObject("song");
                            pOut.writeObject(user);
                            pOut.writeObject(song);

                            //getting chunks from publisher a new connection is established for every chunk
                            ArrayList<byte[]> chunks = new ArrayList<>();
                            byte[] chunk = (byte[]) pIn.readObject();
                            while (chunk.length > 0) {
                                chunks.add(chunk);
                                if (chunk.length < 1024) break;
                                chunk = (byte[]) pIn.readObject();
                            }//end of while

                            for (byte[] tempChunk : chunks) {
                                out.writeObject(tempChunk);
                            }//end of for


                        } else { //if publisher2 is responsible for user song request

                            //starting connection with the publisher, broker is client this time
                            publisherSocket = new Socket(broker.getRegisteredPublisher().get(1).getIp(), broker.getRegisteredPublisher().get(1).getPort());
                            pIn = new ObjectInputStream(publisherSocket.getInputStream());
                            pOut = new ObjectOutputStream(publisherSocket.getOutputStream());

                            System.out.println("Pub 2 is responsible for returning chunks of song");

                            //sending request to publisher
                            pOut.writeObject("song");
                            pOut.writeObject(user);
                            pOut.writeObject(song);

                            //getting chunks from publisher a new connection is established for every chunk
                            ArrayList<byte[]> chunks = new ArrayList<>();
                            byte[] chunk = (byte[]) pIn.readObject();
                            while (chunk.length > 0) {
                                chunks.add(chunk);
                                if (chunk.length < 1024) break;
                                chunk = (byte[]) pIn.readObject();
                            }//end of while

                            for (byte[] tempChunk : chunks) {
                                out.writeObject(tempChunk);
                            }//end of for

                        }

                    } catch (IOException e) {

                        disconnectPublisher();
                        e.printStackTrace();

                    } finally {
                        disconnectPublisher();
                    }

                }

            } else {

                //Here the thread will handle publisher requests

                //responding to publisher
                out.writeObject("publisher_ok");

                user = (String) in.readObject();
                if (user.trim().equalsIgnoreCase("broker_list")) {
                    //sending broker list to publisher
                    out.writeObject(broker.getBrokers());

                } else if (user.trim().equalsIgnoreCase("topics")) {

                    //sending topics that publisher asked
                    broker.setTopicsB1((ArrayList) in.readObject());
                    broker.getBrokers().get(1).setTopicsB1(broker.getTopicsB1());

                    broker.setTopicsB2((ArrayList) in.readObject());
                    broker.getBrokers().get(1).setTopicsB2(broker.getTopicsB2());

                    broker.setTopicsP1((ArrayList) in.readObject());
                    broker.getBrokers().get(1).setTopicsP1(broker.getTopicsP1());

                    broker.setTopicsP2((ArrayList) in.readObject());
                    broker.getBrokers().get(1).setTopicsP2(broker.getTopicsP2());

                    broker.setArtistHash((HashMap) in.readObject());

                    System.out.println("Received");
                    System.out.println(broker.getTopicsB1().size());
                    System.out.println(broker.getTopicsB2().size());
                    System.out.println(broker.getTopicsP1().size());
                    System.out.println(broker.getTopicsP2().size());
                    System.out.println(broker.getArtistHash().size());


                    Publisher p = (Publisher) in.readObject();//

                    System.out.println(p.getPort() + " " + p.getIp());

                    if (p.getPort() == 3334)                                  //
                        broker.setP1(p);                                     //
                    else if (p.getPort() == 4443)                             //  it works only for default ports, p1 = 3334 and p2 = 4443
                        broker.setP2(p);                                     //
                    //
                    if (broker.getP1() != null && broker.getP2() != null) {   //
                        broker.getRegisteredPublisher().add(broker.getP1()); //
                        broker.getRegisteredPublisher().add(broker.getP2()); //
                        System.out.println("All good at passing publishers " + broker.getRegisteredPublisher().size());
                    }//end of if


                }//end of if/else if

            }
        } catch (IOException | ClassNotFoundException e) {
            disconnectConsumer();
            e.printStackTrace();
        } finally {
            disconnectConsumer();
        }//end of try/catch/finally
    } // end of run

    /*
        Closing the streams with Publisher
     */
    public void disconnectPublisher() {
        try {
            publisherSocket.close();
            pIn.close();
            pOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//end of try/catch
    }//end of disconnectPublisher

    /*
        Closing the streams with Consumer
     */
    public void disconnectConsumer() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//end of try/catch
    }//end of disconnectConsumer

}
