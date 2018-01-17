package com.corvesta.keyspring.blueprint.config.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
/* 
 * This Class is used so that we can get all the request headers logged.
 */
@Configuration
public class RequestLoggingFilterConfig {
 
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
          = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(false);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("Exit ");
        filter.setBeforeMessagePrefix("Enter ");
        return filter;
    }
}