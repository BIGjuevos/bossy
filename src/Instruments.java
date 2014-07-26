import Client.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instruments extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;

    private Client client;

    public Instruments() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        dispose();
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
