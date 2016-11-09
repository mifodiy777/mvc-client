package ru.innopolis.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.innopolis.client.service.LessonService;
import ru.innopolis.client.service.StudentService;

/**
 * Created by Кирилл on 03.11.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.innopolis.client")
public class WebAppConfig extends WebMvcConfigurerAdapter {


    // Позволяет видеть все ресурсы в корне, такие как картинки, стили и т.п.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
    }

    /**
     * Кастомный сервис аутентификации
     *
     * @return сервис
     */

    @Bean
    public UserDetailsService userDetailsService() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:5000/UserDetailsService");
        rmiProxyFactoryBean.setServiceInterface(UserDetailsService.class);
        rmiProxyFactoryBean.afterPropertiesSet();
        return (UserDetailsService) rmiProxyFactoryBean.getObject();
    }

    @Bean
    public StudentService studentService() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:5000/StudentService");
        rmiProxyFactoryBean.setServiceInterface(StudentService.class);
        rmiProxyFactoryBean.afterPropertiesSet();
        return (StudentService) rmiProxyFactoryBean.getObject();
    }

    @Bean
    public LessonService lessonService() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:5000/LessonService");
        rmiProxyFactoryBean.setServiceInterface(LessonService.class);
        rmiProxyFactoryBean.afterPropertiesSet();
        return (LessonService) rmiProxyFactoryBean.getObject();
    }

    // а этот бин инициализирует View нашего проекта
    // точно это же мы делали в mvc-dispatcher-servlet.xml
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

}
