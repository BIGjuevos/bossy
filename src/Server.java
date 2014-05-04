import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ryan on 5/3/14.
 */
public class Server implements Runnable {
    private Bossy dialog;

    private boolean connected = false;

    private PrintWriter out;

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(31313);
            Socket clientSocket = serverSocket.accept();
            this.out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;

            this.connected = true;
            this.dialog.debugMessage("NOTICE: A client has connected.");

            while ((inputLine = in.readLine()) != null) {
                dialog.debugMessage("RECV: " + inputLine);
                if (inputLine.equals("kthxbye"))
                    break;
            }
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void sendMessage(String message) {
        if ( this.connected ) {
            this.out.print(message + "\n");
            this.out.flush();
            this.dialog.debugMessage("SENT: " + message);
        } else {
            this.dialog.debugMessage("ERROR: Can't send message, a client is not connected.");
        }
    }

    public void setDialog(Bossy dialog) {
        this.dialog = dialog;
    }
}
