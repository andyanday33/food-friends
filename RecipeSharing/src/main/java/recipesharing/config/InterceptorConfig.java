package recipesharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import recipesharing.interceptor.JWTInterceptor;
/*
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/", "/admin/login"
                        , "/api", "/admin/logout", "/user/logout"
                        ,"/user/register")
        ;
    }
}
*/