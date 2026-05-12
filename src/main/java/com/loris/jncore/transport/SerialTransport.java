package com.loris.jncore.transport;

import com.fazecast.jSerialComm.SerialPort;

import java.io.*;

public class SerialTransport implements ITransport {
    private final String portName;
    private final int baudRate;

    private SerialPort port;

    private BufferedReader reader;
    private OutputStream output;

    public SerialTransport(String portName, int baudRate) {
        this.portName = portName;
        this.baudRate = baudRate;
    }

    @Override
    public boolean connect() {
        port = SerialPort.getCommPort(portName);

        port.setBaudRate(baudRate);
        port.setNumDataBits(8);
        port.setNumStopBits(1);
        port.setParity(0);

        if (!port.openPort())
            return false;

        reader = new BufferedReader(new InputStreamReader(port.getInputStream()));
        output = port.getOutputStream();

        return true;
    }

    @Override
    public void disconnect() {
        if (port != null)
            port.closePort();
    }

    @Override
    public void send(String data) throws Exception {
        output.write((data + "\n").getBytes());
        output.flush();
    }

    @Override
    public String readLine() throws Exception {
        return reader.readLine();
    }

    @Override
    public boolean isConnected() {
        return port != null && port.isOpen();
    }
}