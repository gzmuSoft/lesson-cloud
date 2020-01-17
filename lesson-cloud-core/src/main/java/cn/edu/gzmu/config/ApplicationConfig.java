package cn.edu.gzmu.config;

import cn.edu.gzmu.properties.Oauth2Properties;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-13 18:59
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig implements WebMvcConfigurer {

    private final @NonNull HateoasPageableHandlerMethodArgumentResolver pageableResolver;
    private final @NonNull Oauth2Properties oauth2Properties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(6000)
                .allowedOrigins("*");
    }

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(
                oauth2Properties.getClientId(),
                oauth2Properties.getClientSecret()
        );
    }

    @Bean
    @Primary
    public PagedResourcesAssembler<?> myPagedResourcesAssembler() {
        PagedResourcesAssembler<Object> pagedResourcesAssembler = new PagedResourcesAssembler<>(pageableResolver, null);
        pagedResourcesAssembler.setForceFirstAndLastRels(true);
        return pagedResourcesAssembler;
    }
}
