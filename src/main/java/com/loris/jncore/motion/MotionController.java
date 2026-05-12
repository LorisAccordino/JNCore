package com.loris.jncore.motion;

import com.loris.jncore.protocol.ICNCProtocol;

public class MotionController {

    private final ICNCProtocol protocol;

    public MotionController(ICNCProtocol protocol) {
        this.protocol = protocol;
    }

    public void rapidMove(double x, double y, double z) throws Exception {
        protocol.sendCommand(String.format("G0 X%.3f Y%.3f Z%.3f", x, y, z));
    }

    public void rapidMoveXY(double x, double y) throws Exception {
        protocol.sendCommand(String.format("G0 X%.3f Y%.3f", x, y));
    }

    public void rapidMoveZ(double z) throws Exception {
        protocol.sendCommand(String.format("G0 Z%.3f", z));
    }

    public void linearMove(double x, double y, double z, int feed) throws Exception {
        protocol.sendCommand(String.format("G1 X%.3f Y%.3f Z%.3f F%d", x, y, z, feed));
    }

    public void arcCW(double x, double y, double i, double j, int feed) throws Exception {
        protocol.sendCommand(String.format("G2 X%.3f Y%.3f I%.3f J%.3f F%d", x, y, i, j, feed));
    }

    public void arcCCW(double x, double y, double i, double j, int feed) throws Exception {
        protocol.sendCommand(String.format("G3 X%.3f Y%.3f I%.3f J%.3f F%d", x, y, i, j, feed));
    }

    public void absoluteMode() throws Exception {
        protocol.sendCommand("G90");
    }

    public void relativeMode() throws Exception {
        protocol.sendCommand("G91");
    }

    public void dwell(double seconds) throws Exception {
        protocol.sendCommand(String.format("G4 P%.3f", seconds));
    }

    public void setFeed(int feed) throws Exception {
        protocol.sendCommand(String.format("F%d", feed));
    }

    public void spindleOn(int rpm) throws Exception {
        protocol.sendCommand(String.format("M3 S%d", rpm));
    }

    public void spindleOff() throws Exception {
        protocol.sendCommand("M5");
    }

    public void laserPower(int power) throws Exception {
        protocol.sendCommand(String.format("S%d", power));
    }

    public void coolantOn() throws Exception {
        protocol.sendCommand("M8");
    }

    public void coolantOff() throws Exception {
        protocol.sendCommand("M9");
    }

    public void mm() throws Exception {
        protocol.sendCommand("G21");
    }

    public void inches() throws Exception {
        protocol.sendCommand("G20");
    }
}