public class SynchronizedDemo {
    private boolean ready = false;
    private int result = 0;
    private int number = 1;

    private void write() {
        ready = true;
        number = 2;
    }

    private void read() {
        if (ready) {
            result = number * 3;
        }
        System.out.println("result = " + result);
    }

    public static void main(String[] args) {
        SynchronizedDemo sd = new SynchronizedDemo();
        Thread readThread = new Thread(sd::read);
        Thread writeThread = new Thread(sd::write);
        writeThread.start();
        readThread.start();

        TestInterface<InterruptedException> st = () -> Thread.sleep(3000);
    }
//    private class CThread extends Thread {
//        private boolean flag;
//        public CThread(boolean flag) {
//            this.flag = flag;
//        }
//
//        @Override
//        public void run() {
//            if (flag) {
//                write();
//            } else {
//                read();
//            }
//        }
//    }

//    public static void main(String[] args) {
//        SynchronizedDemo sd = new SynchronizedDemo();
//        sd.new CThread(true).start();
//        sd.new CThread(false).start();
//    }
}
