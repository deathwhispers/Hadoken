package pers.guangjian.hadoken.connector.core.metadata;

import pers.guangjian.hadoken.connector.core.event.EventMessage;
import pers.guangjian.hadoken.connector.core.message.UpdateTagMessage;
import com.rhy.aibox.connector.core.message.attribute.*;
import pers.guangjian.hadoken.connector.core.message.attribute.*;
import pers.guangjian.hadoken.connector.core.message.function.FunctionInvokeMessage;
import pers.guangjian.hadoken.connector.core.message.function.FunctionInvokeMessageResponse;
import pers.guangjian.hadoken.connector.core.things.PropertyMetadata;
import pers.guangjian.hadoken.connector.core.things.ThingMetadata;

import java.util.List;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:54
 */
public interface DeviceMetadata extends ThingMetadata {

    /**
     * @return 所有属性定义
     * @see ReadAttributeMessage
     * @see WriteAttributeMessage
     * @see ReportAttributeMessage
     * @see ReadAttributeMessageResponse
     * @see WriteAttributeMessageResponse
     */
    List<PropertyMetadata> getProperties();

    /**
     * @return 所有功能定义
     * @see FunctionInvokeMessage
     * @see FunctionInvokeMessageResponse
     */
    List<FunctionMetadata> getFunctions();

    /**
     * @return 事件定义
     * @see EventMessage
     */
    List<EventMetadata> getEvents();

    /**
     * @return 标签定义
     * @see UpdateTagMessage
     */
    List<PropertyMetadata> getTags();

    /**
     * 合并物模型，合并后返回新的物模型对象
     *
     * @param metadata 要合并的物模型
     * @since 1.1.6
     */
    @Override
    default <T extends ThingMetadata> DeviceMetadata merge(T metadata) {
        return this.merge(metadata, MergeOption.DEFAULT_OPTIONS);
    }

    default <T extends ThingMetadata> DeviceMetadata merge(T metadata, MergeOption... options) {
        throw new UnsupportedOperationException("unsupported merge metadata");
    }
}
