package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import Logger.Logish;
import Server.*;

public class Client extends Logish {
    private Server server;

    public void send(String message) {
        this.logger.debug("Sending '" + message + "'");

        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
            byte[] sendData;
            sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.server.getClientInetAddress(), this.server.getPort());
            clientSocket.send(sendPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
