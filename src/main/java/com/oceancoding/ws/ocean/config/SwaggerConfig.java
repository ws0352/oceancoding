package com.oceancoding.ws.ocean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //是否开启swagger，正式环境关闭
    @Value("${swagger.enabled}")
    private boolean enableSwagger;
    @Bean
    public Docket createRestfulApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否开启
                .enable(enableSwagger)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oceancoding.ws.ocean"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("小海马有限公司官网接口文档")
                .description("文档旨在为开发人员更直观的看到接口形式")
                .termsOfServiceUrl("http://www.oceancoding.cn")
                .contact("wangsheng")
                .version("1.0.0")
                .build();
    }
}
