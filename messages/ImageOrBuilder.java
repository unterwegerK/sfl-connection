// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Image.proto

package de.ku.sfl.connection.messages;

public interface ImageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:de.ku.sfl.connection.messages.Image)
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
   * <code>required bytes data = 2;</code>
   * @return Whether the data field is set.
   */
  boolean hasData();
  /**
   * <code>required bytes data = 2;</code>
   * @return The data.
   */
  com.google.protobuf.ByteString getData();
}
