package pers.guangjian.hadoken.connector.core.cluster;

import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 10:11
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerNode implements Serializable {
    private static final long serialVersionUID = -6849794470754667710L;

    @Nonnull
    private String id;

    private String name;

    private String host;

    private Map<String, Object> tags;

    private boolean leader;

    private long uptime;

    private long lastKeepAlive;

    public boolean hasTag(String tag) {
        return tags != null && tags.containsKey(tag);
    }

    public Optional<Object> getTag(String tag) {
        return Optional.ofNullable(tags)
                .map(t -> t.get(tag));
    }

    public boolean isSame(ServerNode another) {
        return id.equals(another.getId());
    }

    public ServerNode copy() {
        ServerNode serverNode = new ServerNode();
        BeanUtils.copyProperties(this, serverNode);
        return serverNode;
    }
}
