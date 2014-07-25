package Logger;

/**
 * Created by BIGjuevos on 7/24/14.
 *
 * base class for anything that wants to use the logger facility
 */
public class Logish {
    protected Logger logger;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
