package memo;

import memo.security.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns(
                "/random",
                "/getPictureByTitle",
                "/getPictures",
                "/increaseScore"
        );
        //Jeśli dodamy w taki sposób jak poniżej to do żadnego endpointa nie zostanie przypisany filtr
        //Działa tylko w przypadku gdy podajemy jeden endpoint, to powyżej działa dla kilku
        /*
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/random"));
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/getPictureByTitle"));
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/getPictures"));
        */
        return filterRegistrationBean;
    }
}
