package de.ku.sfl.connection.objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * A medical substance that can be injected to cause effects.
 *
 */
public class Substance {

    private class EffectComparer implements Comparator<SubstanceEffect> {
        @Override
        public int compare(SubstanceEffect lhs, SubstanceEffect rhs) {
            return lhs.getDelay() - rhs.getDelay();
        }
    }

    private final static String INTERNAL_NAME = "internalName";
    private final static String EXTERNAL_NAME = "externalName";
    private final static String TRIVIAL_NAME = "trivialName";
    private final static String NUMBER_OF_EFFECTS = "numberOfEffects";
    private final static String COLOR = "color";
    private final static String EFFECT = "effect";

    /**
     * This substance is an "empty" substance that can be used to model "empty" ampules etc.
     */
    public static final Substance EMPTY;

    private long id;

    private List<SubstanceEffect> effects;
    private int color;
    private String internalName;
    private String externalName;
    private String trivialName;

    static {
        EMPTY = new Substance(new Vector<SubstanceEffect>(), 0, "<leer>", "", "");
    }

    /**
     * For serialization
     */
    @SuppressWarnings("unused")
    private Substance() {

    }

    public Substance(
            List<SubstanceEffect> effects,
            int color,
            String internalName,
            String externalName,
            String trivialName) {
        this.effects = effects;
        this.color = color;
        this.internalName = internalName;
        this.externalName = externalName;
        this.trivialName = trivialName;

        Collections.sort(this.effects, new EffectComparer());
    }

    public Substance(JSONObject substanceObject) throws JSONException {
        int numberOfEffects = substanceObject.getInt(NUMBER_OF_EFFECTS);

        List<SubstanceEffect> effects = new Vector<>(numberOfEffects);
        for (int effectCounter = 0; effectCounter < numberOfEffects; effectCounter++) {
            JSONObject effectObject = substanceObject.getJSONObject(EFFECT + effectCounter);
            effects.add(new SubstanceEffect(effectObject));
        }

        color = substanceObject.getInt(COLOR);
        internalName = getString(substanceObject, INTERNAL_NAME);
        externalName = getString(substanceObject, EXTERNAL_NAME);
        trivialName = getString(substanceObject, TRIVIAL_NAME);
    }

    public static String getString(JSONObject jsonObject, String name) throws JSONException {
        if (jsonObject.isNull(name)) {
            return null;
        } else {
            return jsonObject.getString(name);
        }
    }

    public JSONObject serialize() throws JSONException {
        JSONObject substanceObject = new JSONObject();
        putString(substanceObject, INTERNAL_NAME, internalName);
        putString(substanceObject, EXTERNAL_NAME, externalName);
        putString(substanceObject, TRIVIAL_NAME, trivialName);
        substanceObject.put(COLOR, color);

        substanceObject.put(NUMBER_OF_EFFECTS, effects.size());

        for (int effectIndex = 0; effectIndex < effects.size(); effectIndex++) {
            SubstanceEffect effect = effects.get(effectIndex);
            substanceObject.put(EFFECT + effectIndex, effect.serialize());
        }

        return substanceObject;
    }

    public static void putString(JSONObject object, String name, String string) throws JSONException {
        if (string == null) {
            object.put(name, JSONObject.NULL);
        } else {
            object.put(name, string);
        }
    }

    public int getColor() {
        return color;
    }

    /**
     * Returns the internal name of this substance. This is the name that is shown to the game
     * masters and can be used as a key for the substance. Has to be unique.
     */
    public String getInternalName() {
        return internalName;
    }

    /**
     * Returns the external Name for this substance. This is the name that is shown to players.
     */
    public String getExternalName() {
        return externalName;
    }

    /**
     * Returns the trivial name of this substance. This is the name that describes the substance
     * colloquially.
     */
    public String getTrivialName() {
        return trivialName;
    }

    public List<SubstanceEffect> getEffects() {
        return new Vector<SubstanceEffect>(effects);
    }
}
