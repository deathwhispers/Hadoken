package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.message.codec.Transport;

import java.io.Serializable;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:11
 */
public interface AuthenticationRequest extends Serializable {
    Transport getTransport();
}