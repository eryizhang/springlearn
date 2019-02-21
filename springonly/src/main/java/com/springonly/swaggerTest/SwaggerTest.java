package com.springonly.swaggerTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/12 10:18
 * @version: 1.0
 */
//swagger2的配置文件，在项目的启动类的同级文件建立

@Configuration
@EnableSwagger2
public class SwaggerTest {

    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等

    @Bean
    public Docket createRestApi() {
        System.out.println("right");
        ApiInfo apiInfo = new ApiInfoBuilder()
                //页面标题
                .title("测试title")
                //创建人
                .contact(new Contact("Maxg", "", ""))
                //版本号
                .version("1.0")
                //描述
                .description("描述").build();
        ParameterBuilder userName = new ParameterBuilder();
        ParameterBuilder tokenPar1 = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        userName.name("userName").description("用户名").defaultValue("userName").modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();
        tokenPar1.name("estateId").description("楼盘ID").defaultValue("estateId").modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();

        pars.add(userName.build());
        pars.add(tokenPar1.build());

        Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.zyh.learn.zyhlearn")).paths(PathSelectors.ant("/**"))
                .build().globalOperationParameters(pars).apiInfo(apiInfo);
        return docket;
    }
}
