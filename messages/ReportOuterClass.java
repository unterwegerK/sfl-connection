// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Report.proto

package de.ku.sfl.connection.messages;

public final class ReportOuterClass {
  private ReportOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_de_ku_sfl_connection_messages_Report_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_de_ku_sfl_connection_messages_Report_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Report.proto\022\035de.ku.sfl.connection.mes" +
      "sages\032\021BarcodeType.proto\032\021ScannerType.pr" +
      "oto\032\023ReportVariant.proto\"\242\002\n\006Report\022\013\n\003k" +
      "ey\030\002 \002(\t\022\024\n\014internalName\030\004 \002(\t\022?\n\013barcod" +
      "eType\030\006 \002(\0162*.de.ku.sfl.connection.messa" +
      "ges.BarcodeType\022?\n\013scannerType\030\007 \002(\0162*.d" +
      "e.ku.sfl.connection.messages.ScannerType" +
      "\022\020\n\010category\030\010 \002(\t\022\020\n\010location\030\t \002(\t\022\017\n\007" +
      "visible\030\n \002(\010\022>\n\010variants\030\013 \003(\0132,.de.ku." +
      "sfl.connection.messages.ReportVariantB\002P" +
      "\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          de.ku.sfl.connection.messages.BarcodeTypeOuterClass.getDescriptor(),
          de.ku.sfl.connection.messages.ScannerTypeOuterClass.getDescriptor(),
          de.ku.sfl.connection.messages.ReportVariantOuterClass.getDescriptor(),
        });
    internal_static_de_ku_sfl_connection_messages_Report_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_de_ku_sfl_connection_messages_Report_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_de_ku_sfl_connection_messages_Report_descriptor,
        new java.lang.String[] { "Key", "InternalName", "BarcodeType", "ScannerType", "Category", "Location", "Visible", "Variants", });
    de.ku.sfl.connection.messages.BarcodeTypeOuterClass.getDescriptor();
    de.ku.sfl.connection.messages.ScannerTypeOuterClass.getDescriptor();
    de.ku.sfl.connection.messages.ReportVariantOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}