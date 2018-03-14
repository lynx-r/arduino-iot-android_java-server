cd aws-iot-device-sdk-java
mvn clean exec:java -pl aws-iot-device-sdk-java-samples -Dexec.mainClass="com.amazonaws.services.iot.client.sample.pubSub.PublishSubscribeSample" -Dexec.args="-clientEndpoint a1lv5xlogg9711.iot.eu-west-1.amazonaws.com -clientId sdk-java -certificateFile ../mac-proxy-arduino.cert.pem -privateKeyFile ../mac-proxy-arduino.private.key"

// отправить цифру на устройство
aws --endpoint-url https://a1lv5xlogg9711.iot.eu-west-1.amazonaws.com --profile iot iot-data publish --topic "sdk/test/java" --payload "0" --qos 1