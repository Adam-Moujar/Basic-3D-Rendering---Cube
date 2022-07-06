package Renderer.Shapes;

import java.awt.*;

public class Tetrahedron {

    private FpsPolygon[] polygons;
    private Color color;

    public Tetrahedron(Color color, FpsPolygon... polygons)
    {
        this.color = color;
        this.polygons = polygons;
        this.setPolygonColor();
    }

    public Tetrahedron(FpsPolygon... polygons)
    {
        this.color = color.WHITE;
        this.polygons = polygons;
    }

    public void render(Graphics g)
    {
        for(FpsPolygon poly: polygons)
        {
            poly.render(g);
        }
    }

    public void rotate(boolean clockwise, double xdegrees, double ydegrees, double zdegrees)
    {
        for (FpsPolygon poly : polygons)
        {
            poly.rotate(clockwise, xdegrees, ydegrees, zdegrees);
        }
        this.sortPolygons();
    }

    private void sortPolygons()
    {
        FpsPolygon.sortPolys(polygons);
    }

    private void setPolygonColor()
    {
        for(FpsPolygon poly: polygons)
        {
            poly.setColor(this.color);
        }
    }
}
