import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bossy extends JDialog {
    private JPanel contentPane;
    private JButton quitBossyButton;
    private JButton startAllEnginesButton;
    private JButton start1FLButton;
    private JButton start2FRButton;
    private JButton start3BLButton;
    private JButton start4BRButton;
    private JScrollPane debugWindow;

    private JTextArea ta;

    private Server server;

    public Bossy() {
        setContentPane(contentPane);
        setModal(true);
        quitBossyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        start1FLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.sendMessage("start 1");
            }
        });
        start2FRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.sendMessage("start 2");
            }
        });
        start3BLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.sendMessage("start 3");
            }
        });
        start4BRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.sendMessage("start 4");
            }
        });
        startAllEnginesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                server.sendMessage("start 1");
                server.sendMessage("start 2");
                server.sendMessage("start 3");
                server.sendMessage("start 4");
            }
        });
    }

    public void debugMessage(String message) {
        if ( this.ta == null ) {
            this.ta = new JTextArea();

            DefaultCaret caret = (DefaultCaret)this.ta.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

            this.debugWindow.setViewportView(this.ta);
        }
        this.ta.append(message + "\n");
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public static void main(String[] args) {
        //init the dialog
        Bossy dialog = new Bossy();

        //start the server
        Server server = new Server();
        server.setDialog(dialog);
        Thread t = new Thread( server );
        t.start();

        dialog.setServer(server);

        dialog.pack();
        dialog.setVisible(true);

        System.exit(0);
    }
}
