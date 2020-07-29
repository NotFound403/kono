package cn.felord.kono.advice;

import lombok.Data;

import java.io.Serializable;

/**
 * @author felord.cn
 * @since 22:32  2019-04-02
 */
@Data
public class RestBody<T> implements Rest<T>, Serializable {

    private static final long serialVersionUID = -7616216747521482608L;
    private int code = 200;
    private T data;
    private String msg = "";
    private String identifier = "";


    public static Rest<?> ok() {
        return new RestBody<>();
    }

    public static Rest<?> ok(String msg) {
        Rest<?> restBody = new RestBody<>();
        restBody.setMsg(msg);
        return restBody;
    }

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


    public static <T> Rest<T> build(int code, T data, String msg, String identifier) {
        Rest<T> restBody = new RestBody<>();
        restBody.setCode(code);
        restBody.setData(data);
        restBody.setMsg(msg);
        restBody.setIdentifier(identifier);
        return restBody;
    }

    public static Rest<?> failure(String msg, String identifier) {
        Rest<?> restBody = new RestBody<>();
        restBody.setMsg(msg);
        restBody.setIdentifier(identifier);
        return restBody;
    }

    public static Rest<?> failure(int httpStatus, String msg) {
        Rest<?> restBody = new RestBody<>();
        restBody.setCode(httpStatus);
        restBody.setMsg(msg);
        restBody.setIdentifier("-9999");
        return restBody;
    }

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
                "code:" + code +
                ", data:" + data +
                ", msg:" + msg +
                ", identifier:" + identifier +
                '}';
    }
}
