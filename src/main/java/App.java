import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Monitor server = new MonitorImpl();
        List<Philosopher> pList= new ArrayList<Philosopher>();
        for (int i = 0; i < MonitorImpl.NUM_OF_PHILS; i++)
            pList.add(new Philosopher(server, i));
        pList.forEach(p -> new Thread(p).start());

    }

}
