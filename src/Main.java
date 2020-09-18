import java.io.IOException;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) {
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        InetAddress group;

        AddressSet addressSet = new AddressSet();

        try {
            group = InetAddress.getByName(ip);

            Sender sender = new Sender(group, port, addressSet);
            sender.start();

            Receiver receiver = new Receiver(group, port, addressSet);
            receiver.start();

            Checker checker = new Checker(addressSet);
            checker.start();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
