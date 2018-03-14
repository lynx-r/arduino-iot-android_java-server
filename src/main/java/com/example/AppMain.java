package com.example;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import gnu.io.NRSerialPort;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;


class MyMessage extends AWSIotMessage {
    public MyMessage(String topic, AWSIotQos qos, String payload) {
        super(topic, qos, payload);
    }

    @Override
    public void onSuccess() {
        // called when message publishing succeeded
    }

    @Override
    public void onFailure() {
        // called when message publishing failed
    }

    @Override
    public void onTimeout() {
        // called when message publishing timed out
    }
}
public class AppMain {

    public static void main(String[] args) throws InterruptedException, IOException, AWSIotException {
        sendToAws();
    }

    private static void sendToAws() throws AWSIotException {
        AWSIotMqttClient client = new AWSIotMqttClient("a1lv5xlogg9711.iot.eu-west-1.amazonaws.com", "123", "AKIAIKGUGEIUPJDR7QWQ", "hC/CJkCDj6fMb7IOFmfzXEmgAAnKRDReWA2l+n5t");
        client.connect();

        client.publish("sdk/test/java", AWSIotQos.QOS0, "2");
        System.out.println("1243");
    }

    private static void sendToDevice() throws IOException {
        for (String s : NRSerialPort.getAvailableSerialPorts()) {
            System.out.println("Availible port: " + s);
        }

        String port = "/dev/tty.usbmodemFA131";
        int baudRate = 9600;

        NRSerialPort serial = new NRSerialPort(port, baudRate);
        serial.connect();

        System.out.println("Соединение установлено: " + serial.isConnected());
        DataOutputStream outs = new DataOutputStream(serial.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Введите цифру от 0 до 9: ");
            String s = scanner.nextLine();
            char b = s.charAt(0);
            if (b >= 48 && b < 57) {
                outs.write(b);
            } else if (s.equals("!")) {
                break;
            }
        } while (true);

        serial.disconnect();
    }
}