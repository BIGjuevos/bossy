import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Information.*;
import Interface.CommandControl;
import Interface.Instruments;
import Server.*;
import Logger.*;
import Client.*;

public class Bossy extends JFrame {
    /**
     * Silly UI Elements
     */
    private JPanel contentPane;
    private JButton launchCommandAndControlButton;
    private JButton launchVirtualInstrumentationButton;
    private JButton quitButton;
    private JButton startServerButton;
    private JButton stopServerButton;
    private JTextPane debugText;
    private JScrollPane debugTextScrollPane;
    private JTextField serverAddressValue;
    private JTextField clientConnectedValue;
    private JTextField serverRunningValue;


    /**
     * Status properties
     */
    private boolean isServerRunning = false;

    /**
     * Useful things
     */
    private Logger logger;

    private Server server;
    private Thread threadServer;

    private Client client;
    private Thread threadClient;

    /**
     * Information about the drone
     */
    private Engine engineInformation;

    public Bossy() {
        //create a new logger first and foremost
        this.logger = new Logger();
        this.logger.setDebugText(debugText);
        this.logger.setScrollPane(debugTextScrollPane);

        //create the client (to send stuff to a client)
        this.client = new Client();
        this.client.setLogger(logger);

        //create the engine information
        this.engineInformation = new Engine();

        //shiny new server instance (how we get information from the client)
        this.server = new Server(31313);
        this.server.setLogger(this.logger);
        this.server.setClient(this.client);
        this.server.setEngineInformation(this.engineInformation);

        /**
         * Do all of this silly UI stuff last
         */
        setContentPane(contentPane);

        /**
         * Quit the applications
         */
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logger.debug("Exiting now");
                System.exit(0);
            }
        });
        /**
         * Start the server
         */
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !isServerRunning ) {
                    //server isn't running let's start it
                    server.allowStart();
                    client.setServer(server);
                    threadServer = new Thread(server);
                    threadServer.start();

                    serverRunningValue.setText("Yes");
                    serverAddressValue.setText(server.getAddress());

                    isServerRunning = true;
                    startServerButton.setEnabled(false);
                    stopServerButton.setEnabled(true);
                }
            }
        });
        /**
         * Stop the server
         */
        stopServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( isServerRunning ) {
                    //server isn't running let's start it
                    server.requestStop();

                    //wait for the thread to exit
                    try {
                        if ( !threadServer.isInterrupted() && threadServer.isAlive() ) {
                            threadServer.join();
                        }
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    serverRunningValue.setText("No");
                    serverAddressValue.setText("N/A");

                    isServerRunning = false;
                    startServerButton.setEnabled(true);
                    stopServerButton.setEnabled(false);
                }
            }
        });
        /**
         * Launch the command and control window
         */
        launchCommandAndControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( isServerRunning ) {
                    CommandControl dialog = new CommandControl();
                    dialog.setClient(client);

                    dialog.setEngineInformation(engineInformation);

                    engineInformation.setCcDialog(dialog);

                    dialog.setTitle("Command & Control");
                    dialog.pack();
                    dialog.setVisible(true);
                } else {
                    System.out.println("Server must be running first");
                }
            }
        });
        launchVirtualInstrumentationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( isServerRunning ) {
                    Instruments dialog = new Instruments();

                    dialog.setClient(client);

                    dialog.setTitle("Virtual Instrument Panel");
                    dialog.pack();
                    dialog.setVisible(true);
                } else {
                    System.out.println("Server must be running first");
                }
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        //init the main bossy screen
        Bossy dialog = new Bossy();
        dialog.setTitle("Bossy: Open Source Quad-Copter Control System");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
}
