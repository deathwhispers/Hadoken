package pers.guangjian.hadoken.connector.core.message;

import javax.annotation.Nullable;
import java.util.Map;

/**更新物标签消息
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:27
 */
public interface UpdateTingTagsMessage extends ThingMessage {

    /**
     * key为标签ID，value为标签值
     *
     * @return 标签信息
     */
    @Nullable
    Map<String, Object> getTags();

    @Override
    default MessageType getMessageType() {
        return MessageType.UPDATE_TAG;
    }
}