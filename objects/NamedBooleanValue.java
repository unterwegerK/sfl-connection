package de.ku.sfl.connection.objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents a switch that a client can present to the server and other clients.
 */
public class NamedBooleanValue {

    private static final String NAMED_VALUE_NAME = "name";
    private static final String NAMED_VALUE_STATE = "state";

    private String name;
    private boolean state;

    /**
     * This constructor sets the state to false.
     */
    public NamedBooleanValue(String name){
        this(name, false);
    }

    public NamedBooleanValue(String name, boolean initialState){
        this.name = name;
        this.state = initialState;
    }

    public NamedBooleanValue(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString(NAMED_VALUE_NAME);
        state = jsonObject.getBoolean(NAMED_VALUE_STATE);
    }

    public JSONObject serialize() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NAMED_VALUE_NAME, getName());
        jsonObject.put(NAMED_VALUE_STATE, getState());
        return jsonObject;
    }

    public String getName(){
        return name;
    }

    public boolean getState(){
        return state;
    }

    public void setState(boolean newState){
        state = newState;
    }
}
