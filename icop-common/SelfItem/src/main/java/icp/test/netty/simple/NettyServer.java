package icp.test.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: liukj
 * @date: 2020/8/14
 * @description：
 */
public class NettyServer {

    public static void main(String[] args) {
        // 该线程组只是处理连接请求，交给workerGroup,两个都是无限循环
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 创建服务器端，配置启动参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        /*serverBootstrap.group(bossGroup,workerGroup)
                //使用nioSockerChannel作为实现
                .channel(NioServerSocketChannel.class)
                // 保存活性连接
                .option(ChannelOption.SO_BACKLOG,128)*/
    }
}
