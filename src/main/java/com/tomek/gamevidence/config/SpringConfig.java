package com.tomek.gamevidence.config;

import com.tomek.gamevidence.logging.RestLoggingInterceptor;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringConfig {

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();

    List<ClientHttpRequestInterceptor> interceptors
            = restTemplate.getInterceptors();
    if (CollectionUtils.isEmpty(interceptors)) {
      interceptors = new ArrayList<>();
    }
    interceptors.add(new RestLoggingInterceptor());
    restTemplate.setInterceptors(interceptors);
    return restTemplate;
  }

  @Bean
  public ServletRegistrationBean h2servletRegistration(){
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
    registrationBean.addUrlMappings("/console/*");
    return registrationBean;
  }
}
