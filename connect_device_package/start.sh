# stop script on error
set -e

# Check to see if root CA file exists, download if not
if [ ! -f ./root-CA.crt ]; then
  printf "\nDownloading AWS IoT Root CA certificate from Symantec...\n"
  curl https://www.symantec.com/content/en/us/enterprise/verisign/roots/VeriSign-Class%203-Public-Primary-Certification-Authority-G5.pem > root-CA.crt
fi

# install AWS Device SDK for Java if not already installed
if [ ! -d ./aws-iot-device-sdk-java ]; then
  printf "\nInstalling AWS SDK...\n"
  git clone https://github.com/aws/aws-iot-device-sdk-java.git
  cd aws-iot-device-sdk-java
  mvn clean install -Dgpg.skip=true
  cd ..
fi

# run pub/sub sample app using certificates downloaded in package
printf "\nRunning pub/sub sample application...\n"
cd aws-iot-device-sdk-java
mvn exec:java -pl aws-iot-device-sdk-java-samples -Dexec.mainClass="com.amazonaws.services.iot.client.sample.pubSub.PublishSubscribeSample" -Dexec.args="-clientEndpoint a1lv5xlogg9711.iot.eu-west-1.amazonaws.com -clientId sdk-java -certificateFile ../mac-proxy-arduino.cert.pem -privateKeyFile ../mac-proxy-arduino.private.key"
