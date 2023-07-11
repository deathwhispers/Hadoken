package pers.guangjian.hadoken.connector.core.message.codec;

import pers.guangjian.hadoken.connector.core.metadata.ConfigMetadata;

import javax.annotation.Nullable;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:34
 */
public interface MessageCodecDescription {
    String getDescription();

    @Nullable
    ConfigMetadata getConfigMetadata();

}