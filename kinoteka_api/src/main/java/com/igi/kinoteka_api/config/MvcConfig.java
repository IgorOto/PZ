package com.igi.kinoteka_api.config;

import com.igi.kinoteka_api.converter.StringToLocalDateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public StringToLocalDateTimeConverter stringToLocalDateTimeConverter() {
        return new StringToLocalDateTimeConverter();
    }

//    @Bean
//    public Jackson2ObjectMapperBuilder jacksonBuilder() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.deserializerByType(FreshShowing.class, new FreshShowingDeserializer(stringToLocalDateTimeConverter()));
//        return builder;
//    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToLocalDateTimeConverter());
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        //builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
//        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
//        //converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(false).build()));
//    }
}
