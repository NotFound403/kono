package cn.felord.kono.advice;

import java.io.Serializable;

/**
 * @author Dax
 * @since 22:32  2019-04-02
 */
public class RestBody<T> implements Rest<T>, Serializable {

    private static final long serialVersionUID = -7616216747521482608L;
    private int httpStatus = 200;
    private T data;
    private String msg = "";
    private String identifier = "";

    /**
     * Gets http status.
     *
     * @return the http status
     */
    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Gets identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets identifier.
     *
     * @param identifier the identifier
     */
    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Ok rest.
     *
     * @return the rest
     */
    public static Rest<?> ok() {
        return new RestBody<>();
    }

    /**
     * Ok rest.
     *
     * @param msg the msg
     * @return the rest
     */
    public static Rest<?> ok(String msg) {
        Rest<?> restBody = new RestBody<>();
        restBody.setMsg(msg);
        return restBody;
    }

    /**
     * Ok data rest.
     *
     * @param <T> the type parameter
     * @return the rest
     */
    public static <T> Rest<T> okData(T data) {
        Rest<T> restBody = new RestBody<>();
        restBody.setData(data);
        return restBody;
    }

    public static <T> Rest<T> okData(T data, String msg) {
        Rest<T> restBody = new RestBody<>();
        restBody.setData(data);
        restBody.setMsg(msg);
        return restBody;
    }

    /**
     * Build rest.
     *
     * @param <T>        the type parameter
     * @param httpStatus the http status
     * @param data       the data
     * @param msg        the msg
     * @param identifier the identifier
     * @return the rest
     */
    public static <T> Rest<T> build(int httpStatus, T data, String msg, String identifier) {
        Rest<T> restBody = new RestBody<>();
        restBody.setHttpStatus(httpStatus);
        restBody.setData(data);
        restBody.setMsg(msg);
        restBody.setIdentifier(identifier);
        return restBody;
    }

    /**
     * Failure rest.
     *
     * @param msg the msg
     * @return the rest
     */
    public static Rest<?> failure(String msg, String identifier) {
        Rest<?> restBody = new RestBody<>();
        restBody.setMsg(msg);
        restBody.setIdentifier(identifier);
        return restBody;
    }

    /**
     * Failure rest.
     *
     * @param msg the msg
     * @return the rest
     */
    public static Rest<?> failure(int httpStatus, String msg ) {
        Rest<?> restBody = new RestBody< >();
        restBody.setHttpStatus(httpStatus);
        restBody.setMsg(msg);
        restBody.setIdentifier("-9999");
        return restBody;
    }

    /**
     * Failure data rest.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @param msg  the msg
     * @return the rest
     */
    public static <T> Rest<T> failureData(T data, String msg, String identifier) {
        Rest<T> restBody = new RestBody<>();
        restBody.setIdentifier(identifier);
        restBody.setData(data);
        restBody.setMsg(msg);
        return restBody;
    }

    @Override
    public String toString() {
        return "{" +
                "httpStatus:" + httpStatus +
                ", data:" + data +
                ", msg:" + msg +
                ", identifier:" + identifier +
                '}';
    }
}
