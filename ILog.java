package de.ku.sfl.connection;

public interface ILog {

    void trace(String message);

    void warning(String message);

    void error(String message, Exception exception);
}
