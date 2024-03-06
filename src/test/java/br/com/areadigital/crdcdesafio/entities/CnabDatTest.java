package br.com.areadigital.crdcdesafio.entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.areadigital.crdcdesafio.models.FooterRecord;
import br.com.areadigital.crdcdesafio.models.HeaderRecord;
import br.com.areadigital.crdcdesafio.models.TransactionRecord;

@ExtendWith(MockitoExtension.class)
public class CnabDatTest {

    @Mock
    private CnabDat mockCnabDat;

    @Test
    public void testConstructorAndGetters() {
        String id = "123";
        HeaderRecord header = new HeaderRecord("Type", "Company", "123", "Reserve");
        List<TransactionRecord> transactions = new ArrayList<>();
        transactions.add(new TransactionRecord("Type", "Type", BigDecimal.TEN, "Source", "Destination"));
        FooterRecord footer = new FooterRecord("Type", "Info");

        CnabDat cnabDat = new CnabDat(id, header, transactions, footer);

        assertEquals(id, cnabDat.getId());
        assertEquals(header, cnabDat.getHeader());
        assertEquals(transactions, cnabDat.getTransactions());
        assertEquals(footer, cnabDat.getFooter());
    }

    @Test
    public void testBuilder() {
        String id = "123";
        HeaderRecord header = new HeaderRecord("Type", "Company", "123", "Reserve");
        List<TransactionRecord> transactions = new ArrayList<>();
        transactions.add(new TransactionRecord("Type", "Type", BigDecimal.TEN, "Source", "Destination"));
        FooterRecord footer = new FooterRecord("Type", "Info");

        CnabDat cnabDat = CnabDat.builder()
                .id(id)
                .header(header)
                .transactions(transactions)
                .footer(footer)
                .build();

        assertEquals(id, cnabDat.getId());
        assertEquals(header, cnabDat.getHeader());
        assertEquals(transactions, cnabDat.getTransactions());
        assertEquals(footer, cnabDat.getFooter());
    }

    @Test
    public void testMock() {
        String id = "123";
        HeaderRecord header = new HeaderRecord("Type", "Company", "123", "Reserve");
        List<TransactionRecord> transactions = new ArrayList<>();
        transactions.add(new TransactionRecord("Type", "Type", BigDecimal.TEN, "Source", "Destination"));
        FooterRecord footer = new FooterRecord("Type", "Info");

        when(mockCnabDat.getId()).thenReturn(id);
        when(mockCnabDat.getHeader()).thenReturn(header);
        when(mockCnabDat.getTransactions()).thenReturn(transactions);
        when(mockCnabDat.getFooter()).thenReturn(footer);

        assertEquals(id, mockCnabDat.getId());
        assertEquals(header, mockCnabDat.getHeader());
        assertEquals(transactions, mockCnabDat.getTransactions());
        assertEquals(footer, mockCnabDat.getFooter());
    }
}