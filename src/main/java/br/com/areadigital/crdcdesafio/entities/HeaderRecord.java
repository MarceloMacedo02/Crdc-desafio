package br.com.areadigital.crdcdesafio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeaderRecord {
    private String recordType;
    private String companyName;
    private String companyId;
}
