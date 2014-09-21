package Server;

import Information.Engine;
import Logger.Logish;

public class Request extends Logish {
    public static final int TYPE_HELLO = 0;
    public static final int TYPE_INFO_ENGINE = 1;

    protected int type;
    protected boolean hasResponse = false;

    protected String response = "";
    protected Server server;

    private Engine engineInformation;

    public void handle(String message, Server server) {
        this.server = server;
        if ( message.substring(0, 5).equals("hello") ) {
            /**
             * welcome message
             */
            String name = message.substring(6).trim();
            this.logger.debug("Get welcome message from " + name);

            this.response = "welcome " + name;
            this.hasResponse = true;
        } else if ( message.substring(0, 1).equals("E") ) {
            /**
             * engine status update
             */
            Engine e = this.server.getEngineInformation();

            String[] parts = message.split(" ");
            int engine = Integer.parseInt(parts[1]);

            if ( message.substring(0,3).equals("E T") ) {
                int throttle = Integer.parseInt(parts[2]);

                //update the specific engine
                switch (engine) {
                    case 0:
                        e.setFrontLeftThrottle(throttle);
                        break;
                    case 1:
                        e.setFrontRightThrottle(throttle);
                        break;
                    case 2:
                        e.setBackLeftThrottle(throttle);
                        break;
                    case 3:
                        e.setBackRightThrottle(throttle);
                        break;
                }
            } else if ( message.substring(0,3).equals("E R") ) {
                int trim = Integer.parseInt(parts[2]);

                //update the specific engine
                switch (engine) {
                    case 0:
                        e.setFrontLeftTrim(trim);
                        break;
                    case 1:
                        e.setFrontRightTrim(trim);
                        break;
                    case 2:
                        e.setBackLeftTrim(trim);
                        break;
                    case 3:
                        e.setBackRightTrim(trim);
                        break;
                }
            }
        } else {
            /**
             * unknown request
             */
            this.logger.debug("Invalid message received '" + message + "'");
        }
    }

    public String getResponse() {
        return this.response;
    }

    public boolean hasResponse() {
        return this.hasResponse;
    }

    public int getType() {
        return this.type;
    }

    public Engine getEngineInformation() {
        return engineInformation;
    }

    public void setEngineInformation(Engine engineInformation) {
        this.engineInformation = engineInformation;
    }
}
