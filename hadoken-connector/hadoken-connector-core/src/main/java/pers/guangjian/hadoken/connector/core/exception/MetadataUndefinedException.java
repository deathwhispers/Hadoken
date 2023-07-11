package pers.guangjian.hadoken.connector.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:48
 */
@Getter
@Setter
public class MetadataUndefinedException extends I18nSupportException {

    private String deviceId;

    public MetadataUndefinedException(String deviceId){
        super("validation.metadata_undefined",deviceId);
    }
}
