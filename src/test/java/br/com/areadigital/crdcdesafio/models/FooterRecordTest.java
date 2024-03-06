package br.com.areadigital.crdcdesafio.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FooterRecordTest {

    @Mock
    private FooterRecord mockFooterRecord;

    @Test
    public void testConstructorAndGetters() {
        String recordType = "Type";
        String footerInfo = "Info";

        FooterRecord footerRecord = new FooterRecord(recordType, footerInfo);

        assertEquals(recordType, footerRecord.getRecordType());
        assertEquals(footerInfo, footerRecord.getFooterInfo());
    }

    @Test
    public void testBuilder() {
        String recordType = "Type";
        String footerInfo = "Info";

        FooterRecord footerRecord = FooterRecord.builder()
                .recordType(recordType)
                .footerInfo(footerInfo)
                .build();

        assertEquals(recordType, footerRecord.getRecordType());
        assertEquals(footerInfo, footerRecord.getFooterInfo());
    }

    @Test
    public void testMock() {
        String recordType = "Type";
        String footerInfo = "Info";

        when(mockFooterRecord.getRecordType()).thenReturn(recordType);
        when(mockFooterRecord.getFooterInfo()).thenReturn(footerInfo);

        assertEquals(recordType, mockFooterRecord.getRecordType());
        assertEquals(footerInfo, mockFooterRecord.getFooterInfo());
    }
}