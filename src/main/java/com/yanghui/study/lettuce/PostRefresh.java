package com.yanghui.study.lettuce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;


/**
 * @author yanghui
 * @date 2019-9-2
 */
@Component
@Slf4j
public class PostRefresh implements ApplicationRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void run(ApplicationArguments args) throws Exception {
        RedisConnectionFactory redisConnectionFactory = applicationContext.getBean(RedisConnectionFactory.class);
        //
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
