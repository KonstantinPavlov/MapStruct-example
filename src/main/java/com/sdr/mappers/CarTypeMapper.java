package com.sdr.mappers;

import com.sdr.dto.CarTypeDTO;
import com.sdr.entity.CarType;
import org.mapstruct.Mapper;

/**
 * Created by Konstantin on 29.09.2017.
 */
@Mapper(componentModel = "spring")
public interface CarTypeMapper {

    CarTypeDTO carTypeToDTO(CarType carType);
}
