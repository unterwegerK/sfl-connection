// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NamedIntegerValueChangedMessage.proto

package de.ku.sfl.connection.messages;

public interface NamedIntegerValueChangedMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:de.ku.sfl.connection.messages.NamedIntegerValueChangedMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string name = 1;</code>
   * @return Whether the name field is set.
   */
  boolean hasName();
  /**
   * <code>required string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>required string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>required int32 value = 2;</code>
   * @return Whether the value field is set.
   */
  boolean hasValue();
  /**
   * <code>required int32 value = 2;</code>
   * @return The value.
   */
  int getValue();
}