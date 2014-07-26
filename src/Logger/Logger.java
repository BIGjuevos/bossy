package Logger;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.sql.Timestamp;

/**
 * Created by BIGjuevos on 7/24/14.
 *
 * This is the class that should be used to log stuff to the debug screen
 */
public class Logger {
    private JTextPane debugText;
    private JScrollPane scrollPane;

    public void debug(String str) {
        append("DEBUG " + str);
    }

    private void append(String str) {
        java.util.Date date = new java.util.Date();
        String leader = "" + new Timestamp(date.getTime());

        leader = String.format("%1$-" + 24 + "s", leader);

        str = leader + str + "\n";

        try {
            Document doc = debugText.getDocument();
            doc.insertString(doc.getLength(), str, null);

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JScrollBar vertical = scrollPane.getVerticalScrollBar();
                    vertical.setValue(vertical.getMaximum());
                }
            });
        } catch(BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void setDebugText(JTextPane dt) {
        debugText = dt;
    }
    public void setScrollPane(JScrollPane sp) {
        scrollPane = sp;
    }
}
