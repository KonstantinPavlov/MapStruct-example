package com.sdr.mappers;

import com.sdr.dto.ModelDTO;
import com.sdr.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by Konstantin on 02.10.2017.
 */
@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mapping(source = "company.id", target = "company")
    ModelDTO modelToDTO(Model model);
}
