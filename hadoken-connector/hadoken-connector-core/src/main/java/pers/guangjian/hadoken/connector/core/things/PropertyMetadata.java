package pers.guangjian.hadoken.connector.core.things;

import pers.guangjian.hadoken.connector.core.metadata.DataType;
import pers.guangjian.hadoken.connector.core.metadata.Jsonable;
import pers.guangjian.hadoken.connector.core.metadata.MergeOption;
import pers.guangjian.hadoken.connector.core.metadata.Metadata;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:21
 */
public interface PropertyMetadata extends Metadata, Jsonable {

    DataType getValueType();

    default PropertyMetadata merge(PropertyMetadata another, MergeOption... option){
        throw new UnsupportedOperationException("不支持属性物模型合并");
    }
}