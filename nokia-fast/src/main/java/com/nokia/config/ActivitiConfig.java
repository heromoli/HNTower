package com.nokia.config;

import com.nokia.datasource.config.DynamicDataSource;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;

/**
 * Created by wow on 2019/6/18.
 */
@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {
    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(@Qualifier("dynamicDataSource") DynamicDataSource dataSource,
                                                                             @Qualifier("transactionManager") PlatformTransactionManager transactionManager,
                                                                             SpringAsyncExecutor springAsyncExecutor) throws IOException {
        SpringProcessEngineConfiguration springProcessEngineConfiguration = this.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        return springProcessEngineConfiguration;
    }
}
