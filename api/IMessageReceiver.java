package de.ku.sfl.connection.api;

import de.ku.sfl.connection.messages.NamedBooleanValueChangedMessage;
import de.ku.sfl.connection.messages.NamedIntegerValueChangedMessage;
import de.ku.sfl.connection.messages.RebuildDatabaseMessage;
import de.ku.sfl.connection.messages.ReportDiscoveredMessage;

public interface IMessageReceiver {
    void handleMessage(RebuildDatabaseMessage message);

    void handleMessage(ReportDiscoveredMessage message);

    void handleMessage(NamedBooleanValueChangedMessage message);

    void handleMessage(NamedIntegerValueChangedMessage message);
}
