package br.com.areadigital.crdcdesafio.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HeaderRecordTest {

    @Mock
    private HeaderRecord mockHeaderRecord;

    @Test
    public void testConstructorAndGetters() {
        String recordType = "Type";
        String companyName = "Company";
        String companyId = "123";
        String resever = "Reserve";

        HeaderRecord headerRecord = new HeaderRecord(recordType, companyName, companyId, resever);

        assertEquals(recordType, headerRecord.getRecordType());
        assertEquals(companyName, headerRecord.getCompanyName());
        assertEquals(companyId, headerRecord.getCompanyId());
        assertEquals(resever, headerRecord.getResever());
    }

    @Test
    public void testBuilder() {
        String recordType = "Type";
        String companyName = "Company";
        String companyId = "123";
        String resever = "Reserve";

        HeaderRecord headerRecord = HeaderRecord.builder()
                .recordType(recordType)
                .companyName(companyName)
                .companyId(companyId)
                .resever(resever)
                .build();

        assertEquals(recordType, headerRecord.getRecordType());
        assertEquals(companyName, headerRecord.getCompanyName());
        assertEquals(companyId, headerRecord.getCompanyId());
        assertEquals(resever, headerRecord.getResever());
    }

    @Test
    public void testMock() {
        String recordType = "Type";
        String companyName = "Company";
        String companyId = "123";
        String resever = "Reserve";

        when(mockHeaderRecord.getRecordType()).thenReturn(recordType);
        when(mockHeaderRecord.getCompanyName()).thenReturn(companyName);
        when(mockHeaderRecord.getCompanyId()).thenReturn(companyId);
        when(mockHeaderRecord.getResever()).thenReturn(resever);

        assertEquals(recordType, mockHeaderRecord.getRecordType());
        assertEquals(companyName, mockHeaderRecord.getCompanyName());
        assertEquals(companyId, mockHeaderRecord.getCompanyId());
        assertEquals(resever, mockHeaderRecord.getResever());
    }
}