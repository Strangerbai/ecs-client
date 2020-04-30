package com.bee.sample.ecs.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.InputStreamReader;
import java.net.InetAddress;


public class RpcClient {

    public String send(String msg) throws Exception {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        RpcClientHandler rpcClientHandler = new RpcClientHandler();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(parentGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LineBasedFrameDecoder(2048));
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(rpcClientHandler);
                    }
                });

        ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
        future.channel().writeAndFlush(msg + "\r\n").sync();
        return "success";

    }
}
