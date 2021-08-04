package com.supermap.model.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : zhangfx 2019/10/17/ 09:42
 * @version : 1.0
 */
@Configuration
public class EsConfig {

    @Value("${elasticsearch.scheme}")
    private String scheme;

    @Value("${elasticsearch.ip}")
    private  String ip;

    @Value("${elasticsearch.port}")
    private  int port;

    @Value("${elasticsearch.username}")
    private String username;

    @Value("${elasticsearch.password}")
    private  String password;

    @Bean
    public RestHighLevelClient client(){
        //需要用户名和密码的认证
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username,password));
        RestHighLevelClient client=new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(ip,port,scheme)
                ).setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
        );
        return client;
    }
}