package br.com.areadigital.crdcdesafio.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import br.com.areadigital.crdcdesafio.dtos.CnabResponseDto;
import br.com.areadigital.crdcdesafio.entities.CnabDat;
import br.com.areadigital.crdcdesafio.mapper.CnabMapper;
import br.com.areadigital.crdcdesafio.service.CnabFileService;

@ExtendWith(MockitoExtension.class)
class CnabFileControllerTest {

    @Mock
    private CnabFileService cnabFileService;

    @Mock
    private CnabMapper cnabMapper;

    @InjectMocks
    private CnabFileController cnabFileController;

    @Test
    void testUploadFile() {
        // Mock the file
        MultipartFile mockFile = new MockMultipartFile("file", "test.txt", "text/plain",
                "Mock file content".getBytes());

        CnabDat mockCnabDat = new CnabDat();
        CnabResponseDto mockCnabResponseDto = new CnabResponseDto();

        when(cnabFileService.cnabFilePosicional(mockFile)).thenReturn(mockCnabDat);

        when(cnabMapper.toDto(mockCnabDat)).thenReturn(mockCnabResponseDto);

        ResponseEntity<CnabResponseDto> response = cnabFileController.uploadFile(mockFile);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCnabResponseDto, response.getBody());
    }
}