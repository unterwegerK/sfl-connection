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

/**
 * This thread handles incoming and outgoing messages. It does not hold state, i.e. it can be recreated for
 * each connection.
 */
public class SocketThread extends Thread {
    private volatile boolean cancel;

    private InetSocketAddress serverAddress;

    private final Socket socket;

    private Client client;
    private final ILog log;
    private DataOutputStream outputStream;

    public SocketThread(InetSocketAddress serverAddress, Client client, ILog log) {
        this.serverAddress = serverAddress;
        this.client = client;
        this.log = log;
        socket = new Socket();
    }

    @Override
    public void run() {

        try {
            log.trace("Opening Socket");
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
                        log.trace("Received ping message.");
                        outputStream.writeBoolean(true);
                        outputStream.flush();
                    } else {
                        int length = inputStream.readInt();
                        byte[] buffer = new byte[length];
                        inputStream.read(buffer, 0, length);
                        log.trace("Received message with " + length + " bytes. ");

                        client.handleMessage(buffer);
                    }
                } catch (NoSuchElementException e) {
                    log.warning("NoSuchElementException thrown...");
                    break;
                }
                synchronized (this) {
                    if (cancel) {
                        break;
                    }
                }
            }

            log.trace("Closing Socket.");
            socket.close();
        } catch (IOException e) {
            log.error("Error while creating socket.", e);
        } catch (JSONException e) {
            log.error("Error while reading message", e);
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
        if(client != null && outputStream != null) {
            Vector<byte[]> messages = client.getPendingMessages();

            for (byte[] message : messages) {
                if (socket.isBound() && !socket.isClosed() && socket.isConnected()) {
                    try {
                        outputStream.writeInt(message.length);
                        outputStream.write(message);
                    } catch (IOException e) {
                        log.error("Error while sending message.", e);
                    }
                }
            }
        }
    }
}
