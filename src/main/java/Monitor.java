public interface Monitor {
    // called by a philosopher when he/she wants to eat
     void takeForks(int philosopherId);

    // called by a philosopher when he/she are done eating
     void returnForks(int philosopherId);
}
