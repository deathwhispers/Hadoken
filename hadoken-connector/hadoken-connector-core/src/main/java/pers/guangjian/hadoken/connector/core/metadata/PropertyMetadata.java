package pers.guangjian.hadoken.connector.core.metadata;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 16:55
 */
public interface PropertyMetadata  extends Metadata, Jsonable {

    DataType getValueType();

    default PropertyMetadata merge(PropertyMetadata another, MergeOption... option){
        throw new UnsupportedOperationException("不支持属性物模型合并");
    }
}
