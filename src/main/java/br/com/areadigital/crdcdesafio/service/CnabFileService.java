package br.com.areadigital.crdcdesafio.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
//impot bigdecimal
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.areadigital.crdcdesafio.entities.CnabDat;
import br.com.areadigital.crdcdesafio.entities.FooterRecord;
import br.com.areadigital.crdcdesafio.entities.HeaderRecord;
import br.com.areadigital.crdcdesafio.entities.TransactionRecord;
import br.com.areadigital.crdcdesafio.exceptions.ErrorMessage;
import br.com.areadigital.crdcdesafio.exceptions.InvalidPosicionalFileException;
import br.com.areadigital.crdcdesafio.exceptions.InvalidPosicionalFormatException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CnabFileService {

    private final String MESSAGE_ERROR = "Erro ao processar o arquivo CNAB. Certifique-se de que o arquivo esteja no formato posicional correto.";

    public CnabDat parseCNABFileToJson(final MultipartFile cnabFile) throws InvocationTargetException {
        List<TransactionRecord> transactions = new ArrayList<>();
        HeaderRecord header = new HeaderRecord();
        FooterRecord footer = new FooterRecord();
        List<ErrorMessage> errors = new ArrayList<>();
        int numeroLinha = 1;

        try {
            String content = new String(cnabFile.getBytes());

            // Processar o conteúdo do arquivo
            String[] linhas = content.split("\n");
            for (String linha : linhas) {
                final String code = linha.substring(0, 3);
                System.out.println("linha:" + linha.length());
                switch (code) {
                    case "001":
                        // Processar cabeçalho
                        header = parseHeader(linha);
                        break;
                    case "002":
                        // Processar transação
                        final TransactionRecord transaction = parseTransaction(linha, numeroLinha, errors);
                        numeroLinha++;
                        transactions.add(transaction);
                        break;
                    case "003":
                        // Processar rodapé
                        footer = parseFooter(linha);
                        break;
                    default:
                        log.error("error:", MESSAGE_ERROR);
                        throw new InvalidPosicionalFileException("error", MESSAGE_ERROR);

                }
            }

        } catch (final IOException exception) {
            log.error("error:", MESSAGE_ERROR);
            throw new InvalidPosicionalFileException("error", MESSAGE_ERROR);

        }
        if (errors.size() > 0) {
            log.error("error:", "O arquivo CNAB possui formato inválido.");
            throw new InvalidPosicionalFormatException("error", "O arquivo CNAB possui formato inválido.", errors);
        }
        final CnabDat cnabDat = new CnabDat(header, transactions, footer);
        return cnabDat;
    }

    private HeaderRecord parseHeader(final String linha) {
        String razaoSocial = linha.substring(3, 34).trim();
        String identificadorEmpresa = linha.substring(34, 48).trim();

        return new HeaderRecord("001", razaoSocial, identificadorEmpresa);
    }

    private TransactionRecord parseTransaction(final String linha, int numeroLinha, List<ErrorMessage> errors) {
        System.out.println(linha.length());

        String code = linha.substring(0, 3);
        String tipoTransacao = linha.substring(3, 4);
        BigDecimal valor = new BigDecimal("0.00");
        ;
        try {
            valor = new BigDecimal(linha.substring(4, 13)).movePointLeft(2);
        } catch (Exception e) {
            errors.add(new ErrorMessage(numeroLinha, "Valor da transação está fora do formato válido."));
        }
        String contaOrigem = linha.substring(20, 36).trim();
        String contaDestino = linha.substring(36, 52).trim();

        if (!tipoTransacao.equals("C") && !tipoTransacao.equals("D") && !tipoTransacao.equals("T")) {
            errors.add(new ErrorMessage(numeroLinha, "Tipo de transação inválido."));

        }

        BigDecimal zero = BigDecimal.ZERO;
        if (valor.compareTo(zero) <= 0) {
            log.error("error:", "Valor da transação está fora do formato válido.");
            errors.add(new ErrorMessage(numeroLinha, " Valor da transação não pode ser nulo"));

        }

        if (contaOrigem.isEmpty())

        {

            log.error("error:", "Conta origem é obrigatória.");
            errors.add(new ErrorMessage(numeroLinha, "Conta origem é obrigatória."));
        }

        if (contaDestino.isEmpty()) {

            log.error("error:", "Conta destino é obrigatória.");
            errors.add(new ErrorMessage(numeroLinha, "Conta destino é obrigatória."));
        }

        return new TransactionRecord(code, tipoTransacao, valor, contaOrigem, contaDestino);
    }

    private FooterRecord parseFooter(final String linha) {
        return new FooterRecord("003", linha.substring(3));
    }

}