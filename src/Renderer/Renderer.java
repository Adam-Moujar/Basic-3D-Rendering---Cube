package Renderer;

import Renderer.Point.FpsPoint;
import Renderer.Shapes.FpsPolygon;
import Renderer.Shapes.Tetrahedron;
import Window.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer {

    private Window window;
    private BufferStrategy bs;

    private Tetrahedron tetra;

    public Renderer(Window window)
    {
        this.window = window;
        init();
    }

    public void render()
    {
        bs = window.getBufferStrategy();
        if(bs == null)
        {
            window.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,window.WIDTH,window.HEIGHT);

        tetra.render(g);

        g.dispose();
        bs.show();
    }
    public void update()
    {
        tetra.rotate(true, 1,1,1);
    }

    public void init()
    {
        int s = 100;
        FpsPoint p1 = new FpsPoint(s/2, -s/2, -s/2);
        FpsPoint p2 = new FpsPoint(s/2, s/2, -s/2);
        FpsPoint p3 = new FpsPoint(s/2, s/2, s/2);
        FpsPoint p4 = new FpsPoint(s/2, -s/2, s/2);
        FpsPoint p5 = new FpsPoint(-s/2, -s/2, -s/2);
        FpsPoint p6 = new FpsPoint(-s/2, s/2, -s/2);
        FpsPoint p7 = new FpsPoint(-s/2, s/2, s/2);
        FpsPoint p8 = new FpsPoint(-s/2, -s/2, s/2);
        tetra = new Tetrahedron(
                new FpsPolygon(Color.BLUE, p5,p6,p7,p8),
                new FpsPolygon(Color.WHITE, p1,p2,p6,p5),
                new FpsPolygon(Color.YELLOW, p1,p5,p8,p4),
                new FpsPolygon(Color.GREEN, p2,p6,p7,p3),
                new FpsPolygon(Color.ORANGE, p4,p3,p7,p8),
                new FpsPolygon(Color.RED, p1,p2,p3,p4)
        );

    }
}
