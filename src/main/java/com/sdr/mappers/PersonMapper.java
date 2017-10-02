package com.sdr.mappers;

import com.sdr.dto.PersonDTO;
import com.sdr.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created by Konstantin on 29.09.2017.
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "gender.id", target = "gender")
    PersonDTO personToDTO(Person person);


}
