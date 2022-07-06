package Renderer.Point;

import java.awt.*;

import Renderer.Shapes.FpsPolygon;
import Window.Window;

public class PointConverter {

    private static double scale = 1;
    public static Point convertPoint(FpsPoint point3D)
    {
        double x3d = point3D.y * scale;
        double y3d = point3D.z * scale;
        double depth = point3D.x;
        double[] newVals = scale(x3d,y3d,depth);

        int x2d = (int) (Window.WIDTH / 2 + newVals[0]);
        int y2d = (int) (Window.HEIGHT / 2 - newVals[1]);

        return new Point(x2d,y2d);
    }

    private static double[] scale(double x, double y, double depth)
    {
        double distance = Math.sqrt(x*x + y*y);
        double theta = Math.atan2(y,x); // the angle of the vector using the x-y cartesian plane
        double cameraDepth = 15 - depth; // The 15, is for how many units far is the camera
        double localScale = Math.abs(1400 / (cameraDepth + 1400));
        distance = distance * localScale;
        double[] newVal = new double[2];
        newVal[0] = distance * Math.cos(theta);
        newVal[1] = distance * Math.sin(theta);

        return newVal;
    }

    public static void rotateAxisX(FpsPoint p, boolean clockwise, double degrees)
    {
        double radius = Math.sqrt(p.y * p.y + p.z * p.z );
        double theta = Math.atan2(p.z, p.y);
        theta += 2*Math.PI/360 * degrees * (clockwise?-1:1);
        p.y = radius * Math.cos(theta);
        p.z = radius * Math.sin(theta);
    }

    public static void rotateAxisY(FpsPoint p, boolean clockwise, double degrees) {
        double radius = Math.sqrt(p.x * p.x + p.z * p.z);
        double theta = Math.atan2(p.x, p.z);
        theta += 2 * Math.PI / 360 * degrees * (clockwise?-1:1);
        p.z = radius * Math.cos(theta);
        p.x = radius * Math.sin(theta);
    }

    public static void rotateAxisZ(FpsPoint p, boolean clockwise, double degrees) {
        double radius = Math.sqrt(p.x * p.x + p.y * p.y);
        double theta = Math.atan2(p.y, p.x);
        theta += 2 * Math.PI / 360 * degrees * (clockwise?-1:1);
        p.x = radius * Math.cos(theta);
        p.y = radius * Math.sin(theta);
    }
}
