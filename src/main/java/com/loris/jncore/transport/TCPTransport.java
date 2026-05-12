package com.loris.jncore.transport;

import java.io.*;
import java.net.Socket;

public class TCPTransport implements ITransport {
    private final String host;
    private final int portNumber;

    private Socket socket;
    private BufferedReader reader;
    private OutputStream output;

    public TCPTransport(String host, int portNumber) {
        this.host = host;
        this.portNumber = portNumber;
    }

    @Override
    public boolean connect() throws Exception {
        socket = new Socket(host, portNumber);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = socket.getOutputStream();
        return true;
    }

    @Override
    public void disconnect() {
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception ignored) {}
        }
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
        return socket != null && socket.isConnected();
    }
}