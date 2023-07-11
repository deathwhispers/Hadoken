package pers.guangjian.hadoken.connector.core.things;

import lombok.*;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 15:13
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
public class ThingId {

    @NonNull
    private final String type;

    @NonNull
    private final String id;

    public String toUniqueId() {
        byte[] typeBytes = type.getBytes(StandardCharsets.UTF_8);
        byte[] idBytes = id.getBytes(StandardCharsets.UTF_8);
        byte[] arr = new byte[typeBytes.length + idBytes.length];
        System.arraycopy(typeBytes, 0, arr, 0, typeBytes.length);
        System.arraycopy(idBytes, 0, arr, typeBytes.length, idBytes.length);
        return DigestUtils.md5DigestAsHex(arr);
    }

    @Override
    public String toString() {
        return type + ":" + id;
    }
}
