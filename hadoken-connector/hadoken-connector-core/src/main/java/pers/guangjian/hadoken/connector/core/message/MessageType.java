package pers.guangjian.hadoken.connector.core.message;

import cn.hutool.json.JSONObject;
import pers.guangjian.hadoken.connector.core.device.DeviceThingType;
import pers.guangjian.hadoken.connector.core.event.EventMessage;
import com.rhy.aibox.connector.core.message.attribute.*;
import com.rhy.aibox.connector.core.message.firmware.*;
import pers.guangjian.hadoken.connector.core.message.function.FunctionInvokeMessage;
import pers.guangjian.hadoken.connector.core.message.function.FunctionInvokeMessageResponse;
import pers.guangjian.hadoken.connector.core.things.ThingId;
import lombok.SneakyThrows;
import pers.guangjian.hadoken.connector.core.message.attribute.*;
import pers.guangjian.hadoken.connector.core.message.firmware.*;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:06
 */
public enum MessageType {

    // 上报设备属性
    REPORT_ATTRIBUTE(ReportAttributeMessage::new),

    // 下行读属性
    READ_ATTRIBUTE(ReadAttributeMessage::new),
    //上行读响应
    READ_ATTRIBUTE_RESPONSE(ReadAttributeMessageResponse::new),

    // 下行写属性
    WRITE_ATTRIBUTE(WriteAttributeMessage::new),
    // 写响应
    WRITE_ATTRIBUTE_RESPONSE(WriteAttributeMessageResponse::new),

    // 调用设备能力
    INVOKE_ABILITY(FunctionInvokeMessage::new) {
        @Override
        public <T extends Message> T convert(Map<String, Object> map) {
            Object inputs = map.get("inputs");
            //处理以Map形式传入参数的场景
            if (inputs instanceof Map) {
                Map<String, Object> newMap = new HashMap<>(map);
                @SuppressWarnings("unchecked")
                Map<String, Object> inputMap = (Map<String, Object>) newMap.remove("inputs");
                FunctionInvokeMessage message = super.convert(newMap);
                inputMap.forEach(message::addInput);
                return (T) message;
            }
            return super.convert(map);
        }
    },

    // 调用设备能力响应
    INVOKE_ABILITY_RESPONSE(FunctionInvokeMessageResponse::new),
    //事件消息
    EVENT(EventMessage::new),

    //广播,暂未支持
    BROADCAST(DefaultBroadcastMessage::new),

    // 设备上线
    ONLINE(DeviceOnlineMessage::new),
    // 设备离线
    OFFLINE(DeviceOfflineMessage::new),

    // 注册消息
    REGISTER(DeviceRegisterMessage::new),
    // 注销消息
    UN_REGISTER(DeviceUnRegisterMessage::new),

    // 派生属性
    DERIVED_METADATA(DerivedMetadataMessage::new),

    // 读取设备固件信息
    READ_FIRMWARE(ReadFirmwareMessage::new),

    // 读固件信息响应
    READ_FIRMWARE_RESPONSE(ReadFirmwareMessageResponse::new),

    // 上报固件信息
    REPORT_FIRMWARE(ReportFirmwareMessage::new),

    // 设备拉取固件信息
    REQUEST_FIRMWARE(RequestFirmwareMessage::new),
    // 设备拉取固件信息响应
    REQUEST_FIRMWARE_RESPONSE(RequestFirmwareMessageResponse::new),

    // 固件升级
    //UPGRADE_FIRMWARE(UpgradeFirmwareMessage::new),

    // 固件升级消息响应
    //UPGRADE_FIRMWARE_RESPONSE(UpgradeFirmwareMessageResponse::new),

    // 固件升级进度上报
    //UPGRADE_FIRMWARE_PROGRESS(UpgradeFirmwareProgressMessage::new),

    // 透传消息
    DIRECT(DirectDeviceMessage::new),

    //更新标签
    //since 1.1.2
    UPDATE_TAG(UpdateTagMessage::new, DefaultUpdateTingTagsMessage::new),

    // 应答指令
    ACKNOWLEDGE(AcknowledgeDeviceMessage::new),

    // 状态检查
    //STATE_CHECK(DeviceStateCheckMessage::new),
    //STATE_CHECK_RESPONSE(DeviceStateCheckMessageResponse::new),

    // 未知消息（默认）
    UNKNOWN(null) {
        @Override
        @SuppressWarnings("all")
        public <T extends Message> T convert(Map<String, Object> map) {
            if (map.containsKey("success")) {
                CommonDeviceMessageResponse<?> response = new CommonDeviceMessageResponse();
                response.fromJson(new JSONObject(map));
                return (T) response;
            }
            CommonDeviceMessage response = new CommonDeviceMessage();
            response.fromJson(new JSONObject(map));
            return (T) response;
        }
    };

    final Supplier<? extends Message> deviceInstance;
    final Supplier<? extends Message> thingInstance;

    private static final Map<String, MessageType> mapping;

    static {
        mapping = new HashMap<>();
        for (MessageType value : values()) {
            mapping.put(value.name().toLowerCase(), value);
            mapping.put(value.name().toUpperCase(), value);
        }
    }

    MessageType(Supplier<Message> deviceInstance) {
        this(deviceInstance, null);
    }

    MessageType(Supplier<? extends Message> deviceInstance, Supplier<? extends ThingMessage> thingInstance) {
        this.deviceInstance = deviceInstance;
        this.thingInstance = thingInstance;
    }

    @SuppressWarnings("all")
    public <T extends DeviceMessage> T forDevice() {
        return (T) deviceInstance.get();
    }

    public <T extends ThingMessage> T forThing() {
        if (null == thingInstance) {
            throw new UnsupportedOperationException("type " + name() + " unsupported for thing");
        }
        return (T) thingInstance.get();
    }

    public <T extends ThingMessage> T forThing(ThingId thingId) {
        return forThing(thingId.getType(), thingId.getId());
    }

    public <T extends ThingMessage> T forThing(String type, String id) {
        if (!DeviceThingType.device.name().equals(type)) {
            return (T) this.forThing().thingId(type, id);
        }
        return (T) this.forDevice().thingId(type, id);
    }

    @SuppressWarnings("all")
    public <T extends Message> T convert(Map<String, Object> map) {
        Supplier<? extends Message> supplier = deviceInstance;

        if (deviceInstance != null) {
            if (thingInstance != null) {
                //不是设备
                Object type = map.get("thingType");
                if (type != null) {
                    if (!DeviceThingType.device.name().equals(type)) {
                        supplier = thingInstance;
                    }
                }
            }
            T msg = (T) supplier.get();
            msg.fromJson(new JSONObject(map));
            return msg;
//            try {
//                return (T) FastBeanCopier.copy(map, supplier);
//            } catch (Throwable e) {
//                //fallback jsonobject
//                return (T) new JSONObject(map).toJavaObject(supplier.get().getClass());
//            }
        }
        return null;
    }

    public static <T extends Message> Optional<T> convertMessage(Map<String, Object> map) {
        return of(map)
                .map(type -> type.convert(map));
    }

    public static Optional<MessageType> of(String name) {
        return Optional.ofNullable(mapping.get(name));
    }

    public static Optional<MessageType> of(Map<String, Object> map) {
        Object msgType = map.get("messageType");
        if (msgType instanceof MessageType) {
            return Optional.of((MessageType) msgType);
        } else if (msgType instanceof String) {
            return of(((String) msgType));
        }

        if (map.containsKey("event")) {
            return Optional.of(EVENT);
        }

        if (map.containsKey("functionId")) {
            return map.containsKey("inputs") ? Optional.of(INVOKE_ABILITY) : Optional.of(INVOKE_ABILITY_RESPONSE);
        }
        if (map.containsKey("properties")) {
            Object properties = map.get("properties");
            return properties instanceof Collection ? Optional.of(READ_ATTRIBUTE) : Optional.of(READ_ATTRIBUTE_RESPONSE);
        }
        if (map.containsKey("tags")) {
            return Optional.of(UPDATE_TAG);
        }
        if (map.containsKey("success")) {
            return Optional.of(ACKNOWLEDGE);
        }
        return Optional.of(UNKNOWN);
    }

    @SneakyThrows
    public static Message readExternal(ObjectInput input) {
        int type = input.readByte();
        MessageType messageType = values()[type];
        boolean isDevice = input.readBoolean();
        Message message;
        if (isDevice && messageType.deviceInstance != null) {
            message = messageType.deviceInstance.get();
        } else {
            message = messageType.thingInstance.get();
        }
        message.readExternal(input);
        return message;
    }

    @SneakyThrows
    public static void writeExternal(Message message, ObjectOutput output) {
        output.writeByte(message.getMessageType().ordinal());
        output.writeBoolean(message instanceof DeviceMessage);
        message.writeExternal(output);
    }
}
