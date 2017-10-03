package com.sdr.mappers;

import com.sdr.dto.CarDTO;
import com.sdr.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Konstantin on 29.09.2017.
 */
@Mapper(uses = {PersonMapper.class, CarTypeMapper.class, ModelMapper.class}, componentModel = "spring")
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDTO carToDTO(Car car);
}
