package com.andrey.spring.config;

import com.andrey.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Configuration()
public class ApplicationConfiguration {

//    @Bean
//    @Profile("prod1")
//    @Scope(BeanDefinition.SCOPE_SINGLETON)
//    public ConnectionPool pool4(){
//        return new ConnectionPool("username", 20);
//    }
//
//    @Bean
//    @Scope(BeanDefinition.SCOPE_SINGLETON)
//    public ConnectionPool pool3(){
//        return new ConnectionPool("test-pool", 25);
//    }
}
