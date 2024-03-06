package br.com.areadigital.crdcdesafio.models;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private String recordType;
    private String transactionType;
    private BigDecimal transactionValue;
    private String sourceAccount;
    private String destinationAccount;
}
