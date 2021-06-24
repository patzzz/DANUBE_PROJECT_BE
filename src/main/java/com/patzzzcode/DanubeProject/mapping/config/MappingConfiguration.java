package com.patzzzcode.DanubeProject.mapping.config;

import com.patzzzcode.DanubeProject.mapping.CustomConverter;
import com.patzzzcode.DanubeProject.mapping.CustomMapping;

import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MappingConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        applicationContext.getBeansWithAnnotation(CustomMapping.class).values().stream()
                .filter(o -> o instanceof PropertyMap).forEach(o -> modelMapper.addMappings((PropertyMap) o));

        applicationContext.getBeansWithAnnotation(CustomConverter.class).values().stream()
                .filter(o -> o instanceof Converter).forEach(o -> modelMapper.addConverter((Converter) o));

        return modelMapper;
    }
}
