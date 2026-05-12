package com.loris.jncore.transport;

import java.util.LinkedList;
import java.util.Queue;

public class MockTransport implements ITransport {
    private boolean connected = false;
    private final Queue<String> responses = new LinkedList<>();

    @Override
    public boolean connect() {
        connected = true;
        System.out.println("[MOCK] Connected");
        responses.add("Grbl 1.1h ['$' for help]");
        return true;
    }

    @Override
    public void disconnect() {
        connected = false;
        System.out.println("[MOCK] Disconnected");
    }

    @Override
    public void send(String data) {
        System.out.println(">> " + data);
        simulateResponse(data);
    }

    @Override
    public String readLine() {
        while (responses.isEmpty())
            sleep(10);
        return responses.poll();
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    private void simulateResponse(String cmd) {
        cmd = cmd.trim();

        if (cmd.equals("?")) {
            responses.add("<Idle|MPos:10.000,20.000,0.000>");
            return;
        }

        if (cmd.contains("BAD")) {
            responses.add("error:2");
            return;
        }

        responses.add("ok");
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {
        }
    }
}