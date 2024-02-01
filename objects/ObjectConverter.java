package de.ku.sfl.connection.objects;

import java.util.List;
import java.util.Vector;

public class ObjectConverter {

    public static List<Report> convertReports(List<de.ku.sfl.connection.messages.Report> reportsList) {
        List<de.ku.sfl.connection.objects.Report> convertedReports = new Vector<>();
        for(de.ku.sfl.connection.messages.Report report : reportsList) {
            convertedReports.add(new de.ku.sfl.connection.objects.Report(
                    report.getInternalName(),
                    report.getKey(),
                    report.getBarcodeType(),
                    report.getScannerType(),
                    report.getVisible(),
                    convertReportVariants(report.getVariantsList()),
                    report.getCategory(),
                    report.getLocation(),
                    0
            ));
        }
        return convertedReports;
    }

    public static List<de.ku.sfl.connection.objects.ReportVariant> convertReportVariants(List<de.ku.sfl.connection.messages.ReportVariant> variantsList) {
        List<de.ku.sfl.connection.objects.ReportVariant> convertedReportVariants = new Vector<>();
        for(de.ku.sfl.connection.messages.ReportVariant reportVariant : variantsList)
        {
            convertedReportVariants.add(new de.ku.sfl.connection.objects.ReportVariant(reportVariant.getExternalName(), reportVariant.getDescription(), reportVariant.getPriority(), reportVariant.getDependenciesList()));
        }
        return convertedReportVariants;
    }

    public static List<de.ku.sfl.connection.objects.NamedBooleanValue> convertNamedBooleanValues(List<de.ku.sfl.connection.messages.NamedBooleanValue> namedBooleanValuesList) {
        List<de.ku.sfl.connection.objects.NamedBooleanValue> convertedNamedBooleanValues = new Vector<>();
        for(de.ku.sfl.connection.messages.NamedBooleanValue namedBooleanValue : namedBooleanValuesList)
        {
            convertedNamedBooleanValues.add(new de.ku.sfl.connection.objects.NamedBooleanValue(namedBooleanValue.getName(), namedBooleanValue.getValue()));
        }
        return convertedNamedBooleanValues;
    }

    public static List<de.ku.sfl.connection.objects.NamedIntegerValue> convertNamedIntegerValues(List<de.ku.sfl.connection.messages.NamedIntegerValue> namedIntegerValuesList) {
        List<de.ku.sfl.connection.objects.NamedIntegerValue> convertedNamedIntegerValues = new Vector<>();
        for(de.ku.sfl.connection.messages.NamedIntegerValue namedIntegerValue : namedIntegerValuesList)
        {
            convertedNamedIntegerValues.add(new de.ku.sfl.connection.objects.NamedIntegerValue(namedIntegerValue.getName(), namedIntegerValue.getValue()));
        }
        return convertedNamedIntegerValues;
    }

    public static List<de.ku.sfl.connection.objects.Image> convertImages(List<de.ku.sfl.connection.messages.Image> images){
        List<de.ku.sfl.connection.objects.Image> convertedImages = new Vector<>();
        for(de.ku.sfl.connection.messages.Image image : images)
        {
            convertedImages.add(new de.ku.sfl.connection.objects.Image(image.getName(), image.getData().toByteArray()));
        }
        return convertedImages;
    }
}
