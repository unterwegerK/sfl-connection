// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NamedBooleanValueChangedMessage.proto

package de.ku.sfl.connection.messages;

public final class NamedBooleanValueChangedMessageOuterClass {
  private NamedBooleanValueChangedMessageOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_de_ku_sfl_connection_messages_NamedBooleanValueChangedMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_de_ku_sfl_connection_messages_NamedBooleanValueChangedMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n%NamedBooleanValueChangedMessage.proto\022" +
      "\035de.ku.sfl.connection.messages\">\n\037NamedB" +
      "ooleanValueChangedMessage\022\014\n\004name\030\001 \002(\t\022" +
      "\r\n\005value\030\002 \002(\010B\002P\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_de_ku_sfl_connection_messages_NamedBooleanValueChangedMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_de_ku_sfl_connection_messages_NamedBooleanValueChangedMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_de_ku_sfl_connection_messages_NamedBooleanValueChangedMessage_descriptor,
        new java.lang.String[] { "Name", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}