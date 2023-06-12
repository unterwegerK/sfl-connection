package de.ku.sfl.connection;

import android.util.Log;

import java.util.Calendar;
import java.util.TimerTask;

public class WatchdogTimerTask extends TimerTask {
    /**
     * Time to wait for a returned ping. If no ping is returned in this time, the connection
     * to the server is disconnected.
     */
    private final static float CONNECTION_TIMEOUT = 100.0f;

    private Client client;
    private final ILog log;

    public WatchdogTimerTask(Client connectionService, ILog log) {
        this.client = connectionService;
        this.log = log;
    }

    @Override
    public void run() {
        checkServerConnection();
    }

    private void checkServerConnection() {
        if (client.isConnectedToServer()) {
            float secondsSinceLastPing = (float) (Calendar.getInstance()
                    .getTimeInMillis() - client.getTimeOfLastPing()
                    .getTimeInMillis()) / 1000.0f;

            if (secondsSinceLastPing > CONNECTION_TIMEOUT) {
                log.trace("Closing connection as "
                        + secondsSinceLastPing
                        + " seconds since last received ping.");
                client.disconnectFromServer();
            }
        } else {
            client.reconnectToServer();
        }
    }
}
