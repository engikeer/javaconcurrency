import com.sun.istack.internal.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VehicleTracker {
    private final Map<String, MutablePoint> locations;

    public VehicleTracker(Map<String, MutablePoint> locations) {
//        this.locations = deepCopy(locations);
        this.locations = new HashMap<>();
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return new MutablePoint(loc);
    }

    public static void main(String[] args) {
        MutablePoint p = new MutablePoint();
        p.x = 1;
        p.y = 1;
        Map<String, MutablePoint> map = new HashMap<>();
        map.put("A", p);

        // 修改线程
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1500);
                p.x = 2;
                p.y = 2;
                System.out.println("线程1完成，p：(" + p.x + ", " + p.y + ")");
            } catch (Exception e) {}
        });

        // 拷贝线程
        Thread thread2 = new Thread(() -> {
            VehicleTracker vehicleTracker = new VehicleTracker(map);
            MutablePoint mp = vehicleTracker.getLocation("A");
            System.out.println("线程2完成，copy: (" + mp.x + ", " + mp.y + ")");
        });

        thread1.start();
        thread2.start();
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}

class MutablePoint {
    public int x, y;

    public MutablePoint() {x = 0; y = 0;}

//    // 非线程安全的拷贝构造方法，由于两个状态值不是同时捕获的
//    // 可能在捕获 x 之后， y 被其他线程修改，导致捕获的不一致的 x 和 y
//    public MutablePoint(MutablePoint p) {
//        this.x = p.x;
//        try {
//            Thread.sleep(3000);
//        } catch (Exception e) {
//            System.out.println("异常");
//        }
//        this.y = p.y;
//    }

    // 通过私有构造函数改变参数形式，从接收一个实例变为接收一组基本变量值
    // 基本变量值不会被线程共享
    private MutablePoint(int[] a) {
        this.x = a[0];
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("异常");
        }
        this.y = a[1];
    }

    // 安全的拷贝构造函数
    public MutablePoint(MutablePoint p) {
        this(p.get());
    }

    // 通过同步的 get 方法同时捕获 x 和 y
    public synchronized int[] get() {
        return new int[] {x, y};
    }
}