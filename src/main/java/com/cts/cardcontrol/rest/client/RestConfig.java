package com.cts.cardcontrol.rest.client;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	private static Logger LOGGER = LoggerFactory.getLogger(RestConfig.class);

	@Value("${base24auth.service.connection.timeout}")
	private int connectTimeour;

	@Value("${proxy.host}")
	private String proxyHost;

	@Value("${proxy.port}")
	private int proxyPort;

	@Bean
	public RestOperations createRestTemplate(final ClientHttpRequestFactory clientHttpRequestFactory) {
		return new RestTemplate(clientHttpRequestFactory);
	}

	@Bean
	public ClientHttpRequestFactory createClientHttpRequestFactory() {
		/*
		 * HttpComponentsClientHttpRequestFactory
		 * httpComponentsClientHttpRequestFactory = new
		 * HttpComponentsClientHttpRequestFactory();
		 * httpComponentsClientHttpRequestFactory.setConnectTimeout(
		 * connectionTimeour);
		 */
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
		//clientHttpRequestFactory.setProxy(proxy);
		clientHttpRequestFactory.setConnectTimeout(connectTimeour);

		return clientHttpRequestFactory;
	}

}
