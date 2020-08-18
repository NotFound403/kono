package cn.felord.kono.configuration;

import cn.hutool.core.util.RandomUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Sms configuration.
 *
 * @author felord
 * @see CaptchaCacheStorage
 * @since 10 :52
 */

@Configuration
public class SMSConfiguration {
    /**
     * @see cn.felord.kono.enumeration.CacheEnum#SMS_CAPTCHA_CACHE
     */
    private static final String SMS_CAPTCHA_CACHE = "smsCode";

    /**
     * Sms cache storage sms cache storage.
     *
     * @return the sms cache storage
     */
    @Bean
    CaptchaCacheStorage smsCacheStorage() {
        return new CaptchaCacheStorage() {

            @CachePut(cacheNames = SMS_CAPTCHA_CACHE, key = "#phone")
            @Override
            public String put(String phone) {
                return RandomUtil.randomNumbers(5);
            }

            @Cacheable(cacheNames = SMS_CAPTCHA_CACHE, key = "#phone")
            @Override
            public String get(String phone) {
                return null;
            }

            @CacheEvict(cacheNames = SMS_CAPTCHA_CACHE, key = "#phone")
            @Override
            public void expire(String phone) {
                   //Noop
            }
        };
    }
}
