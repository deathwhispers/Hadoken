package pers.guangjian.hadoken.connector.core;

/**
 * M2M连接接口
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/5/24 14:00
 */
public interface M2MConnect<T> {

    /**
     * 初始化连接
     */
    void initConnect();

    /**
     * 获取连接
     *
     * @return T
     */
    T getConnect();

    /**
     * 断开连接
     *
     * @return <code>true</code> 断开连接成功
     * <p>
     * <code>false</code> 断开连接失败
     */
    boolean disConnect();

    /**
     * 关闭连接
     */
    void close();
}
