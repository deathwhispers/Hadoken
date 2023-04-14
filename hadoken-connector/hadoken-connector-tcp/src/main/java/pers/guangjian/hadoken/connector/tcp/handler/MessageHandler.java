package pers.guangjian.hadoken.connector.tcp.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.guangjian.hadoken.connector.tcp.message.JiecHeartBeat;
import pers.guangjian.hadoken.connector.tcp.message.JiecHeartBeatResp;
import pers.guangjian.hadoken.connector.tcp.message.JiecVehicleNumReport;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2023/4/13 10:15
 */
@Slf4j
@Component
@ChannelHandler.Sharable
@RequiredArgsConstructor
public class MessageHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        log.debug("channelId:" + ctx.channel().id());
        log.debug("收到消息:{}", message);
        JSONObject parse = JSONUtil.parseObj(message);
        String cmd = parse.getStr("cmd");
        if (cmd.equals("1001")) {
            // 心跳
            JiecHeartBeat jiecHeartBeat = JSONUtil.toBean(message, JiecHeartBeat.class);
            // 心跳响应
            ctx.writeAndFlush(JSONUtil.toJsonStr(JiecHeartBeatResp.resp()));
        }
        if (cmd.equals("1003")) {
            // 车辆计数上报
            JiecVehicleNumReport vehicleNumReport = JSONUtil.toBean(message, JiecVehicleNumReport.class);

        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.debug("断开连接");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("成功建立连接,channelId：{}", ctx.channel().id());
        super.channelActive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.debug("心跳事件时触发");
        if (evt instanceof IdleStateEvent) {
            log.debug("发送心跳");
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}