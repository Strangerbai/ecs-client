package com.bee.sample.ecs.server;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Component;

//@Component
public class RpcServer {

    // 启动服务器
    public void start() throws Exception {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                    // 指定存放已经连接上但还来不及处理的请求的队列的长度
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 为了保证请求长连接不被清除掉
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new LineBasedFrameDecoder(2048));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new RpcServerHandler());
                        }
                    });


            ChannelFuture future = bootstrap.bind(8888).sync();
            System.out.println("服务已启动");
            future.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }


}
