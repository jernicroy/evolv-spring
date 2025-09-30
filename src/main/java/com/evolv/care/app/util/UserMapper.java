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

    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "shortName", target = "shortName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "hashCode", target = "hashCode")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "token", target = "token")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    UserInfo toDto(UserEntity entity);

    UserEntity toEntity(UserInfo dto);
}
