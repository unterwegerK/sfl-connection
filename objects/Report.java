package de.ku.sfl.connection.objects;
import java.util.List;
import java.util.Vector;

import de.ku.sfl.connection.messages.BarcodeType;
import de.ku.sfl.connection.messages.ScannerType;

/**
 * A report is a discovereable unit of information. A report has an internal name for the
 * game masters. The text that is shown to the players as the description can change during the
 * game. Hence, it is stored in a {@link ReportVariant}.
 *
 * Reports are structured in categories. These are hierarchial but lightweight. That means a
 * category of a report is a string where subcategories are delimited by ::. E.g. the report
 * "Dog" could have the category "lifeform::animal".
 *
 * To relate a report to the real-life position, a location can be specified.
 */
public class Report {

    private int id;
    private String internalName;
    private String key;
    private BarcodeType barcodeType;
    private ScannerType scannerType;
    private boolean visible;
    private String categorie;
    private String location;
    private int numberOfInstances;

    private List<ReportVariant> variants = new Vector<ReportVariant>();

    /**
     * Creates a report with the given values and one variant with priority 0.
     */
    public Report(
            String externalName,
            String internalName,
            String description,
            String key,
            BarcodeType barcodeType,
            ScannerType scannerType,
            String category,
            String location,
            int numberOfInstances) {
        this.internalName = internalName;
        this.barcodeType = barcodeType;
        this.scannerType = scannerType;
        this.id = id;
        this.key = key;
        visible = true;
        this.categorie = category;
        this.location = location;
        ReportVariant variante = new ReportVariant(externalName, description, 0,
                null);
        variante.setRelatedReport(this);
        variants.add(variante);
        this.numberOfInstances = numberOfInstances;
    }

    /**
     * Creates a report with the given values and variants.
     */
    public Report(
            String internalName,
            String key,
            int barcodeType,
            int scannerType,
            boolean visible,
            List<ReportVariant> variants,
            String category,
            String location,
            int numberOfInstances) {
        this(internalName, key, BarcodeType.values()[barcodeType],
                ScannerType.values()[scannerType], visible, variants, category, location, numberOfInstances);
    }

    /**
     * Creates a report with the given values and variants.
     */
    public Report(
            String internerName,
            String schluessel,
            BarcodeType barcodeType,
            ScannerType scannerType,
            boolean visible,
            List<ReportVariant> variants,
            String categories,
            String location,
            int numberOfInstances) {
        this.internalName = internerName;
        this.key = schluessel;
        this.barcodeType = barcodeType;
        this.scannerType = scannerType;
        this.visible = visible;
        this.categorie = categories;
        this.location = location;
        this.numberOfInstances = numberOfInstances;

        if (variants != null) {
            this.variants = variants;
            for (ReportVariant variante : variants) {
                variante.setRelatedReport(this);
            }
        }
    }

    public int getId() {
        return id;
    }

    /**
     * Returns a list of variants of this report.
     */
    public List<ReportVariant> getVariants() {
        return new Vector<ReportVariant>(variants);
    }

    public String getInternalName() {
        return internalName;
    }

    public String getKey() {
        return key;
    }

    public BarcodeType getBarcodeType() {
        return barcodeType;
    }

    public ScannerType getScannerType() {
        return scannerType;
    }

    public String getCategory() {
        return categorie;
    }

    public String getLocation() {
        return location;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    /**
     * Returns the variant with the given priority.
     */
    public ReportVariant getVariant(int priority) {
        for (ReportVariant variante : variants) {
            if (variante.getPriority() == priority) {
                return variante;
            }
        }

        return null;
    }

    /**
     * Indicates whether this report is shown in the scanner.
     */
    public boolean isVisible() {
        return visible;
    }

    public static int getIntegerBarcodeType(BarcodeType type) {
        return type.ordinal();
    }

    public static int getIntegerScannerType(ScannerType scannerType) {
        return scannerType.ordinal();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        Report otherReport = (Report) other;

        boolean areEqual = true;
        areEqual &= otherReport.id == id;
        areEqual &= otherReport.internalName.equals(internalName);
        areEqual &= otherReport.key.equals(key);
        areEqual &= otherReport.variants.size() == variants.size();

        if (!areEqual) return false;

        List<ReportVariant> otherVariants = otherReport.getVariants();
        for (ReportVariant variant : variants) {
            boolean found = false;

            for (ReportVariant otherVariant : otherVariants) {
                if (variant.getPriority() == otherVariant.getPriority()) {
                    if (variant.getDescription().equals(otherVariant.getDescription())
                            && variant.getExternalName().equals(
                            otherVariant.getExternalName())) {
                        found = true;
                        break;
                    } else {
                        return false;
                    }
                }
            }

            if (!found) {
                return false;
            }
        }

        return areEqual;
    }

    @Override
    public int hashCode() {
        long hashCode = 0;
        hashCode += id + internalName.hashCode() + key.hashCode();

        for (ReportVariant variant : variants) {
            hashCode += variant.hashCode();
        }

        return (int) (hashCode % Integer.MAX_VALUE);
    }

    @Override
    public String toString() {
        String s = "id=" + id + " internalName=" + internalName + " key="
                + key;

        for (ReportVariant variant : variants) {
            s += "[";
            s += variant.toString();
            s += "]";
        }

        return s;
    }
}

