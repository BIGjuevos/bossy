package Interface;

import Client.Client;
import Information.Engine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JTextField throttle1;
    private JTextField throttle2;
    private JTextField throttle3;
    private JTextField throttle4;
    private JButton FrUpButton;
    private JButton FrDownButton;
    private JButton BlUpButton;
    private JButton BlDownButton;
    private JButton BrUpButton;
    private JButton BrDownButton;
    private JButton FlUpButton;
    private JButton FlDownButton;
    private JButton AllUpButton;
    private JButton AllDownButton;
    private JButton FrTrUpButton;
    private JButton FrTrDownButton;
    private JButton BlTrUpButton;
    private JButton BlTrDownButton;
    private JButton BrTrUpButton;
    private JButton BrTrDownButton;
    private JTextField trim1;
    private JTextField trim2;
    private JTextField trim3;
    private JTextField trim4;
    private JButton FlTrUpButton;
    private JButton FlTrDownButton;
    private JButton AllUp10Button;
    private JButton AllDown10Button;

    private Client client;
    private Engine engineInformation;

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
        startFrontLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //start front left engine
                client.send("E S 0");
            }
        });
        startFrontRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //start front right engine
                client.send("E S 1");
            }
        });
        startBackLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //start back left engine
                client.send("E S 2");
            }
        });
        startBackRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //start back right engine
                client.send("E S 3");
            }
        });
        AllUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 0 " + (engineInformation.getFrontLeftThrottle() + 1) );
                client.send("E T 1 " + (engineInformation.getFrontRightThrottle() + 1) );
                client.send("E T 2 " + (engineInformation.getBackLeftThrottle() + 1) );
                client.send("E T 3 " + (engineInformation.getBackRightThrottle() + 1) );
            }
        });
        AllDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 0 " + (engineInformation.getFrontLeftThrottle() - 1) );
                client.send("E T 1 " + (engineInformation.getFrontRightThrottle() - 1) );
                client.send("E T 2 " + (engineInformation.getBackLeftThrottle() - 1) );
                client.send("E T 3 " + (engineInformation.getBackRightThrottle() - 1) );
            }
        });
        FlUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 0 " + (engineInformation.getFrontLeftThrottle() + 1) );
            }
        });
        FlDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 0 " + (engineInformation.getFrontLeftThrottle() - 1) );
            }
        });
        FrUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 1 " + (engineInformation.getFrontRightThrottle() + 1) );
            }
        });
        FrDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 1 " + (engineInformation.getFrontRightThrottle() - 1) );
            }
        });
        BlUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 2 " + (engineInformation.getBackLeftThrottle() + 1) );
            }
        });
        BlDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 2 " + (engineInformation.getBackLeftThrottle() - 1) );
            }
        });
        BrUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 3 " + (engineInformation.getBackRightThrottle() + 1) );
            }
        });
        BrDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 3 " + (engineInformation.getBackRightThrottle() - 1) );
            }
        });
        FlTrUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E R 0 " + (engineInformation.getFrontLeftTrim() + 1) );
            }
        });
        FlTrDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E R 0 " + (engineInformation.getFrontLeftTrim() - 1) );
            }
        });
        FrTrUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E R 1 " + (engineInformation.getFrontRightTrim() + 1) );
            }
        });
        FrTrDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E R 1 " + (engineInformation.getFrontRightTrim() - 1) );
            }
        });
        BlTrUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E R 2 " + (engineInformation.getBackLeftTrim() + 1) );
            }
        });
        BlTrDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E R 2 " + (engineInformation.getBackLeftTrim() - 1) );
            }
        });
        BrTrUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E R 3 " + (engineInformation.getBackRightTrim() + 1) );
            }
        });
        BrTrDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E R 3 " + (engineInformation.getBackRightTrim() - 1) );
            }
        });
        AllUp10Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 0 " + (engineInformation.getFrontLeftThrottle() + 10) );
                client.send("E T 1 " + (engineInformation.getFrontRightThrottle() + 10) );
                client.send("E T 2 " + (engineInformation.getBackLeftThrottle() + 10) );
                client.send("E T 3 " + (engineInformation.getBackRightThrottle() + 10) );
            }
        });
        AllDown10Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("E T 0 " + (engineInformation.getFrontLeftThrottle() - 10) );
                client.send("E T 1 " + (engineInformation.getFrontRightThrottle() - 10) );
                client.send("E T 2 " + (engineInformation.getBackLeftThrottle() - 10) );
                client.send("E T 3 " + (engineInformation.getBackRightThrottle() - 10) );
            }
        });
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEngineInformation(Engine engineInformation) {
        this.engineInformation = engineInformation;
    }

    public JTextField getThrottle1() {
        return throttle1;
    }

    public JTextField getThrottle2() {
        return throttle2;
    }

    public JTextField getThrottle3() {
        return throttle3;
    }

    public JTextField getThrottle4() {
        return throttle4;
    }

    public JTextField getTrim4() {
        return trim4;
    }

    public JTextField getTrim3() {
        return trim3;
    }

    public JTextField getTrim2() {
        return trim2;
    }

    public JTextField getTrim1() {
        return trim1;
    }
}
