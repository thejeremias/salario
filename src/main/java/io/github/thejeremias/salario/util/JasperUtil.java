package io.github.thejeremias.salario.util;

import java.io.InputStream;
import java.util.List;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Map;


import io.github.thejeremias.salario.dto.LinhaRelatorioDto;

/**
 *  Classe utilitária para gerar um relatório JasperReport.
 */

public class JasperUtil {
	
	private JasperUtil() {
		throw new IllegalArgumentException("Classe utilitária");
	}

	public static JasperPrint gerarRelatorio(List<LinhaRelatorioDto> dados, String caminho, Map<String, Object> parametros) throws JRException  {
		InputStream inputStream = JasperUtil.class.getResourceAsStream(caminho);
		if (inputStream == null) {
			throw new IllegalArgumentException("Arquivo não encontrado.");
		}
		return JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(dados));
	}
    
}

