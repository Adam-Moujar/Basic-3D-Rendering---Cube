package Game;

import Renderer.Renderer;
import Window.Window;

public class Game implements Runnable{

    private Thread thread;
    private boolean isRunning;
    private Renderer renderer;
    private Window window;

    public Game()
    {
        isRunning = false;
        window = new Window();
        renderer = new Renderer(window);
    }
    public void start()
    {
        isRunning = true;
        this.thread = new Thread(this, "Window");
        thread.start();
    }

    public void stop()
    {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double tps = 1000000000.0/ 60; // I want the game to have 60 ticks per second
        double timeDiff = 0;
        int frames = 0;


        while(isRunning)
        {
            long currentTime = System.nanoTime();
            timeDiff += (currentTime - lastTime) / tps;
            lastTime = currentTime;

            while(timeDiff >= 1)
            {
                renderer.update();
                timeDiff--;

                renderer.render();
                frames++;
            }

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                window.setTitle(window.getTitle() + " | " + frames + " fps");
                frames = 0;
            }
        }

        stop();
    }
}
