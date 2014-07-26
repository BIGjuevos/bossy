import Client.Client;
import Information.Engine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandControl extends JFrame {
    private JPanel contentPane;
    private JButton buttonClose;
    private JButton startEnginesButton;
    private JButton startFrontLeftButton;
    private JButton startFrontRightButton;
    private JButton startBackLeftButton;
    private JButton startBackRightButton;
    private JButton stopAllEnginesButton;

    private Client client;

    public CommandControl() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonClose);

        /**
         * close this window
         */
        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        /**
         * start our engines
         */
        startEnginesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //start all four engines
                client.send("E S 0");
                client.send("E S 1");
                client.send("E S 2");
                client.send("E S 3");
            }
        });
        stopAllEnginesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stop all four engines
                client.send("E X 0");
                client.send("E X 1");
                client.send("E X 2");
                client.send("E X 3");
            }
        });
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
