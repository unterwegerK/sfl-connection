package de.ku.sfl.connection;

public interface IMessageDispatcher {
    void handleMessage(byte[] message);
}
