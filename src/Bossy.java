import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Server.*;
import Logger.*;

public class Bossy extends JDialog {
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
     * Actual useful things
     */
    private Logger logger;

    private Server server;
    private Thread threadServer;

    public Bossy() {
        //create a new logger first and foremost
        logger = new Logger();
        logger.setDebugText(debugText);
        logger.setScrollPane(debugTextScrollPane);

        //shiny new server instance
        server = new Server(31313);
        server.setLogger(logger);

        /**
         * Do all of this silly UI stuff last
         */
        setContentPane(contentPane);
        setModal(true);

        /**
         * quit the applications
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
    }

    public static void main(String[] args) {
        //init the main bossy screen
        Bossy dialog = new Bossy();
        dialog.setTitle("Bossy: Drone Command and Control System");
        dialog.pack();
        dialog.setVisible(true);

        System.exit(0);
    }
}
