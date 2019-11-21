public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static Runnable read = () -> {
        while (!ready) {
            Thread.yield();
        }
        System.out.println("number = " + number);
    };

    public static void main(String[] args) {
        new Thread(read).start();
        number = 42;
        ready = true;
    }
}
