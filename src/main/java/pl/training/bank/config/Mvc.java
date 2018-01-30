package pl.training.bank.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.training.bank.dto.DtoMapper;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@ComponentScan("pl.training.bank.controller")
@Import(Beans.class)
@EnableWebMvc
@Configuration
public class Mvc implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("resources").addResourceLocations("assets");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("index.html").setViewName("index");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("errors");
        return messageSource;
    }

    @Bean
    public DtoMapper dtoMapper(MessageSource messageSource) {
        return new DtoMapper(messageSource);
    }

    private Predicate<HttpMessageConverter<?>> jacksonConverters = converter -> converter instanceof AbstractJackson2HttpMessageConverter;
    private Function<HttpMessageConverter<?>, ObjectMapper> toJacksonMapper = converter -> ((AbstractJackson2HttpMessageConverter) converter).getObjectMapper();

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream()
                .filter(jacksonConverters)
                .map(toJacksonMapper)
                .forEach(objectMapper -> {
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                });
    }
}
