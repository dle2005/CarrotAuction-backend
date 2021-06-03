package carrotauction.com.carrotauction.config;

import javax.servlet.ServletContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;


@Configuration
@EnableJpaAuditing
public class JpaConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8000", "http://www.carrotauction.com")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "HEAD", "DELETE");
    }

    @Bean
    public TomcatContextCustomizer sameSiteCookiesConfig() {
        return context -> {
            final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
            cookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
            context.setCookieProcessor(cookieProcessor);
        };
    }

    @Bean
    public ServletContextInitializer servletContextInitializer(@Value("${secure.cookie:true}") boolean secure) {
        return (ServletContext servletContext) -> {
            servletContext.getSessionCookieConfig().setSecure(secure);
        };
    }
}
