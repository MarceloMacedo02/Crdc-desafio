package br.com.areadigital.crdcdesafio.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import br.com.areadigital.crdcdesafio.dtos.CnabResponseDto;
import br.com.areadigital.crdcdesafio.entities.CnabDat;

@Mapper(componentModel = "spring")
@Component
public interface CnabMapper {
    default CnabResponseDto toDto(CnabDat cnabDat) {
        return new CnabResponseDto(cnabDat);
    }
}
