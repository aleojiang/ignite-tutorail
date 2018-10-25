package pjiang.tutorial.datajpa.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pjiang.tutorial.datajpa.dto.UserDTO;
import pjiang.tutorial.datajpa.repository.entity.UserEntity;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  13:35 2018/10/24.
 */
@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
    
        TypeMap<UserDTO, UserEntity> typeMap = modelMapper.createTypeMap(UserDTO.class, UserEntity.class);
        typeMap.addMapping(UserDTO::getFirstName, UserEntity::setFirstName);
    
        return modelMapper;
    }
}
