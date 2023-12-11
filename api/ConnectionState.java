package de.ku.sfl.connection.api;

public enum ConnectionState {
    /**
     * Denotes that the client is deliberately not connected to the server.
     */
    Disconnected,

    /**
     * Denotes that the client is connected to the server.
     */
    Connected,

    /**
     * Denotes that the client should be connected to the server but the connection is currently
     * lost. In this state, an automatic reconnection is tried.
     */
    Lost
}
