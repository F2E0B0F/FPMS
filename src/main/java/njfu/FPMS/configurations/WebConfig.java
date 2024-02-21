package njfu.FPMS.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 解决跨域问题
@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry){
        registry.addMapping("/**")
        .allowCredentials(true)    // Cookie 发送
        .allowedOriginPatterns("*")     // 支持的域
        .allowedMethods("GET","POST","PUT","DELETE")
        .allowedHeaders("*")
        .exposedHeaders("*");
    }
}
