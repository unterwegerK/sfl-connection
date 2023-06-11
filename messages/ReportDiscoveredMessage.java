package de.ku.sfl.connection.messages;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import de.ku.sfl.connection.objects.BarcodeType;
import de.ku.sfl.connection.objects.DiscoveredReport;
import de.ku.sfl.connection.objects.Report;
import de.ku.sfl.connection.objects.ReportVariant;
import de.ku.sfl.connection.objects.ScannerType;

public class ReportDiscoveredMessage extends Message {
    private static final String REPORT = "report";

    public DiscoveredReport report;

    public ReportDiscoveredMessage(DiscoveredReport discoveredReport) {
        super(MessageType.ReportDiscovered);

        report = discoveredReport;
    }

    public ReportDiscoveredMessage(JSONObject messageObject) throws JSONException {
        super(messageObject);

        JSONObject reportObject = messageObject.getJSONObject(REPORT);
        report = new DiscoveredReport(reportObject);
    }

    public Report getDiscoveredReport() {
        return report;
    }

    @Override
    public JSONObject serialize() throws JSONException {
        JSONObject messageObject = super.serialize();
        messageObject.put(REPORT, report.serialize());
        return messageObject;
    }
}
