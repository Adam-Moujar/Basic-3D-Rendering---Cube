package Window;
import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    private JFrame frame;
    private String title;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Window()
    {
        frame = new JFrame();
        title = "Maze Generator 3D";
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        init();
    }

    public void init()
    {
        frame.setTitle(title);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setTitle(String title)
    {
        frame.setTitle(title);
    }

    public String getTitle()
    {
        return title;
    }

    //public static int getWidth()
    //{
    //    return WIDTH;
    //}

}
