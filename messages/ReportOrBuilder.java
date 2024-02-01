// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Report.proto

package de.ku.sfl.connection.messages;

public interface ReportOrBuilder extends
    // @@protoc_insertion_point(interface_extends:de.ku.sfl.connection.messages.Report)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string key = 2;</code>
   * @return Whether the key field is set.
   */
  boolean hasKey();
  /**
   * <code>required string key = 2;</code>
   * @return The key.
   */
  java.lang.String getKey();
  /**
   * <code>required string key = 2;</code>
   * @return The bytes for key.
   */
  com.google.protobuf.ByteString
      getKeyBytes();

  /**
   * <code>required string internalName = 4;</code>
   * @return Whether the internalName field is set.
   */
  boolean hasInternalName();
  /**
   * <code>required string internalName = 4;</code>
   * @return The internalName.
   */
  java.lang.String getInternalName();
  /**
   * <code>required string internalName = 4;</code>
   * @return The bytes for internalName.
   */
  com.google.protobuf.ByteString
      getInternalNameBytes();

  /**
   * <code>required .de.ku.sfl.connection.messages.BarcodeType barcodeType = 6;</code>
   * @return Whether the barcodeType field is set.
   */
  boolean hasBarcodeType();
  /**
   * <code>required .de.ku.sfl.connection.messages.BarcodeType barcodeType = 6;</code>
   * @return The barcodeType.
   */
  de.ku.sfl.connection.messages.BarcodeType getBarcodeType();

  /**
   * <code>required .de.ku.sfl.connection.messages.ScannerType scannerType = 7;</code>
   * @return Whether the scannerType field is set.
   */
  boolean hasScannerType();
  /**
   * <code>required .de.ku.sfl.connection.messages.ScannerType scannerType = 7;</code>
   * @return The scannerType.
   */
  de.ku.sfl.connection.messages.ScannerType getScannerType();

  /**
   * <code>required string category = 8;</code>
   * @return Whether the category field is set.
   */
  boolean hasCategory();
  /**
   * <code>required string category = 8;</code>
   * @return The category.
   */
  java.lang.String getCategory();
  /**
   * <code>required string category = 8;</code>
   * @return The bytes for category.
   */
  com.google.protobuf.ByteString
      getCategoryBytes();

  /**
   * <code>required string location = 9;</code>
   * @return Whether the location field is set.
   */
  boolean hasLocation();
  /**
   * <code>required string location = 9;</code>
   * @return The location.
   */
  java.lang.String getLocation();
  /**
   * <code>required string location = 9;</code>
   * @return The bytes for location.
   */
  com.google.protobuf.ByteString
      getLocationBytes();

  /**
   * <code>required bool visible = 10;</code>
   * @return Whether the visible field is set.
   */
  boolean hasVisible();
  /**
   * <code>required bool visible = 10;</code>
   * @return The visible.
   */
  boolean getVisible();

  /**
   * <code>repeated .de.ku.sfl.connection.messages.ReportVariant variants = 11;</code>
   */
  java.util.List<de.ku.sfl.connection.messages.ReportVariant> 
      getVariantsList();
  /**
   * <code>repeated .de.ku.sfl.connection.messages.ReportVariant variants = 11;</code>
   */
  de.ku.sfl.connection.messages.ReportVariant getVariants(int index);
  /**
   * <code>repeated .de.ku.sfl.connection.messages.ReportVariant variants = 11;</code>
   */
  int getVariantsCount();
  /**
   * <code>repeated .de.ku.sfl.connection.messages.ReportVariant variants = 11;</code>
   */
  java.util.List<? extends de.ku.sfl.connection.messages.ReportVariantOrBuilder> 
      getVariantsOrBuilderList();
  /**
   * <code>repeated .de.ku.sfl.connection.messages.ReportVariant variants = 11;</code>
   */
  de.ku.sfl.connection.messages.ReportVariantOrBuilder getVariantsOrBuilder(
      int index);
}