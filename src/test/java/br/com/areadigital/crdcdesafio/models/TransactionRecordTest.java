package br.com.areadigital.crdcdesafio.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionRecordTest {

    @Mock
    private TransactionRecord mockTransactionRecord;

    @Test
    public void testConstructorAndGetters() {
        String recordType = "Type";
        String transactionType = "Type";
        BigDecimal transactionValue = BigDecimal.TEN;
        String sourceAccount = "Source";
        String destinationAccount = "Destination";

        TransactionRecord transactionRecord = new TransactionRecord(recordType, transactionType, transactionValue, sourceAccount, destinationAccount);

        assertEquals(recordType, transactionRecord.getRecordType());
        assertEquals(transactionType, transactionRecord.getTransactionType());
        assertEquals(transactionValue, transactionRecord.getTransactionValue());
        assertEquals(sourceAccount, transactionRecord.getSourceAccount());
        assertEquals(destinationAccount, transactionRecord.getDestinationAccount());
    }

    @Test
    public void testBuilder() {
        String recordType = "Type";
        String transactionType = "Type";
        BigDecimal transactionValue = BigDecimal.TEN;
        String sourceAccount = "Source";
        String destinationAccount = "Destination";

        TransactionRecord transactionRecord = TransactionRecord.builder()
                .recordType(recordType)
                .transactionType(transactionType)
                .transactionValue(transactionValue)
                .sourceAccount(sourceAccount)
                .destinationAccount(destinationAccount)
                .build();

        assertEquals(recordType, transactionRecord.getRecordType());
        assertEquals(transactionType, transactionRecord.getTransactionType());
        assertEquals(transactionValue, transactionRecord.getTransactionValue());
        assertEquals(sourceAccount, transactionRecord.getSourceAccount());
        assertEquals(destinationAccount, transactionRecord.getDestinationAccount());
    }

    @Test
    public void testMock() {
        String recordType = "Type";
        String transactionType = "Type";
        BigDecimal transactionValue = BigDecimal.TEN;
        String sourceAccount = "Source";
        String destinationAccount = "Destination";

        when(mockTransactionRecord.getRecordType()).thenReturn(recordType);
        when(mockTransactionRecord.getTransactionType()).thenReturn(transactionType);
        when(mockTransactionRecord.getTransactionValue()).thenReturn(transactionValue);
        when(mockTransactionRecord.getSourceAccount()).thenReturn(sourceAccount);
        when(mockTransactionRecord.getDestinationAccount()).thenReturn(destinationAccount);

        assertEquals(recordType, mockTransactionRecord.getRecordType());
        assertEquals(transactionType, mockTransactionRecord.getTransactionType());
        assertEquals(transactionValue, mockTransactionRecord.getTransactionValue());
        assertEquals(sourceAccount, mockTransactionRecord.getSourceAccount());
        assertEquals(destinationAccount, mockTransactionRecord.getDestinationAccount());
    }
}