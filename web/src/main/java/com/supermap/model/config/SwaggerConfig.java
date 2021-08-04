package com.supermap.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration
public class SwaggerConfig {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.OAS_30)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.supermap.model.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("SpringBoot单数据源接口API")
                    .description("创建于2020/11/05")
                    .version("1.0")
                    .build();
        }
}