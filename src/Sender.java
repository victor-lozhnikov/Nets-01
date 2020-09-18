import java.io.IOException;
import java.net.*;

public class Sender extends Thread {
    InetAddress group;
    int port;
    MulticastSocket socket;
    AddressSet addressSet;

    String message = "Hello, who is there?";
    DatagramPacket packet;
    int sleepTime = 1000;

    public Sender(InetAddress group, int port, AddressSet addressSet) throws IOException {
        this.group = group;
        this.port = port;
        this.addressSet = addressSet;

        socket = new MulticastSocket();

        packet = new DatagramPacket(message.getBytes(), message.length(), group, port);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                socket.send(packet);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
