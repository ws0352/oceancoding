package com.oceancoding.ws.ocean.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestfulApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oceancoding.ws.ocean"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder().title("小海马公司官方网站")
                        .description("小海马公司位于山东东营，是北京林业大学三位学生参与创建与运营的公司")
                        .version("9.0")
                        .contact(new Contact("王升","www.oceancoding.cn","13121192200@163.com"))
                        .license("The Apache License")
                        .licenseUrl("www.oceancoding.cn")
                        .build());
    }
}
