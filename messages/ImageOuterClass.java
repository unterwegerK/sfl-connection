// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Image.proto

package de.ku.sfl.connection.messages;

public final class ImageOuterClass {
  private ImageOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_de_ku_sfl_connection_messages_Image_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_de_ku_sfl_connection_messages_Image_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013Image.proto\022\035de.ku.sfl.connection.mess" +
      "ages\"#\n\005Image\022\014\n\004name\030\001 \002(\t\022\014\n\004data\030\002 \002(" +
      "\014B\002P\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_de_ku_sfl_connection_messages_Image_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_de_ku_sfl_connection_messages_Image_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_de_ku_sfl_connection_messages_Image_descriptor,
        new java.lang.String[] { "Name", "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
