import java.net.InetAddress;
import java.util.*;

public class AddressSet {
    private final HashMap<InetAddress, Long> addressSet;
    long timeout = 3000;

    public AddressSet() {
        addressSet = new HashMap<>();
    }

    public void add(InetAddress address, long time) {
        if (addressSet.containsKey(address)) {
            addressSet.replace(address, time);
            return;
        }
        addressSet.put(address, time);
        print();
    }

    public void check() {
        boolean toPrint = false;
        Iterator<Map.Entry<InetAddress, Long>> iterator = addressSet.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<InetAddress, Long> entry = iterator.next();
            if (System.currentTimeMillis() - entry.getValue() > timeout) {
                iterator.remove();
                toPrint = true;
            }
        }
        if (toPrint) {
            print();
        }
    }

    public void print() {
        for (InetAddress inetAddress : addressSet.keySet()) {
            System.out.println(inetAddress.getHostAddress());
        }
        System.out.println("-------");
    }
}
