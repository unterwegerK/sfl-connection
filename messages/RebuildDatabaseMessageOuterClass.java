// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RebuildDatabaseMessage.proto

package de.ku.sfl.connection.messages;

public final class RebuildDatabaseMessageOuterClass {
  private RebuildDatabaseMessageOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_de_ku_sfl_connection_messages_RebuildDatabaseMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_de_ku_sfl_connection_messages_RebuildDatabaseMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\034RebuildDatabaseMessage.proto\022\035de.ku.sf" +
      "l.connection.messages\032\013Image.proto\032\014Repo" +
      "rt.proto\032\027NamedBooleanValue.proto\032\027Named" +
      "IntegerValue.proto\"\242\002\n\026RebuildDatabaseMe" +
      "ssage\0226\n\007reports\030\001 \003(\0132%.de.ku.sfl.conne" +
      "ction.messages.Report\022L\n\022namedBooleanVal" +
      "ues\030\002 \003(\01320.de.ku.sfl.connection.message" +
      "s.NamedBooleanValue\022L\n\022namedIntegerValue" +
      "s\030\003 \003(\01320.de.ku.sfl.connection.messages." +
      "NamedIntegerValue\0224\n\006images\030\004 \003(\0132$.de.k" +
      "u.sfl.connection.messages.ImageB\002P\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          de.ku.sfl.connection.messages.ImageOuterClass.getDescriptor(),
          de.ku.sfl.connection.messages.ReportOuterClass.getDescriptor(),
          de.ku.sfl.connection.messages.NamedBooleanValueOuterClass.getDescriptor(),
          de.ku.sfl.connection.messages.NamedIntegerValueOuterClass.getDescriptor(),
        });
    internal_static_de_ku_sfl_connection_messages_RebuildDatabaseMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_de_ku_sfl_connection_messages_RebuildDatabaseMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_de_ku_sfl_connection_messages_RebuildDatabaseMessage_descriptor,
        new java.lang.String[] { "Reports", "NamedBooleanValues", "NamedIntegerValues", "Images", });
    de.ku.sfl.connection.messages.ImageOuterClass.getDescriptor();
    de.ku.sfl.connection.messages.ReportOuterClass.getDescriptor();
    de.ku.sfl.connection.messages.NamedBooleanValueOuterClass.getDescriptor();
    de.ku.sfl.connection.messages.NamedIntegerValueOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
