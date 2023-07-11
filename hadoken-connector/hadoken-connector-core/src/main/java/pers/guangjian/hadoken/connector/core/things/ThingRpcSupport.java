package pers.guangjian.hadoken.connector.core.things;

import pers.guangjian.hadoken.connector.core.message.ThingMessage;

import java.util.List;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 9:25
 */
public interface ThingRpcSupport {

    List<? extends ThingMessage> call(ThingMessage message);

}
