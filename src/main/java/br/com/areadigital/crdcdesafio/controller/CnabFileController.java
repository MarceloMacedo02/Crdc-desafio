package br.com.areadigital.crdcdesafio.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.areadigital.crdcdesafio.dtos.CnabResponseDto;
import br.com.areadigital.crdcdesafio.entities.CnabDat;
import br.com.areadigital.crdcdesafio.mapper.CnabMapper;
import br.com.areadigital.crdcdesafio.service.CnabFileService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cnab-file")
public class CnabFileController {
    private final CnabFileService cnabFileService;

    private final CnabMapper cnabMapper;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CnabResponseDto> uploadFile(@RequestPart("file") MultipartFile file) {

        final CnabDat cnabDat = cnabFileService.cnabFilePosicional(file);
        CnabResponseDto cnabResponseDto = cnabMapper.toDto(cnabDat);
        return ResponseEntity.ok().body(cnabResponseDto);
    }

}
