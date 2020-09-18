import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AddressSet {
    private final HashMap<SocketAddress, Long> addressSet;
    long timeout = 5000;

    public AddressSet() {
        addressSet = new HashMap<>();
    }

    public void add(SocketAddress address, long time) {
        if (addressSet.containsKey(address)) {
            addressSet.replace(address, time);
            return;
        }
        addressSet.put(address, time);
        print();
    }

    public void check() {
        boolean toPrint = false;
        Iterator<Map.Entry<SocketAddress, Long>> iterator = addressSet.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<SocketAddress, Long> entry = iterator.next();
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
        for (SocketAddress socketAddress : addressSet.keySet()) {
            System.out.println(socketAddress);
        }
        System.out.println("-------");
    }
}
