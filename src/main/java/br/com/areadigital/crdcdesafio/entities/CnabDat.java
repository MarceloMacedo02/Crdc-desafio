package br.com.areadigital.crdcdesafio.entities;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.areadigital.crdcdesafio.models.FooterRecord;
import br.com.areadigital.crdcdesafio.models.HeaderRecord;
import br.com.areadigital.crdcdesafio.models.TransactionRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cnab")
public class CnabDat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private HeaderRecord header;
    private List<TransactionRecord> transactions;
    private FooterRecord footer;
}
