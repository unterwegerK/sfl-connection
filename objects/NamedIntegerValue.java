package de.ku.sfl.connection.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class NamedIntegerValue {

    private static final String NAMED_VALUE_NAME = "name";
    private static final String NAMED_VALUE_VALUE = "value";

    private final String name;
    private int value;
    private final Object lockObject = new Object();

    public NamedIntegerValue(String name, int initialValue){
        this.name = name;
        this.value = initialValue;
    }

    public NamedIntegerValue(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString(NAMED_VALUE_NAME);
        value = jsonObject.getInt(NAMED_VALUE_VALUE);
    }

    public JSONObject serialize() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NAMED_VALUE_NAME, getName());
        jsonObject.put(NAMED_VALUE_VALUE, getValue());
        return jsonObject;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        synchronized(lockObject) {
            return value;
        }
    }

    public void setValue(int newValue){
        synchronized (lockObject) {
            this.value = newValue;
        }
    }

}
