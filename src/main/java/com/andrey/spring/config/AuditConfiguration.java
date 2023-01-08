package com.andrey.spring.config;

import com.andrey.spring.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@EnableJpaAuditing//регистрируем аудит
@EnableEnversRepositories(basePackageClasses = ApplicationRunner.class)//подключает механизм Hibernate Envers и позволяет делать вспомогательные запросы к этим таблицам.
// Еще она сканирует все классы Repositories
@Configuration
public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorAware(){
        var result = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        if(result.isEmpty()){
            return () -> Optional.of(" ");
        }else {
            return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> (UserDetails) authentication.getPrincipal())
                .map(UserDetails::getUsername);
        }
//
    }

}
