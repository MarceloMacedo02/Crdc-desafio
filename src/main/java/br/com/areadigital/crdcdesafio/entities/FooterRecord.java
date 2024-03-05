package br.com.areadigital.crdcdesafio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FooterRecord {
    private String recordType;
    private String footerInfo;

}
