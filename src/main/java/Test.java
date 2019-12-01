import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);



        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (Exception e) {

            }
//            list.set(0, 100);
            map.put("A", 100);
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(450);
            } catch (Exception e) {

            }
//            System.out.println(list.get(0));
            System.out.println(map.get("A"));
        });
//
        thread2.start();
        thread1.start();



    }



}
