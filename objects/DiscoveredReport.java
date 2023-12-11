package de.ku.sfl.connection.objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import de.ku.sfl.connection.ILog;

/**
 * A DiscoveredReport is a report with a discovery time-stamp.
 */
public class DiscoveredReport extends Report {

    private final static String TAG = DiscoveredReport.class.getCanonicalName();

    private final static String ID = "id";
    private final static String INTERNAL_NAME = "internalName";
    private final static String KEY = "key";
    private final static String BARCODE_TYPE = "barcodeType";
    private final static String SCANNER_TYPE = "scannerType";
    private final static String VISIBLE = "visible";
    private final static String NUMBER_OF_VARIANTS = "numberOfVariants";
    private final static String TIMESTAMP = "timestamp";
    private final static String DISCOVERED = "discovered";
    private final static String READ = "read";
    private final static String CATEGORY = "category";
    private final static String LOCATION = "location";
    private final static String NUMBER_OF_INSTANCES = "numberOfInstances";

    private final static String VARIANT = "variant";

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss,S_Z", Locale.US);

    private Calendar discoveryTimestamp;
    private boolean discovered;
    private boolean read;

    public DiscoveredReport(
            int id,
            String internalName,
            String key,
            BarcodeType barcodeType,
            ScannerType scannerType,
            boolean visible,
            List<ReportVariant> variants,
            Calendar timestamp,
            boolean discovered,
            boolean read,
            String category,
            String location,
            int numberOfInstances) {
        super(id, internalName, key, barcodeType, scannerType, visible, variants, category,
                location, numberOfInstances);
        this.discoveryTimestamp = timestamp;
        this.discovered = discovered;
        this.read = read;
    }

    public DiscoveredReport(
            int id,
            String externalName,
            String internalName,
            String key,
            String description,
            Calendar timestamp,
            boolean discovered,
            boolean read,
            String category,
            String location,
            int numberOfInstances) {
        super(id, externalName, internalName, description, key,
                BarcodeType.DATA_MATRIX, ScannerType.SCIENTIFIC, category, location, numberOfInstances);
        this.discoveryTimestamp = timestamp;
        this.discovered = discovered;
        this.read = read;
    }

    public DiscoveredReport(JSONObject reportObject, ILog log) throws JSONException {
        super(
                reportObject.getInt(ID),
                reportObject.getString(INTERNAL_NAME),
                reportObject.getString(KEY),
                (BarcodeType) (BarcodeType.values()[reportObject.getInt(BARCODE_TYPE)]),
                (ScannerType) (ScannerType.values()[reportObject.getInt(SCANNER_TYPE)]),
                reportObject.getBoolean(VISIBLE),
                parseVariants(reportObject),
                reportObject.getString(CATEGORY),
                reportObject.getString(LOCATION),
                reportObject.getInt(NUMBER_OF_INSTANCES)
                );
        String timestampString = reportObject.getString(TIMESTAMP);
        try {
            discoveryTimestamp = GregorianCalendar.getInstance();
            discoveryTimestamp.setTime(dateFormat.parse(timestampString));
        } catch (ParseException e) {
            log.error(TAG, "Error while parsing timestamp " + timestampString + ".", e);
        }
    }

    private static List<ReportVariant> parseVariants(JSONObject reportObject) throws JSONException {
        int numberOfVariants = reportObject.getInt(NUMBER_OF_VARIANTS);
        List<ReportVariant> variants = new Vector<>();
        for (int v = 0; v < numberOfVariants; v++) {
            JSONObject variantObject = reportObject.getJSONObject(VARIANT + v);
            variants.add(new ReportVariant(variantObject));
        }
        return variants;
    }

    public JSONObject serialize() throws JSONException {
        JSONObject reportObject = new JSONObject();
        reportObject.put(ID, getId());
        reportObject.put(INTERNAL_NAME, getInternalName());
        reportObject.put(KEY, getKey());
        reportObject.put(BARCODE_TYPE, getBarcodeType().ordinal());
        reportObject.put(SCANNER_TYPE, getScannerType().ordinal());
        reportObject.put(VISIBLE, isVisible());
        reportObject.put(TIMESTAMP, dateFormat.format(getDiscoveryTimestamp().getTime()));
        // TODO Debug
//        reportObject.put(DISCOVERED, report.wasDiscovered());
        reportObject.put(DISCOVERED, false);
        reportObject.put(READ, false);
        reportObject.put(CATEGORY, getCategory());
        reportObject.put(LOCATION, getLocation());
        reportObject.put(NUMBER_OF_INSTANCES, getNumberOfInstances());
        reportObject.put(NUMBER_OF_VARIANTS, getVariants().size());

        int variantCounter = 0;
        for (ReportVariant variant : getVariants()) {
            reportObject.put(VARIANT + variantCounter++, variant.serialize());
        }

        return reportObject;
    }

    /**
     * Returns the current variant for this report. That is the variant with the highest priority
     * for which all dependencies are discovered.
     */
    public ReportVariant getCurrentVariant() {
        List<ReportVariant> variants = getVariants();

        return variants.get(variants.size() - 1);
    }

    public Calendar getDiscoveryTimestamp() {
        return discoveryTimestamp;
    }

    public boolean wasDiscovered() {
        return discovered;
    }

    public boolean wasRead() {
        return read;
    }
}

