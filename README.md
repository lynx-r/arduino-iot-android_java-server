# IoT service for Android project

Java server which connects to Arduino odd job and to AWS IoT, accepts messages from IoT and send them to Arduino.

# How does it work

https://vimeo.com/259539079

# How did I construct odd job 

https://circuitdigest.com/microcontroller-projects/7-segment-display-interfacing-with-arduino

# Code

The major work with device is in TestTopicListener.java

The major work with AWS IoT in PublishSubscribeSample.java

# Arduino

Arduino sketch in arduino-sketch.c
Library for connecting to Arduino in Java code 
https://github.com/NeuronRobotics/nrjavaserial

# Links:

* https://www.arduino.cc/
* https://aws.amazon.com/iot
* https://github.com/aws/aws-iot-device-sdk-java
