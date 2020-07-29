package cn.felord.kono.advice;

/**
 * The interface Rest.
 *
 * @param <T> the type parameter
 * @author felord.cn
 * @since 22 :29  2019-04-02
 */
public interface Rest<T> {
    /**
     * 业务状态码，设计时应该区别于http状态码.
     *
     * @param code the code
     */
    void setCode(int code);

    /**
     * 数据载体.
     *
     * @param data the data
     */
    void setData(T data);

    /**
     * 提示信息.
     *
     * @param msg the msg
     */
    void setMsg(String msg);

    /**
     * 预留的标识位，作为一些业务的处理标识.
     *
     * @param identifier 标识
     */
    void setIdentifier(String identifier);
}
