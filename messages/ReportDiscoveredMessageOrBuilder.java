// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ReportDiscoveredMessage.proto

package de.ku.sfl.connection.messages;

public interface ReportDiscoveredMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:de.ku.sfl.connection.messages.ReportDiscoveredMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string key = 1;</code>
   * @return Whether the key field is set.
   */
  boolean hasKey();
  /**
   * <code>required string key = 1;</code>
   * @return The key.
   */
  java.lang.String getKey();
  /**
   * <code>required string key = 1;</code>
   * @return The bytes for key.
   */
  com.google.protobuf.ByteString
      getKeyBytes();

  /**
   * <code>required bool discovered = 2;</code>
   * @return Whether the discovered field is set.
   */
  boolean hasDiscovered();
  /**
   * <code>required bool discovered = 2;</code>
   * @return The discovered.
   */
  boolean getDiscovered();
}
