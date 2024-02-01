package de.ku.sfl.connection.api;

public interface IMessageSender {
    void sendReportDiscoveredMessage(String reportKey, boolean discovered);

    void sendNamedIntegerValueChangedMessage(String name, int value);

    void sendNamedBooleanValueChangedMessage(String name, boolean value);
}
