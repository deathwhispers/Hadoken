package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.message.DeviceMessage;
import pers.guangjian.hadoken.connector.core.message.interceptor.DeviceMessageSenderInterceptor;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:36
 */
public class CompositeDeviceMessageSenderInterceptor implements DeviceMessageSenderInterceptor {
    private final List<DeviceMessageSenderInterceptor> interceptors = new CopyOnWriteArrayList<>();

    public void addInterceptor(DeviceMessageSenderInterceptor interceptor) {
        interceptors.add(interceptor);
        //重新排序
        interceptors.sort(Comparator.comparingInt(DeviceMessageSenderInterceptor::getOrder));
    }

    /**
     * 在消息发送前触发. 执行此方法后将使用返回值{@link DeviceMessage}进行发送到设备.
     *
     * @param device  设备操作接口
     * @param message 消息对象
     * @return 新的消息
     */
    @Override
    public DeviceMessage preSend(DeviceOperator device, DeviceMessage message) {
        DeviceMessage mono = (message);
        for (DeviceMessageSenderInterceptor interceptor : interceptors) {
            mono = interceptor.preSend(device, mono);
        }
        return mono;
    }

    /**
     * 执行发送时触发.
     *
     * @param device 设备操作接口
     * @param source 指令
     * @param sender 消息发送逻辑
     * @return 新的发送逻辑
     */
    @Override
    public List<DeviceMessage> doSend(DeviceOperator device, DeviceMessage source, List<DeviceMessage> sender) {
        List<DeviceMessage> flux = sender;

        for (DeviceMessageSenderInterceptor interceptor : interceptors) {
            flux = interceptor.doSend(device, source, flux);
        }
        return flux;
    }

    /**
     * 在消息发送后触发.这里发送后并不是真正的发送，其实只是构造了整个发送的逻辑流{@link List}(参数 reply),
     *
     * @param device  设备操作接口
     * @param message 源消息
     * @param reply   回复的消息
     * @param <R>     回复的消息类型
     * @return 新的回复结果
     */
    @Override
    public <R extends DeviceMessage> List<R> afterSent(DeviceOperator device, DeviceMessage message, List<R> reply) {

        List<R> flux = reply;

        for (DeviceMessageSenderInterceptor interceptor : interceptors) {
            flux = interceptor.afterSent(device, message, flux);
        }
        return flux;

    }
}
