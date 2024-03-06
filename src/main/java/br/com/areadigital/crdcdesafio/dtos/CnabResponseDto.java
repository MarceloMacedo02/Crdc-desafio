package br.com.areadigital.crdcdesafio.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.areadigital.crdcdesafio.entities.CnabDat;
import br.com.areadigital.crdcdesafio.models.TransactionRecord;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class CnabResponseDto {
    private String status;
    private String message;
    private Data data = new Data();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Data {
        private List<Transaction> transactions = new ArrayList<>();

        public Data(List<TransactionRecord> transactionsRecords) {
            this.transactions = transactionsRecords.stream().map(Transaction::new).collect(Collectors.toList());
        }

        @Getter
        @Setter
        public static class Transaction {
            private String type;
            private BigDecimal value;
            private String accountOrigin;
            private String accountDestination;

            public Transaction(TransactionRecord transaction) {
                this.type = transaction.getRecordType();
                this.value = transaction.getTransactionValue();
                this.accountOrigin = transaction.getSourceAccount();
                this.accountDestination = transaction.getDestinationAccount();
            }

        }

    }

    public CnabResponseDto(CnabDat cnabDat) {
        this.status = "success";
        this.message = "Arquivo CNAB enviado e processado com sucesso.";
        this.data.transactions = new Data(cnabDat.getTransactions()).getTransactions();
    }
}
