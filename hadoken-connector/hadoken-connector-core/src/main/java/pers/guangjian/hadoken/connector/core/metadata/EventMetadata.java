package pers.guangjian.hadoken.connector.core.metadata;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:22
 */
public interface EventMetadata extends Metadata, Jsonable {

    DataType getType();

    default EventMetadata merge(EventMetadata another, MergeOption... option) {
        throw new UnsupportedOperationException("不支持事件物模型合并");
    }
}