package pers.guangjian.hadoken.connector.core.route;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 10:04
 */
public interface MqttRoute extends Route {

    String getTopic();

    boolean isUpstream();

    boolean isDownstream();

    default int getQos() {
        return 0;
    }

    @Override
    default String getAddress() {
        return getTopic();
    }

    static Builder builder(String topic) {
        return DefaultMqttRoute
                .builder()
                .topic(topic);
    }

    interface Builder{

        Builder group(String group);

        Builder topic(String topic);

        Builder qos(int qos);

        Builder downstream(boolean downstream);

        Builder upstream(boolean downstream);

        Builder description(String description);

        Builder example(String example);

        MqttRoute build();
    }
}
