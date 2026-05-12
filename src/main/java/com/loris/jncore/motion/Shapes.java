package com.loris.jncore.motion;

import java.awt.geom.Point2D;
import java.util.List;

public class Shapes {
    private final MotionController motion;

    public Shapes(MotionController motion) {
        this.motion = motion;
    }

    public void drawLine(double x1, double y1, double x2, double y2, double z, int feed) throws Exception {
        motion.rapidMove(x1, y1, z);
        motion.linearMove(x2, y2, z, feed);
    }

    public void drawRectangle(double x, double y, double width, double height, double z, int feed) throws Exception {
        motion.linearMove(x, y, z, feed);
        motion.linearMove(x + width, y, z, feed);
        motion.linearMove(x + width, y + height, z, feed);
        motion.linearMove(x, y + height, z, feed);
        motion.linearMove(x, y, z, feed);
    }

    public void drawPolyline(List<Point2D> points, double z, int feed) throws Exception {
        if(points.isEmpty()) return;

        Point2D first = points.getFirst();
        motion.rapidMove(first.getX(), first.getY(), z);

        for(Point2D p : points)
            motion.linearMove(p.getX(), p.getY(), z, feed);
    }

    public void drawCircle(double cx, double cy, double radius, double z, int feed) throws Exception {
        motion.rapidMove(cx + radius, cy, z);
        motion.arcCW(cx + radius, cy, -radius, 0, feed);
    }
}