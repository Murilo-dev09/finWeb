package com.finweb.finweb.service;

import com.finweb.finweb.model.transacao.DadosListagemTransacao;
import com.finweb.finweb.model.transacao.DadosResumoDashbord;
import com.finweb.finweb.model.transacao.Transacao;
import com.finweb.finweb.model.usuario.Usuario;
import com.finweb.finweb.repository.TransacaoRepository;
import com.finweb.finweb.repository.UsuarioRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class RelatorioService {

    TransacaoRepository transacaoRepository;
    TransacaoService transacaoService;

    public RelatorioService(TransacaoRepository transacaoRepository,  TransacaoService transacaoService) {
        this.transacaoRepository = transacaoRepository;
        this.transacaoService = transacaoService;
    }

    public byte[] gerarRelatorioTransacoes(Long usuarioId) {
        List<Transacao> transacoes = transacaoRepository.findAllByUsuarioIdOrderByDataDesc(usuarioId);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph titulo = new Paragraph("Relatório de Transações - FinWeb");
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" "));


            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            table.addCell(new PdfPCell(new Phrase("Descrição")));
            table.addCell(new PdfPCell(new Phrase("Categoria")));
            table.addCell(new PdfPCell(new Phrase("Valor (R$)")));
            table.addCell(new PdfPCell(new Phrase("Data")));
            table.addCell(new PdfPCell(new Phrase("Tipo da Transação")));

            for (Transacao t : transacoes) {
                table.addCell(t.getDescricao());
                table.addCell(t.getCategoria().name());
                table.addCell(formatadorMoeda.format(t.getValor()));
                table.addCell(t.getData().format(formatadorData));
                table.addCell(t.getTipoTransacao().name());
            }
            document.add(table);

            document.add(new Paragraph(" "));
            Paragraph resumo = new Paragraph("Resumo: ");
            resumo.setAlignment(Element.ALIGN_CENTER);

            PdfPTable tableResumo = new PdfPTable(3);

            tableResumo.addCell(new PdfPCell(new Phrase("Receitas (R$)")));
            tableResumo.addCell(new PdfPCell(new Phrase("Despesas (R$)")));
            tableResumo.addCell(new PdfPCell(new Phrase("Saldo Total (R$)")));

            var valores = transacaoService.resumoDashboard(usuarioId);
            tableResumo.addCell(formatadorMoeda.format(valores.totalReceitas()));
            tableResumo.addCell(formatadorMoeda.format(valores.totalDespesas()));
            tableResumo.addCell(formatadorMoeda.format(valores.saldo()));

            document.add(resumo);
            document.add(new Paragraph(" "));
            document.add(tableResumo);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o relatório em PDF", e);
        } finally {
            document.close();
        }
        return outputStream.toByteArray();
    }
}
