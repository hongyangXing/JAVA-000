package com.jugg.gateway.outbound.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;


// service返回数据
public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter {


    private String response;


    public String getResponse() {
        return this.response;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        try {
            if (msg instanceof HttpContent) {
                HttpContent httpContent = (HttpContent) msg;
                ByteBuf bb = httpContent.content();
                byte[] respByte = new byte[bb.readableBytes()];
                bb.readBytes(respByte);
                response = new String(respByte, Charset.defaultCharset());
                /*response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(respByte));
                response.headers().set("Content-Type", "application/json");
                response.headers().setInt("Content-Length", 100);*/

                ctx.channel().writeAndFlush(response).addListeners(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            ctx.channel().read();
                        } else {
                            future.channel().close();
                        }
                    }
                });
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    // 出现异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}