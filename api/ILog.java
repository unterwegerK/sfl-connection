package de.ku.sfl.connection.api;

public interface ILog {

    void trace(String tag, String message);

    void warning(String tag, String message);

    void error(String tag, String message);

    void error(String tag, String message, Exception exception);
}
