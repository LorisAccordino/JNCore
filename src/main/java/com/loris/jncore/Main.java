package com.loris.jncore;

import com.loris.jncore.motion.MotionController;
import com.loris.jncore.motion.Shapes;
import com.loris.jncore.protocol.GRBLProtocol;
import com.loris.jncore.protocol.ICNCProtocol;
import com.loris.jncore.transport.ITransport;
import com.loris.jncore.transport.SerialTransport;

public class Main {

    public static void main(String[] args) throws Exception {
        ITransport transport = new SerialTransport("COM4", 115200);
        transport.connect();

        ICNCProtocol protocol = new GRBLProtocol(transport);
        MotionController motion = new MotionController(protocol);
        Shapes shapes = new Shapes(motion);

        protocol.unlock();

        motion.rapidMove(0,0,10);
        shapes.drawRectangle(0,0,50,30,-1,500);
    }
}