package cn.felord.kono.configuration;



/**
 * The interface Sms cache storage.
 *
 * @author Dax
 * @since 11 :13
 */
public interface CaptchaCacheStorage {


    /**
     * 验证码放入缓存.
     *
     * @param phone the phone
     * @return the string
     */
    String put(String phone);

    /**
     * 从缓存取验证码.
     *
     * @param phone the phone
     * @return the string
     */
    String get(String phone);

    /**
     * 验证码手动过期.
     *
     * @param phone the phone
     */
    void expire(String phone);
}
