package com.evolv.care.app.util;


import com.evolv.care.app.dto.UserInfo;
import com.evolv.care.app.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


/**
 * UserMapper - To map the Entity object to DTO Object dynamically.
 * Also, it allows injection as a Spring bean
 *
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserInfo toDto(UserEntity entity);

    UserEntity toEntity(UserInfo dto);
}
