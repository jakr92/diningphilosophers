


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonitorImpl implements Monitor {
    //Set number of Philosophers, States
    public static final int NUM_OF_PHILS = 5;
    public enum State {Thinking, Eating, Hungry}
    //Initialize lock object, State and Condition arrays
    private Lock lock;
    private State[] state;
    private Condition[] forks;
    private final Logger log = Logger.getAnonymousLogger();
    public MonitorImpl()
    {
        lock = new ReentrantLock();
        state = new State[NUM_OF_PHILS];
        forks = new Condition[NUM_OF_PHILS];
        for (int i=0; i<NUM_OF_PHILS; i++)
        {

            state[i] = State.Thinking;
            forks[i] = lock.newCondition();
        }
    }

    public void takeForks(int philosopherId)
    {
        lock.lock();
        try
        {
            state[philosopherId] = State.Hungry;
            log.info("Filozof nr "+philosopherId+" jest głodny.");
            if (!(state[(philosopherId - 1 + NUM_OF_PHILS) % NUM_OF_PHILS]
                    == State.Eating) &&
                    !(state[(philosopherId + 1) % NUM_OF_PHILS]
                            == State.Eating))
            {
                state[philosopherId] = State.Eating;
            }
            else
            {
                forks[philosopherId].await();
                state[philosopherId] = State.Eating;
            }
        }
        catch (InterruptedException e)
        {
            log.log(Level.ALL,e.toString());
        }
        finally
        {
            lock.unlock();
        }
    }

    public void returnForks(int philosopherId)
    {
        lock.lock();
        try
        {
            log.info("Filozof nr "+philosopherId+ " skończył jeść.");
            forks[philosopherId].signal();
            if (philosopherId==4) {
                forks[0].signal();
            }
            else
            {
                forks[philosopherId + 1].signal();
            }
        }
        finally
        {
            lock.unlock();
        }
    }
}
