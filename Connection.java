package de.ku.sfl.connection;

import org.json.JSONException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.Vector;

import de.ku.sfl.connection.api.ConnectionState;
import de.ku.sfl.connection.api.IConnectionStateListener;

/**
 * This class encapsulates the connection to the server.
 */
public class Connection {

    private final static String TAG = Connection.class.getCanonicalName();

    /**
     * Interval to check whether the connection is broken or whether the connection should be
     * restored.
     */
    private final static float CHECK_INTERVAL = 3.0f;
    private final IMessageDispatcher dispatcher;
    private final ILog log;

    private SocketThread receiveThread;

    public Connection(IMessageDispatcher dispatcher, ILog log){
        this.dispatcher = dispatcher;
        this.log = log;
    }

    private ConnectionState connectionState = ConnectionState.Disconnected;

    private Queue<byte[]> messages = new LinkedList<byte[]>();

    private Calendar timeOfLastPing = Calendar.getInstance();

    private Set<IConnectionStateListener> connectionStateListeners = new HashSet<IConnectionStateListener>();

    private String deviceName = null;
    private InetSocketAddress serverAddress;
    private Timer watchdogTimer = new Timer();

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setServerAddress(InetSocketAddress serverAddress){
        this.serverAddress = serverAddress;
    }

    /**
     * Connects to the set server. If no server is set, this method does nothing.
     */
    public void connectToServer() {
        if (this.deviceName != null && this.serverAddress != null) {
            try {
                receiveThread = new SocketThread(serverAddress, this, log);
                receiveThread.start();
                synchronized (receiveThread) {
                    receiveThread.wait(10000);
                    connectionState = ConnectionState.Connected;
                    notifyConnectionStateListeners();

                }
            } catch (SecurityException e) {
                log.error(TAG, "Error while connecting to server.", e);
            } catch (IllegalArgumentException e) {
                log.error(TAG, "Error while connecting to server.", e);
            } catch (InterruptedException e) {
                log.error(TAG, "Error while connecting to server.", e);
            } catch (Exception e) {
                log.error(TAG, "Error while connecting to server.", e);
            }

            watchdogTimer.cancel();
            watchdogTimer = new Timer();
            watchdogTimer.schedule(new WatchdogTimerTask(this, log),
                    (long) (CHECK_INTERVAL * 1000),
                    (long) (CHECK_INTERVAL * 1000));
        }
    }

    public void disconnectFromServer() {
        terminateConnection();
        connectionState = ConnectionState.Disconnected;
        notifyConnectionStateListeners();
    }

    void connectionIsLost(){
        terminateConnection();
        connectionState = ConnectionState.Lost;
        notifyConnectionStateListeners();
    }

    private void terminateConnection() {
        if (receiveThread != null) {
            receiveThread.close();
            receiveThread = null;
        }
    }

    public ConnectionState getConnectionState() {
        return connectionState;
    }

    /**
     * Returns the connected server's address.
     *
     * This method is thread-safe.
     */
    public InetAddress getServerAddress() {
        return receiveThread.getServerAddress();
    }

    /**
     * Closes the connection to the server and finalizes this service.
     */
    public void close() {
        watchdogTimer.cancel();
        if (receiveThread != null) {
            receiveThread.close();
        }
    }

    /**
     * Returns the time when the last ping from the server was received. If no
     * ping was received yet, the time when the connection was established is returned.
     */
    public Calendar getTimeOfLastPing() {
        synchronized (this) {
            return timeOfLastPing;
        }
    }

    /**
     * This method is called whenever a message is received.
     */
    void handleMessage(byte[] message) throws JSONException, IOException {
        dispatcher.handleMessage(message);
    }

    /**
     * Sends a message to the server. If there is no connection, the message is
     * buffered until sending is possible.
     */
    public void sendMessage(byte[] message,boolean ensureDelivery) throws JSONException, IOException {
        if(ensureDelivery || (receiveThread != null && receiveThread.isConnected())) {
            synchronized (messages) {
                messages.add(message);
            }
        }

        if(receiveThread != null)
        {
            receiveThread.sendPendingMessages();
        }
    }

    public Vector<byte[]> getPendingMessages() {
        synchronized (messages) {
            return new Vector<>(messages);
        }
    }

    public void registerConnectionStateListeners(Set<IConnectionStateListener> listeners) {
        connectionStateListeners.addAll(listeners);
    }

    public void registerConnectionStateListener(IConnectionStateListener listener) {
        connectionStateListeners.add(listener);
    }

    public void unregisterConnectionStateListeners(Set<IConnectionStateListener> listeners) {
        connectionStateListeners.removeAll(listeners);
    }

    public void unregisterConnectionStateListener(IConnectionStateListener listener) {
        connectionStateListeners.remove(listener);
    }

    private void notifyConnectionStateListeners() {
        for(IConnectionStateListener listener : connectionStateListeners) {
            listener.connectionStateChanged(connectionState);
        }
    }
}
