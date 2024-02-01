rem requires protobuf 3.17.3

protoc -I%~dp0\messageDefinitions\ --java_out=%~dp0\..\..\..\.. %~dp0\messageDefinitions\*.proto