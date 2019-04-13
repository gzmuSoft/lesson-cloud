package cn.edu.gzmu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-13 18:59
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(6000)
                .allowedOrigins("*");
    }
}
