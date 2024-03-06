package br.com.areadigital.crdcdesafio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import br.com.areadigital.crdcdesafio.entities.CnabDat;
import br.com.areadigital.crdcdesafio.repository.CnabDatRepository;

@ExtendWith(MockitoExtension.class)
class CnabFileServiceTest {

    @Mock
    private CnabDatRepository cnabDatRepository;

    @InjectMocks
    private CnabFileService cnabFileService;

    private MultipartFile cnabFile;

    @BeforeEach
    void setUp() {
        // Criar um arquivo CNAB fictício para os testes
        String content = "001Empresa A              1900010000010000          Empresa A                \n" +
                "002C10000000010000000001234560000012345                                  \n" +
                "002D200000000200000000012345612345600                                  \n" +
                "002T300000000300000000012345623456789                                  \n" +
                "003";
        cnabFile = new MockMultipartFile("cnabFile", "cnab.dat", "text/plain", content.getBytes());
    }

    @Test
    void testCnabFilePosicional_Success() {
        // Mock do comportamento do repositório
        when(cnabDatRepository.findByHeaderCompanyId(anyString())).thenReturn(Optional.empty());
        when(cnabDatRepository.save(any())).thenReturn(new CnabDat());

        // Executar o método de teste
        CnabDat result = cnabFileService.cnabFilePosicional(cnabFile);

        // Verificar se o método validarCompany foi chamado	
        verify(cnabDatRepository, times(1)).findByHeaderCompanyId(anyString());
        verify(cnabDatRepository, times(1)).save(any());

        // Verificar se o resultado não é nulo
        assertNotNull(result);
        // Verificar se o cabeçalho foi definido corretamente
        assertEquals("Empresa A", result.getHeader().getCompanyName());
        assertEquals("1900010000010000", result.getHeader().getCompanyId());
        // Verificar se as transações foram adicionadas corretamente
        assertEquals(3, result.getTransactions().size());
        // Verificar se o rodapé foi definido corretamente
        assertEquals("003", result.getFooter().getRecordType());
    }

	@Test
	void testCnabFilePosicional_SaveError() {
		String content = "001Empresa B     1900010000020000          Empresa B                \n"
				+ "002C10000000010000000001234560000012345                                  \n"
				+ "002D200000000200000000012345612345600                                  \n"
				+ "002T300000000300000000012345623456789                                  \n" + "003XPTO";
		cnabFile = new MockMultipartFile("cnabFile", "cnab.dat", "text/plain", content.getBytes());
		when(cnabDatRepository.findByHeaderCompanyId(anyString())).thenReturn(Optional.empty());
		when(cnabDatRepository.save(any())).thenThrow(new RuntimeException("Erro ao salvar no repositório"));

		assertThrows(RuntimeException.class, () -> cnabFileService.cnabFilePosicional(cnabFile));

		verify(cnabDatRepository, times(1)).findByHeaderCompanyId(anyString());
		verify(cnabDatRepository, times(1)).save(any());
	}

}