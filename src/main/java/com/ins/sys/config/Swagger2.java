package com.ins.sys.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    private final Logger logger = LogManager.getLogger(Swagger2.class);

    @Value("${swagger.scan}")
    private String path;

    @Bean
    public Docket createRestApi() {
        String paths = "com.ins."+path;
        logger.info("Swagger load >>>>>>"+paths);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(paths))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("INS SYS-API文档")
                .description("1.0-Beta")
                //.termsOfServiceUrl("https://gitee.com/QuanZhanZhiLu/easy-boot")
                .version("1.0")
                .build();
    }
}
