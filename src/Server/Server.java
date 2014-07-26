package Server;

import Client.Client;
import Information.Engine;
import Logger.*;

import java.io.IOException;
import java.net.*;

/**
 * Created by BIGjuevos on 7/24/14.
 *
 * This class represents the server that clients send information to
 */
public class Server extends Logish implements Runnable {
    /**
     * key server information
     */
    private int port; //port that the server will run on
    private DatagramSocket socket; //the socket for the server

    /**
     * Internal operations stuff
     */
    private boolean keepRunning = true; //our quit flag in the event a stop is requested
    private Client client;
    private InetAddress clientInetAddress;
    private Engine engineInformation;

    /**
     * Our wonderful constructor
     */
    public Server(int p) {
        port = p;
    }

    private void setupSocket() {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * get the port number
     *
     * @return int
     */
    public int getPort() {
        return port;
    }

    /**
     * get the host of the server
     *
     * @return String
     */
    public String getAddress() {
        return socket.getLocalSocketAddress().toString();
    }

    public InetAddress getClientInetAddress() {
        return this.clientInetAddress;
    }

    /**
     * Request a stop on the server
     */
    public void requestStop() {
        logger.debug("Server stop requested");
        keepRunning = false;

        //drop ourselves a small useless message to break the loop (kind-of hasckish, but meh)
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");

            byte[] sendData = new byte[1024];
            String sentence = "bugger-off";
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            clientSocket.send(sendPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allowStart() {
        logger.debug("Allow server to start");
        keepRunning = true;
        setupSocket();
    }

    @Override
    public void run() {
        //say our server is starting
        logger.debug("Server starting");

        //set this in case it was set to false
        keepRunning = true;

        //set up some buffer for ourselves
        byte[] recvData = new byte[64];

        logger.debug("About to enter server control loop");
        //we have a socket, we should do something with the socket
        while ( keepRunning ) {
            try {
                recvData = new byte[64];
                DatagramPacket recvPacket = new DatagramPacket(recvData, recvData.length);
                socket.receive(recvPacket);
                this.clientInetAddress = recvPacket.getAddress();

                String sentence = new String( recvPacket.getData() );
                sentence = sentence.trim(); //take off the padding crap from the byte array

                /**
                 * take the inbound sentence, give it to a new request thread, and send it on its merry way
                 * The request object if need be can send off a response to the client
                 */
                Request req = new Request();
                req.setLogger(logger);
                req.handle(sentence, this);

                if  ( req.hasResponse() ) {
                    this.client.send(req.getResponse());
                }
            } catch (Exception e) {
                e.printStackTrace();
                //drop out of the while loop
                logger.debug("Leaving server loop unexpectedly");
                break;
            }
        }

        logger.debug("Server will no longer be responsive to packets");

        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("Problem stopping socket");
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEngineInformation(Engine engineInformation) {
        this.engineInformation = engineInformation;
    }

    public Engine getEngineInformation() {
        return engineInformation;
    }
}
