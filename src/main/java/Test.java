import net.jcip.annotations.NotThreadSafe;
import sun.nio.ch.ThreadPool;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ForkJoinPool;

public class Test {
    ExecutorService es = ForkJoinPool.commonPool();
    Comparator comparator = Comparator.comparing(String::length);

    public static void main(String[] args) {
        System.out.println("Hello Concurrency!");
    }
}

@NotThreadSafe
class foo {

}
