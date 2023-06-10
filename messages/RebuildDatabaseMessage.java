package de.ku.sfl.connection.messages;

import java.util.Collection;
import java.util.List;

import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;


import de.ku.sfl.connection.objects.DiscoveredReport;
import de.ku.sfl.connection.objects.NamedBooleanValue;
import de.ku.sfl.connection.objects.Image;
import de.ku.sfl.connection.objects.NamedIntegerValue;
import de.ku.sfl.connection.objects.Reaction;
import de.ku.sfl.connection.objects.Substance;

/**
 * This message requests that a client rebuilds its database from the sent information.
 */
public class RebuildDatabaseMessage extends Message {

    private final static String NUMBER_OF_REPORTS = "numberOfReports";
    private final static String REPORT = "report";

    private final static String NUMBER_OF_IMAGES = "numberOfImages";
    private final static String IMAGE = "image";
    private final static String IMAGE_NAME = "name";
    private final static String IMAGE_DATA = "data";

    private final static String NUMBER_OF_SUBSTANCES = "numberOfSubstances";
    private final static String SUBSTANCE = "substance";

    private final static String NUMBER_OF_REACTIONS = "numberOfReactions";
    private final static String REACTION = "reaction";

    private final static String NUMBER_OF_FLAGS = "numberOfFlags";
    private final static String FLAGS = "flags";
    private final static String FLAG_NAME = "flagName";
    private final static String FLAG_STATE = "flagState";

    private final static String NUMBER_OF_NAMED_VALUES = "numberOfNamedValues";
    private final static String NAMED_VALUE = "namedValue";
    private final static String NAMED_VALUE_NAME = "namedValueName";
    private final static String NAMED_VALUE_VALUE = "namedValueValue";

    private final List<DiscoveredReport> reports;
    private final List<Image> images;
    private final List<Substance> substances;
    private final List<Reaction> reactions;
    private final List<NamedBooleanValue> namedBooleanValues;
    private final List<NamedIntegerValue> namedIntegerValues;

    public RebuildDatabaseMessage(
            List<DiscoveredReport> reports,
            List<Image> images,
            List<Substance> substances,
            List<Reaction> reactions,
            List<NamedBooleanValue> namedBooleanValues,
            List<NamedIntegerValue> namedIntegerValues) {
        super(MessageType.RebuildDatabase);

        this.reports = reports;
        this.images = images;
        this.substances = substances;
        this.reactions = reactions;
        this.namedBooleanValues = namedBooleanValues;
        this.namedIntegerValues = namedIntegerValues;
    }

    public RebuildDatabaseMessage(JSONObject object) throws JSONException {
        super(object);

        // reports
        int numberOfReports = object.getInt(NUMBER_OF_REPORTS);
        reports = new Vector<>();
        for (int i = 0; i < numberOfReports; i++) {
            JSONObject reportObject = object.getJSONObject(REPORT + i);

            reports.add(new DiscoveredReport(reportObject));
        }

        // images
        int numberOfImages = object.getInt(NUMBER_OF_IMAGES);
        images = new Vector<>();
        for (int i = 0; i < numberOfImages; i++) {
            JSONObject imageObject = object.getJSONObject(IMAGE + i);

            String name = imageObject.getString(IMAGE_NAME);

            String data = imageObject.getString(IMAGE_DATA);

            images.add(new Image(name, data));
        }

        // substances
        int numberOfSubstances = object.getInt(NUMBER_OF_SUBSTANCES);
        substances = new Vector<>(numberOfSubstances);
        for (int i = 0; i < numberOfSubstances; i++) {
            JSONObject substanceObject = object.getJSONObject(SUBSTANCE + i);
            substances.add(new Substance(substanceObject));
        }

        // reactions
        int numberOfReactions = object.getInt(NUMBER_OF_REACTIONS);
        reactions = new Vector<>(numberOfReactions);
        for (int i = 0; i < numberOfReactions; i++) {
            JSONObject reactionObject = object.getJSONObject(REACTION + i);
            reactions.add(new Reaction(reactionObject, substances));
        }

        // flags
        int numberOfFlags = object.getInt(NUMBER_OF_FLAGS);
        namedBooleanValues = new Vector<>(numberOfReactions);
        for(int i = 0; i < numberOfFlags; i++) {
            JSONObject flagObject = object.getJSONObject(FLAGS + i);
            String name = flagObject.getString(FLAG_NAME);
            boolean state = flagObject.getBoolean(FLAG_STATE);
            namedBooleanValues.add(new NamedBooleanValue(name, state));
        }

        // named values
        int numberOfNamedValues = object.getInt(NUMBER_OF_NAMED_VALUES);
        namedIntegerValues = new Vector<>(numberOfNamedValues);
        for(int i = 0; i < numberOfNamedValues; i++) {
            JSONObject namedValueObject = object.getJSONObject(NAMED_VALUE + i);
            String name = namedValueObject.getString(NAMED_VALUE_NAME);
            int value = namedValueObject.getInt(NAMED_VALUE_VALUE);
            namedIntegerValues.add(new NamedIntegerValue(name, value));
        }
    }

    @Override
    public JSONObject serialize() throws JSONException {
        JSONObject object = super.serialize();

        // reports
        object.put(NUMBER_OF_REPORTS, reports.size());
        int reportCounter = 0;
        for (DiscoveredReport report : reports) {
            object.put(REPORT + reportCounter++, report.serialize());
        }

        // images
        object.put(NUMBER_OF_IMAGES, images.size());
        int imageCounter = 0;
        for (Image image : images) {
            JSONObject imageObject = new JSONObject();
            imageObject.put(IMAGE_NAME, image.getName());
            imageObject.put(IMAGE_DATA, image.getAsBase64());

            object.put(IMAGE + imageCounter++, imageObject);
        }

        // substances
        object.put(NUMBER_OF_SUBSTANCES, substances.size());
        int substanceCounter = 0;
        for (Substance substance : substances) {
            object.put(SUBSTANCE + substanceCounter++, substance.serialize());
        }

        // Reaktionen
        object.put(NUMBER_OF_REACTIONS, reactions.size());
        int reactionCounter = 0;
        for (Reaction reaction : reactions) {
            object.put(REACTION + (reactionCounter++), reaction.serialize());
        }

        //Schalter
        object.put(NUMBER_OF_FLAGS, namedBooleanValues.size());
        int flagCounter = 0;
        for(NamedBooleanValue flags : this.namedBooleanValues) {

            JSONObject schalterObjekt = new JSONObject();
            schalterObjekt.put(FLAG_NAME, flags.getName());
            schalterObjekt.put(FLAG_STATE, flags.getState());

            object.put(FLAGS + flagCounter, schalterObjekt);
            flagCounter++;
        }

        //Benannte Werte
        object.put(NUMBER_OF_NAMED_VALUES, namedIntegerValues.size());
        int namedIntegerValueCounter = 0;
        for(NamedIntegerValue namedIntegerValue : this.namedIntegerValues) {

            JSONObject wertObjekt = new JSONObject();
            wertObjekt.put(NAMED_VALUE_NAME, namedIntegerValue.getName());
            wertObjekt.put(NAMED_VALUE_VALUE, namedIntegerValue.getValue());

            object.put(NAMED_VALUE + namedIntegerValueCounter, wertObjekt);
            namedIntegerValueCounter++;
        }

        return object;
    }

    /**
     * The reports that are contained in the database.
     */
    public List<DiscoveredReport> getReports() {
        return reports;
    }

    /**
     * The images that are contained in the database.
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * The substances that are contained in the database.
     */
    public List<Substance> getSubstances() {
        return substances;
    }

    /**
     * The reactions that are contained in the database.
     */
    public List<Reaction> getReactions() {
        return reactions;
    }

    /**
     * The flags that are contained in the database.
     */
    public Collection<NamedBooleanValue> getFlags() {
        return namedBooleanValues;
    }

    /**
     * The named values that are contained in the database.
     */
    public Collection<NamedIntegerValue> getNamedValues() { return namedIntegerValues;  }
}
