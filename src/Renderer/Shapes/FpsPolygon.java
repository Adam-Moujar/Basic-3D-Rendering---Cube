package Renderer.Shapes;

import Renderer.Point.FpsPoint;
import Renderer.Point.PointConverter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FpsPolygon {

    private FpsPoint[] points;
    private Color color;

    public FpsPolygon(Color color, FpsPoint... points)
    {
        this.color = color;
        this.points = new FpsPoint[points.length];
        for(int i = 0; i < points.length; i++)
        {
            FpsPoint p = points[i];
            this.points[i] = new FpsPoint(p.x,p.y,p.z);
        }
    }

    public FpsPolygon(FpsPoint... points)
    {
        this.color = Color.WHITE;
        this.points = new FpsPoint[points.length];
        for(int i = 0; i < points.length; i++)
        {
            FpsPoint p = points[i];
            this.points[i] = new FpsPoint(p.x,p.y,p.z);
        }
    }

    public void render(Graphics g)
    {
        Polygon poly = new Polygon();
        for (int i = 0; i < points.length; i++)
        {
            Point p = PointConverter.convertPoint(points[i]);
            poly.addPoint(p.x,p.y);
        }
        g.setColor(color);
        g.fillPolygon(poly);
    }
    public void rotate(boolean clockwise, double xdegrees, double ydegrees, double zdegrees)
    {
        for (FpsPoint p : points)
        {
            PointConverter.rotateAxisX(p, clockwise, xdegrees);
            PointConverter.rotateAxisY(p, clockwise, ydegrees);
            PointConverter.rotateAxisZ(p, clockwise, zdegrees);

        }
    }
    public void setColor(Color color)
    {
        this.color = color;
    }

    public static FpsPolygon[] sortPolys(FpsPolygon[] polys)
    {
        ArrayList<FpsPolygon> polyList = new ArrayList<FpsPolygon>();

        for(FpsPolygon poly : polys)
        {
            polyList.add(poly);
        }

        Collections.sort(polyList, new Comparator<FpsPolygon>() {
            @Override
            public int compare(FpsPolygon poly1, FpsPolygon poly2) {
                return poly2.getAverageX() - poly1.getAverageX() < 0 ? 1:-1;
            }
        });

        for(int i = 0; i < polys.length; i++)
        {
            polys[i] = polyList.get(i);
        }

        return polys;
    }

    public double getAverageX()
    {
        double sum = 0;

        for(FpsPoint p : points)
        {
            sum += p.x;
        }

        return sum/points.length;
    }
}
