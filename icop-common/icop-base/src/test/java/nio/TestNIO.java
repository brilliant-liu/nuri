package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author: liukj
 * @date: 2020/7/14
 * @description：
 */
public class TestNIO {

    public static void main(String[] args) {

    }

    @Test
    public void client() throws IOException {
        String file1 = "E:\\Documents\\image\\swiper\\201911261833002.jpg";
        String file2 = "E:\\Documents\\image\\swiper\\newfile.jpg";

        try(
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));
                FileChannel fileChannel = FileChannel.open(Paths.get(file1), StandardOpenOption.READ);
        ){
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer)!=-1){
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }
        }
    }

    @Test
    public void server() throws IOException {
        String file1 = "E:\\Documents\\image\\swiper\\201911261833002.jpg";
        String file2 = "E:\\Documents\\image\\swiper\\newfile2.jpg";

        try(
                ServerSocketChannel socketChannel = ServerSocketChannel.open();
                FileChannel fileChannel = FileChannel.open(Paths.get(file2), StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        ){
            socketChannel.bind(new InetSocketAddress(9898));
            SocketChannel accept = socketChannel.accept();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (accept.read(buffer)!=-1){
                buffer.flip();
                fileChannel.write(buffer);
                buffer.clear();
            }
        }
    }

    public static class StaticTest {
        public static void main(String[] args) {
            staticFunction();
        }

        static StaticTest st = new StaticTest();

        static {
            System.out.println("1");
        }
// 2 1 3 a=0,b=112
        {
            System.out.println("2");
        }

        StaticTest() {
            System.out.println("3");
            System.out.println("a="+a+",b="+b);
        }

        public static void staticFunction(){
            System.out.println("4");
        }

        int a=110;
        static int b =112;
    }
}
