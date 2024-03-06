package br.com.areadigital.crdcdesafio.dtos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.areadigital.crdcdesafio.entities.CnabDat;

@ExtendWith(MockitoExtension.class)
class CnabResponseDtoTest {

    @Mock
    private CnabDat cnabDat;

    @InjectMocks
    private CnabResponseDto cnabResponseDto;

    @Test
    void testCnabResponseDtoConstructor() {
        CnabResponseDto response = new CnabResponseDto(cnabDat);

        assertEquals("success", response.getStatus());
        assertEquals("Arquivo CNAB enviado e processado com sucesso.", response.getMessage());

    }
}