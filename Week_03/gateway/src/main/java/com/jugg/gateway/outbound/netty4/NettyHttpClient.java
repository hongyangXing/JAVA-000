package com.jugg.gateway.outbound.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;

public class NettyHttpClient {

    public String connect(String host, int port, String api) throws Exception {
        final NettyHttpClientOutboundHandler responseHandler = new NettyHttpClientOutboundHandler();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 客户端接收到的是Response响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    // 客户端发送的是request，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast();
                    ch.pipeline().addLast("handler", responseHandler);
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();

            URI uri = new URI("http://" + host + ":" + port + "/" + api);
            String msg = "test";
            FullHttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString(),
                    Unpooled.wrappedBuffer(msg.getBytes()));
            // 构建http请求
            request.headers().set(HttpHeaderNames.HOST, host);
            request.headers().set(HttpHeaderNames.CONNECTION,
                    HttpHeaderNames.CONNECTION);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH,
                    request.content().readableBytes());
            request.headers().set("messageType", "normal");
            request.headers().set("businessType", "testServerState");
            f.channel().writeAndFlush(request);
            f.channel().closeFuture().sync();
            return responseHandler.getResponse();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

}