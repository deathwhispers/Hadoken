package pers.guangjian.hadoken.connector.core.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:15
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStateInfo implements Serializable {
    private String deviceId;

    private byte state;
}