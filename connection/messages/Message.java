package de.ku.sfl.connection.messages;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Message {

    public static final String TYPE = "type";
    protected MessageType type;

    protected Message(MessageType type) {
        this.type = type;
    }

    protected Message(JSONObject object) throws JSONException {
        type = MessageType.valueOf(object.getString(TYPE));
    }

    /**
     * Serializes the message to a JSONObject.
     */
    public JSONObject serialize() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(TYPE, type);

        return object;
    }
}
