package com.java.book.self.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author dongzonglei
 * @description
 * @date 2019-08-05 17:40
 */
public class NettyTest {

    public static void main(String args[]) throws Exception {
        // 事件处理器
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //boss 线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //worker 线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            //bind 服务端端口
            ChannelFuture f = b.bind(9090).sync();
            f.channel().closeFuture().sync();
        } finally {
            // 终止工作线程组
            workerGroup.shutdownGracefully();
            // 终止 boss 线程组
            bossGroup.shutdownGracefully();
        }
    }

    //socket 连接处理器
    static class EchoServerHandler extends ChannelInboundHandlerAdapter {

        // 处理读事件
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }

        // 处理读完成事件
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ctx.write(msg);
        }

        // 处理异常事件
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
    }
}
