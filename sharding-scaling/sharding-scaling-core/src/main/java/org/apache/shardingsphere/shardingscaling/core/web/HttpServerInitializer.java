package org.apache.shardingsphere.shardingscaling.core.web;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Http server initializer.
 *
 * @author ssxlulu
 */
public final class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(final SocketChannel socketChannel) {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new HttpServerCodec());
        channelPipeline.addLast(new HttpObjectAggregator(65536));
        channelPipeline.addLast(new HttpServerHandler());
    }
}
