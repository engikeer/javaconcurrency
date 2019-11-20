
public class Test {

//    public static void main(String[] args) throws InterruptedException {
//        LWidget lw = new LWidget();
//        Runnable runnable = () -> {
//            synchronized (lw.getFather()) {
//                System.out.println("进入BlockChild");
//                try {
//                    Thread.sleep(3000);
//                } catch (Exception e) {
//
//                }
//                System.out.println("退出BlockChild");
//            }
//
////            System.out.println("进入BlockChild");
////            try {
////                Thread.sleep(3000);
////            } catch (Exception e) {
////
////            }
////            System.out.println("退出BlockChild");
//        };
//        Thread t = new Thread(runnable);
//        t.start();
//        Thread.sleep(1000);
//        lw.dos();
//    }

    public static void main(String[] args) {
        LWidget lw = new LWidget();
//        System.out.println();
        System.out.println(lw.getFather() == lw);
//        System.out.println(lw.name);
//        System.out.println(lw.getFather().name);
    }
}

class Widget {
    public String name = "ff";
    public synchronized void dos() {
        System.out.println("进入父方法");
    }

    public Widget getThis() {
        return this;
    }
}

class LWidget extends Widget {
    public String name = "cc";

    public synchronized void dos() {
        System.out.println("进入子方法");
        super.dos();
        System.out.println("退出子方法");
    }

    public void blockChild() throws InterruptedException {
        synchronized (this) {
            System.out.println("进入BlockChild");
            Thread.sleep(3000);
            System.out.println("退出BlockChild");
        }
    }

    public Widget getFather() {
        return super.getThis();
    }
}