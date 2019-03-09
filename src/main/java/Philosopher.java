import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher implements Runnable {
    Monitor monitor;
    int id;
    private final Logger log = Logger.getAnonymousLogger();

    public Philosopher(Monitor monitor, int id)
    {
        this.monitor = monitor;
        this.id = id;
    }


    public void run()
    {
        while(true)
        {
            think();
            eat();
        }
    }

    public void think()
    {
        log.info("Filozof nr " +id+ " rozmyśla.");
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            log.log(Level.ALL,e.toString());
        }
    }

    public void eat()
    {
        monitor.takeForks(id);
        log.info("Filozof " +id+ " je.");
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            log.log(Level.ALL,e.toString());
        }
        monitor.returnForks(id);
    }
}
