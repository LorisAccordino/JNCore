package com.loris.jncore.protocol;

import com.loris.jncore.machine.MachineState;
import com.loris.jncore.machine.MachineStatus;
import com.loris.jncore.transport.ITransport;

public class GRBLProtocol extends BaseProtocol {

    public GRBLProtocol(ITransport transport) {
        super(transport);
    }

    @Override
    public void initialize() throws Exception {
        Thread.sleep(2000);
        System.out.println("GRBL initialized");
    }

    @Override
    public void home() throws Exception {
        sendCommand("$H");
    }

    @Override
    public void unlock() throws Exception {
        sendCommand("$X");
    }

    @Override
    public void reset() throws Exception {
        transport.send("\u0018");
    }

    @Override
    public MachineStatus queryStatus() throws Exception {
        transport.send("?");
        String line = transport.readLine();
        return parseStatus(line);
    }

    private MachineStatus parseStatus(String line) {
        MachineStatus status = new MachineStatus();

        try {
            if (line.startsWith("<Idle")) status.state = MachineState.IDLE;
            else if (line.startsWith("<Run")) status.state = MachineState.RUN;
            else status.state = MachineState.UNKNOWN;

            int idx = line.indexOf("MPos:");

            if (idx != -1) {
                String pos = line.substring(idx + 5);
                pos = pos.split("\\|")[0];
                String[] xyz = pos.split(",");
                status.x = Double.parseDouble(xyz[0]);
                status.y = Double.parseDouble(xyz[1]);
                status.z = Double.parseDouble(xyz[2]);
            }
        } catch (Exception e) {
            System.out.println("Status parse error: " + line);
        }

        return status;
    }
}