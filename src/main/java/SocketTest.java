import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

public class SocketTest {

    public static void main(String[] args) {
        CountDownLatch startGate = new CountDownLatch(1);

        Runnable server = () -> {
            try {
                startGate.await();

                ServerSocket ss = new ServerSocket(8888);
                System.out.println("启动服务器....");
                Thread.sleep(2000);
                System.out.println("服务器接受消息");
                Socket s = ss.accept();
                System.out.println("客户端："+ s.getInetAddress().getHostName() + "已连接到服务器");

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //读取客户端发送来的消息
                String mess = br.readLine();
                System.out.println("来自客户端：" + mess);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                bw.write(mess + "\n");
                bw.flush();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        };

        Runnable client = () -> {
            try {
                startGate.await();
                Thread.sleep(1000);
                Socket s = new Socket("127.0.0.1",8888);

                //构建IO
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                //向服务器端发送一条消息
                bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
                bw.flush();
                System.out.println("客户端写消息");

                //读取服务器返回的消息
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String mess = br.readLine();
                System.out.println("来自服务器：" + mess);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        };

        new Thread(server).start();
        new Thread(client).start();
        startGate.countDown();
    }
}
