package com.loris.jncore.protocol;

import com.loris.jncore.transport.ITransport;

public abstract class BaseProtocol implements ICNCProtocol {
    protected final ITransport transport;

    public BaseProtocol(ITransport transport) {
        this.transport = transport;
    }

    @Override
    public void sendCommand(String command) throws Exception {
        transport.send(command);
        System.out.println(">> " + command);
        waitForAck();
    }

    @Override
    public void waitForAck() throws Exception {
        while (true) {
            String line = transport.readLine();
            if (line == null) continue;
            System.out.println("<< " + line);
            if (line.contains("ok")) return;
            if (line.contains("error")) throw new RuntimeException(line);
        }
    }
}