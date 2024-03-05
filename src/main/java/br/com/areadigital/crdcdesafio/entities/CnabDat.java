package br.com.areadigital.crdcdesafio.entities;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CnabDat implements Serializable{
    private static final long serialVersionUID = 1L;
	private HeaderRecord header;
    private List<TransactionRecord> transactions;
    private FooterRecord footer;
}
