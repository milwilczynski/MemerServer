package memo;

//KLASA ODPOWIADA ZA DODANIE RESOURCE BO SPRING NORMALNIE WIDZI TYLKO TO CO JEST W STATIC
//WIEC JAK ZROBIMY DODATKOWY FOLDER W STATIC TO MUSIMY DODAC GO DO PATHA JAK PONIZEJ


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//http://localhost:8080/images/boot.jpg
@Configuration
public class Resources implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("");
    }
}
