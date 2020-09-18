import java.io.IOException;
import java.net.*;

public class Receiver extends Thread {
    InetAddress group;
    int port;
    MulticastSocket socket;
    AddressSet addressSet;

    public Receiver(InetAddress group, int port, AddressSet addressSet) throws IOException {
        this.group = group;
        this.port = port;
        this.addressSet = addressSet;

        socket = new MulticastSocket(port);
        socket.joinGroup(new InetSocketAddress(group, port),
                NetworkInterface.getByInetAddress(group));
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            addressSet.add(packet.getSocketAddress(), System.currentTimeMillis());
        }
    }
}
