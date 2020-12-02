package ru.fwoods.computerstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${manufacturer.upload.path}")
    private String manufacturerUploadPath;

    @Value("${promotion.upload.path}")
    private String promotionUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/image/manufacturerLogo/**")
                .addResourceLocations("file://" + manufacturerUploadPath + "/");
        registry.addResourceHandler("/image/promotionBanner/**")
                .addResourceLocations("file://" + promotionUploadPath + "/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(jsonConverter);
    }
}
