package com.loris.jncore.protocol;

import com.loris.jncore.machine.MachineStatus;

public interface ICNCProtocol {
    void initialize() throws Exception;
    void sendCommand(String command) throws Exception;
    void waitForAck() throws Exception;
    void home() throws Exception;
    void unlock() throws Exception;
    void reset() throws Exception;
    MachineStatus queryStatus() throws Exception;
}