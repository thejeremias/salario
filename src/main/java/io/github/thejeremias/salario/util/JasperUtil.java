package io.github.thejeremias.salario.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import io.github.thejeremias.salario.domain.PessoaSalarioConsolidado;
import io.github.thejeremias.salario.dto.LinhaRelatorio;

/**
 *  Classe utilitária para gerar um relatório JasperReport.
 */

public class JasperUtil {
	
	private JasperUtil() {
		throw new IllegalArgumentException("Classe utilitária");
	}

	public static void gerarRelatorio(List<LinhaRelatorio> dados, String caminho, String nomeArquivo, Map<String, Object> parametros, HttpServletResponse response) throws JRException, IOException  {
		InputStream inputStream = JasperUtil.class.getResourceAsStream(caminho);
		if (inputStream == null) {
			throw new IllegalArgumentException("Arquivo não encontrado.");
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(dados));
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=" + nomeArquivo + ".pdf");
		try (ServletOutputStream out = response.getOutputStream()) {
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		}
	}
    
}

