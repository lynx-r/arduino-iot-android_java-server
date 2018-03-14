/*
 * Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.services.iot.client.sample.pubSub;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;
import gnu.io.NRSerialPort;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * This class extends {@link AWSIotTopic} to receive messages from a subscribed
 * topic.
 */
public class TestTopicListener extends AWSIotTopic {

    private String port;
    private int baudRate;
    private DataOutputStream outs;

    public TestTopicListener(String topic, AWSIotQos qos) {
        super(topic, qos);

        // TODO убрать из конструктора
        init();
    }

    private void init() {
        for (String s : NRSerialPort.getAvailableSerialPorts()) {
            System.out.println("Availible port: " + s);
        }

        port = "/dev/tty.usbmodemFA131";
        baudRate = 9600;

        NRSerialPort serial = new NRSerialPort(port, baudRate);
        serial.connect();

        System.out.println("Соединение установлено: " + serial.isConnected());
        outs = new DataOutputStream(serial.getOutputStream());
    }

    @Override
    public void onMessage(AWSIotMessage message) {
        String s = message.getStringPayload();
        try {
            char b = s.charAt(0);
            outs.write(b);
            System.out.println("Сообщение отправлено на устройство: " + b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
