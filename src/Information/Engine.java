package Information;

import Interface.CommandControl;

public class Engine {
    private int frontLeftThrottle;
    private int frontRightThrottle;
    private int backLeftThrottle;
    private int backRightThrottle;

    private CommandControl ccDialog;

    public int getFrontLeftThrottle() {
        return frontLeftThrottle;
    }

    public void setFrontLeftThrottle(int frontLeftThrottle) {
        this.frontLeftThrottle = frontLeftThrottle;

        if ( ccDialog != null ) {
            ccDialog.getThrottle1().setText(Integer.toString(frontLeftThrottle) );
        }
    }

    public int getFrontRightThrottle() {
        return frontRightThrottle;
    }

    public void setFrontRightThrottle(int frontRightThrottle) {
        this.frontRightThrottle = frontRightThrottle;

        if ( ccDialog != null ) {
            ccDialog.getThrottle2().setText( Integer.toString(frontRightThrottle) );
        }
    }

    public int getBackLeftThrottle() {
        return backLeftThrottle;
    }

    public void setBackLeftThrottle(int backLeftThrottle) {
        this.backLeftThrottle = backLeftThrottle;

        if ( ccDialog != null ) {
            ccDialog.getThrottle3().setText( Integer.toString(backLeftThrottle) );
        }
    }

    public int getBackRightThrottle() {
        return backRightThrottle;
    }

    public void setBackRightThrottle(int backRightThrottle) {
        this.backRightThrottle = backRightThrottle;

        if ( ccDialog != null ) {
            ccDialog.getThrottle4().setText( Integer.toString(backRightThrottle) );
        }
    }

    public void setCcDialog(CommandControl ccDialog) {
        this.ccDialog = ccDialog;
    }
}
