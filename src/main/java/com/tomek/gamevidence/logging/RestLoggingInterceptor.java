package com.tomek.gamevidence.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestLoggingInterceptor implements ClientHttpRequestInterceptor {

  private final Logger LOGGER = LoggerFactory.getLogger(RestLoggingInterceptor.class);

  @Override
  public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
    logRequest(httpRequest, bytes);
    ClientHttpResponse httpResponse = clientHttpRequestExecution.execute(httpRequest, bytes);
    logResponse(httpRequest, httpResponse);
    return httpResponse;
  }

  private void logRequest(HttpRequest request, byte[] body) {
    if (LOGGER.isInfoEnabled()) {
      StringBuilder sb = new StringBuilder("REST Outgoing")
              .append(" ").append(request.getMethod())
              .append(" ").append(request.getURI());
      LOGGER.info(sb.toString());
    }
  }

  private void logResponse(HttpRequest request, ClientHttpResponse response) {
    if (LOGGER.isInfoEnabled()) {
      StringBuilder sb = new StringBuilder("REST Incoming")
              .append(" ").append(request.getMethod())
              .append(" ").append(request.getURI());
      LOGGER.info(sb.toString());
    }
  }
}
