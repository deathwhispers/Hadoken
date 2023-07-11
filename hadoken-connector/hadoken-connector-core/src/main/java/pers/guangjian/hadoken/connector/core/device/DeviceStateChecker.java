package pers.guangjian.hadoken.connector.core.device;

import javax.validation.constraints.NotNull;

/**
 * 设备状态检查器,用于自定义设备状态检查
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:41
 */
public interface DeviceStateChecker {

    /**
     * 检查设备状态
     *
     * @param device 设备操作接口
     * @return 设备状态 {@link DeviceState}
     * @see DeviceState
     */
    @NotNull
    Byte checkState(@NotNull DeviceOperator device);

    /**
     * 排序需要，值越小优先级越高
     *
     * @return 序号
     * @since 1.1.5
     */
    default long order() {
        return Long.MAX_VALUE;
    }
}
