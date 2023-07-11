package pers.guangjian.hadoken.connector.core.device;

import pers.guangjian.hadoken.connector.core.message.codec.Transport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:12
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MqttAuthenticationRequest implements AuthenticationRequest {
    private String clientId;

    private String username;

    private String password;

    private Transport transport;
}