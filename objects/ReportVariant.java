package de.ku.sfl.connection.objects;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Vector;

/**
 * A variant of a report. It stores data for a report that may change in the course of the game.
 * That means that discovering other reports may change how a certain report looks like. This
 * currently relates to the external name (i.e. the name visible to the player) of a report and the
 * description of the report.
 *
 * An example for this is that a bottle with an unknown liquid is scanned. This can initially be
 * described as "Bottle with unknown liquid". If later the players learn more about the liquid in
 * general, the report may change to "Bottle to liquid XY". Here, the first variant depends on no
 * other report while the second would have a higher priority but depends on the report "Spectral
 * analysis of liquid XY".
 */
public class ReportVariant {
    private final static String EXTERNAL_NAME = "externalName";
    private final static String PRIORITY = "priority";
    private final static String DESCRIPTION = "description";
    private final static String NUMBER_OF_DEPENDENCIES = "numberOfDependencies";
    private final static String DEPENDENCY = "dependency";

    private String externalName;
    private String description;

    private int priority;

    private Report report = null;

    private List<Integer> dependencies = new Vector<Integer>();

    public ReportVariant(String externalName,
                         String description,
                         int priority,
                         List<Integer> dependencies) {
        this.externalName = externalName;
        this.description = description;
        this.priority = priority;
        if (dependencies != null) {
            this.dependencies = dependencies;
        }
    }

    public ReportVariant(JSONObject variantObject) throws JSONException {
        externalName = variantObject.getString(EXTERNAL_NAME);
        description = variantObject.getString(DESCRIPTION);
        priority = variantObject.getInt(PRIORITY);

        int numberOfDependencies = variantObject.getInt(NUMBER_OF_DEPENDENCIES);
        dependencies = new Vector<>(numberOfDependencies);
        for (int b = 0; b < numberOfDependencies; b++) {
            dependencies.add(variantObject.getInt(DEPENDENCY + b));
        }
    }

    public JSONObject serialize() throws JSONException {
        JSONObject variantObject = new JSONObject();
        variantObject.put(EXTERNAL_NAME, getExternalName());
        variantObject.put(DESCRIPTION, getDescription());
        variantObject.put(PRIORITY, getPriority());

        List<Integer> dependencies = getDependencies();
        variantObject.put(NUMBER_OF_DEPENDENCIES, dependencies.size());

        for (int dependencyCounter = 0; dependencyCounter < dependencies.size(); dependencyCounter++) {
            int dependency = dependencies.get(dependencyCounter);
            variantObject.put(DEPENDENCY + dependencyCounter, dependency);
        }
        return variantObject;
    }

    public int getPriority() {
        return priority;
    }

    public String getExternalName() {
        return externalName;
    }

    public String getDescription() {
        return description;
    }

    void setRelatedReport(Report report) {
        this.report = report;
    }

    public Report getRelatedReport() {
        return report;
    }

    public List<Integer> getDependencies() {
        return new Vector<Integer>(dependencies);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ReportVariant))
            return false;

        ReportVariant variant = (ReportVariant) other;

        return variant.description.equals(description)
                && variant.externalName.equals(externalName)
                && variant.priority == priority;
    }

    @Override
    public int hashCode() {
        long hashCode = 0;
        hashCode += description.hashCode() + externalName.hashCode() + priority;

        return (int) (hashCode % Integer.MAX_VALUE);
    }

    @Override
    public String toString() {
        return " priority=" + priority + " externalName=" + externalName
                + " description=" + description;
    }

}

