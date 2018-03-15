package com.upday.config;

import com.upday.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class is responsible for Web configuration.
 * Created by Amit Mohapatra on 17/03/18.
 * <p/>
 */
@Configuration
public class UpdayWebConfig extends WebMvcConfigurerAdapter
{

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }

}
