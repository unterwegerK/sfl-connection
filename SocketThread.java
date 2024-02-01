package de.ku.sfl.connection;

import org.json.JSONException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Vector;

import de.ku.sfl.connection.api.ILog;

/**
 * This thread handles incoming and outgoing messages. It does not hold state, i.e. it can be recreated for
 * each connection.
 */
public class SocketThread extends Thread {

    private static final String TAG = SocketThread.class.getCanonicalName();

    private volatile boolean cancel;

    private InetSocketAddress serverAddress;

    private final Socket socket;

    private Connection connection;
    private final ILog log;
    private DataOutputStream outputStream;

    public SocketThread(InetSocketAddress serverAddress, Connection connection, ILog log) {
        this.serverAddress = serverAddress;
        this.connection = connection;
        this.log = log;
        socket = new Socket();
    }

    @Override
    public void run() {

        try {
            log.trace(TAG, "Opening Socket");
            socket.connect(serverAddress, 10000);

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            synchronized (this) {
                notifyAll();
            }

            while (true) {
                try {
                    boolean isPingMessage = inputStream.readBoolean();

                    if(isPingMessage) {
                        log.trace(TAG, "Received ping message.");
                        outputStream.writeBoolean(true);
                        outputStream.flush();
                    } else {
                        connection.handleMessage(inputStream);
                    }
                } catch (NoSuchElementException e) {
                    log.warning(TAG, "NoSuchElementException thrown...");
                    break;
                }
                synchronized (this) {
                    if (cancel) {
                        break;
                    }
                }
            }

            log.trace(TAG, "Closing Socket.");
            socket.close();
        } catch (IOException e) {
            log.error(TAG, "Error while creating socket.", e);
        } catch (JSONException e) {
            log.error(TAG, "Error while reading message", e);
        }
    }

    public void close() {
        synchronized (this) {
            cancel = true;
        }
    }

    public boolean isConnected() {
        synchronized (socket) {
            return socket != null ? socket.isConnected() : false;
        }
    }

    public InetAddress getServerAddress(){
        synchronized (socket)
        {
            return socket != null ? socket.getInetAddress() : null;
        }
    }

    public void sendPendingMessages() {
        if(connection != null && outputStream != null) {
            Vector<byte[]> messages = connection.getPendingMessages();

            for (byte[] message : messages) {
                if (socket.isBound() && !socket.isClosed() && socket.isConnected()) {
                    try {
                        outputStream.writeInt(message.length);
                        outputStream.write(message);
                    } catch (IOException e) {
                        log.error(TAG, "Error while sending message.", e);
                    }
                }
            }
        }
    }
}
