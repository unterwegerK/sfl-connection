package de.ku.sfl.connection;

import java.util.Calendar;
import java.util.TimerTask;

import de.ku.sfl.connection.api.ConnectionState;

public class WatchdogTimerTask extends TimerTask {

    private final static String TAG = WatchdogTimerTask.class.getCanonicalName();

    /**
     * Time to wait for a returned ping. If no ping is returned in this time, the connection
     * to the server is disconnected.
     */
    private final static float CONNECTION_TIMEOUT = 100.0f;

    private Connection connection;
    private final ILog log;

    public WatchdogTimerTask(Connection connectionService, ILog log) {
        this.connection = connectionService;
        this.log = log;
    }

    @Override
    public void run() {
        checkServerConnection();
    }

    private void checkServerConnection() {
        if (connection.getConnectionState() == ConnectionState.Connected) {
            float secondsSinceLastPing = (float) (Calendar.getInstance()
                    .getTimeInMillis() - connection.getTimeOfLastPing()
                    .getTimeInMillis()) / 1000.0f;

            if (secondsSinceLastPing > CONNECTION_TIMEOUT) {
                log.trace(TAG, "Closing connection as "
                        + secondsSinceLastPing
                        + " seconds since last received ping.");
                connection.connectionIsLost();
            }
        } else if(connection.getConnectionState() == ConnectionState.Lost) {
            connection.connectToServer();
        }
    }
}
