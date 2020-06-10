package cn.yu.demo.service.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * swagger-ui配置文件设置 访问方式http://IP:port/{项目名}/swagger-ui.html
 */
@Configuration
@ConfigurationProperties(prefix = "swagger")
@EnableSwagger2
@ConditionalOnProperty(value = "swagger.enabled", havingValue = "true")
public class SwaggerConfig {

    private String basePackage;

    private String excludePackage;

    private String docTitle;

    private String docDescription;

    private String docVersion;

    // 定义分隔符
    private static final String SEPARATOR = ",";

    @Bean
    public Docket createRestApi() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json");
        // 添加head参数start
        ParameterBuilder timestamp = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        timestamp.name("timestamp").description("时间戳").modelRef(new ModelRef("string")).parameterType("header")
            .required(false).defaultValue("1591539846").build();
        pars.add(timestamp.build());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).produces(produces)
            .useDefaultResponseMessages(true).select()
            .apis(basePackage(this.getExcludePackage(), this.getBasePackage())).paths(PathSelectors.any()).build()
            .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(this.getDocTitle())// 大标题
            .description(this.getDocDescription()).version(this.getDocVersion())// 版本
            .build();
    }

    /**
     * @param basePackages
     * @return
     * @see RequestHandlerSelectors#basePackage(String)
     */
    public Predicate<RequestHandler> basePackage(final String exceludePackages, final String basePackages) {
        return (input) -> declaringClass(input).transform(handlerPackage(exceludePackages, basePackages)).or(true);

    }

    private Function<Class<?>, Boolean> handlerPackage(final String exceludePackages, final String basePackages) {
        return input -> {
            // 先判断是否在排除包内
            if (StringUtils.isNotEmpty(exceludePackages)) {
                for (String strPackage : exceludePackages.split(SEPARATOR)) {
                    boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                    if (isMatch) {
                        return false;
                    }
                }
            }

            // 在判断是否在指定的扫描包内
            for (String strPackage : basePackages.split(SEPARATOR)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

    public String getBasePackage() {
        return basePackage = StringUtils.isBlank(basePackage) ? "" : basePackage.trim();
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getExcludePackage() {
        return excludePackage = StringUtils.isBlank(excludePackage) ? "" : excludePackage.trim();
    }

    public void setExcludePackage(String excludePackage) {
        this.excludePackage = excludePackage;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocDescription() {
        return docDescription;
    }

    public void setDocDescription(String docDescription) {
        this.docDescription = docDescription;
    }

    public String getDocVersion() {
        return docVersion;
    }

    public void setDocVersion(String docVersion) {
        this.docVersion = docVersion;
    }
}
