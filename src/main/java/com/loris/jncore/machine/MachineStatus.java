package com.loris.jncore.machine;

public class MachineStatus {
    public MachineState state = MachineState.IDLE;

    public double x = 0;
    public double y = 0;
    public double z = 0;

    public double feedRate = 1000;
    public boolean spindleOn = false;
    public boolean alarm = false;

    @Override
    public String toString() {
        return "State=" + state + " X=" + x + " Y=" + y + " Z=" + z;
    }
}