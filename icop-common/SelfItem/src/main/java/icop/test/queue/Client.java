package icop.test.queue;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: liukj
 * @date: 2020/7/30
 * @description：
 */
public class Client {

    public static void main(String[] args) throws IOException {
        String str = "985sfsfs";
        StringBuffer stringBuffer = new StringBuffer("lkksfjnwfs998sfashf施肥技术开发");

        System.out.println(stringBuffer.reverse());

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

            ByteBuffer writeBuffer = ByteBuffer.allocate(32);
            ByteBuffer readBuffer = ByteBuffer.allocate(32);

            writeBuffer.put("hello".getBytes());
            writeBuffer.flip();

            for(int i =0;i<10;i++){
                writeBuffer.rewind();
                socketChannel.write(writeBuffer);
                readBuffer.clear();
                socketChannel.read(readBuffer);
            }
        } catch (IOException e) {
        }
    }
}
