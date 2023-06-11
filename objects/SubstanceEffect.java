package de.ku.sfl.connection.objects;

//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A SubstanceEffect is a physiological effect caused by a substance with a certain delay after
 * injection.
 */
//@Entity
public class SubstanceEffect {

    private final static String EXTERNAL_NAME = "externalName";
    private final static String TRIVIAL_NAME = "trivialName";
    private final static String DELAY = "delay";
    private final static String DESCRIPTION = "description";

    //@Id
    //@GeneratedValue
    private long id;

    private String description;
    private int delay;
    private String externalName;
    private String trivialName;

    /**
     * For serialization...
     */
    @SuppressWarnings("unused")
    private SubstanceEffect() {

    }

    /**
     * Creates a new effect that may change the external name or the trivial name of a substance.
     */
    public SubstanceEffect(String description, int delay, String externalName, String trivialName) {
        this.description = description;
        this.delay = delay;
        this.externalName = externalName;
        this.trivialName = trivialName;
    }

    public SubstanceEffect(JSONObject effectObject) throws JSONException {
        delay = effectObject.getInt(DELAY);
        externalName = getString(effectObject, EXTERNAL_NAME);
        trivialName = getString(effectObject, TRIVIAL_NAME);
        description = getString(effectObject, DESCRIPTION);
    }

    public JSONObject serialize() throws JSONException {
        JSONObject effectObject = new JSONObject();
        effectObject.put(DELAY, delay);
        effectObject.put(EXTERNAL_NAME, getExternalName());
        effectObject.put(TRIVIAL_NAME, getTrivialName());
        effectObject.put(DESCRIPTION, getDescription());

        return effectObject;
    }

    private static String getString(JSONObject jsonObject, String name) throws JSONException {
        if (jsonObject.isNull(name)) {
            return null;
        } else {
            return jsonObject.getString(name);
        }
    }

    /**
     * Creates an effect that does not change neither the external name nor the trivial name of
     * a substance.
     */
    public SubstanceEffect(String description, int delay) {
        this(description, delay, null, null);
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the delay in milliseconds after injection at which this effect is triggered.
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Returns the external name that should replace the previous external name of the substance
     * after this effect is triggered.
     */
    public String getExternalName() {
        return externalName;
    }

    /**
     * Returns the trivial name that should replace the previous trivial name of the substance.
     */
    public String getTrivialName() {
        return trivialName;
    }
}
