package com.loris.jncore.protocol;

import com.loris.jncore.machine.MachineState;
import com.loris.jncore.machine.MachineStatus;
import com.loris.jncore.transport.ITransport;

public class MarlinProtocol extends BaseProtocol {

    public MarlinProtocol(ITransport transport) {
        super(transport);
    }

    @Override
    public void initialize() throws Exception {
        Thread.sleep(2000);
        System.out.println("Marlin initialized");
    }

    @Override
    public void home() throws Exception {
        sendCommand("G28");
    }

    @Override
    public void unlock() {
        // Not needed
    }

    @Override
    public void reset() throws Exception {
        transport.send("M999");
    }

    @Override
    public MachineStatus queryStatus() throws Exception {
        transport.send("M114");
        String line = transport.readLine();
        return parse(line);
    }

    private MachineStatus parse(String line) {
        MachineStatus s = new MachineStatus();
        s.state = MachineState.IDLE;

        try {
            String[] parts = line.split(" ");

            for (String p : parts) {
                if (p.startsWith("X:")) s.x = Double.parseDouble(p.substring(2));
                if (p.startsWith("Y:")) s.y = Double.parseDouble(p.substring(2));
                if (p.startsWith("Z:")) s.z = Double.parseDouble(p.substring(2));
            }
        } catch (Exception ignored) {}
        return s;
    }
}