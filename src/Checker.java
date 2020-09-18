public class Checker extends Thread {
    AddressSet addressSet;
    int sleepTime = 1000;

    public Checker(AddressSet addressSet) {
        this.addressSet = addressSet;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            addressSet.check();
        }
    }
}
