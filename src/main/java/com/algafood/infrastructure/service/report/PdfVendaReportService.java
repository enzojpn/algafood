package com.algafood.infrastructure.service.report;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.domain.filter.VendaDiariaFilter;
import com.algafood.domain.model.dto.VendaDiaria;
import com.algafood.domain.service.VendaQueryService;
import com.algafood.domain.service.VendaReportService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfVendaReportService implements VendaReportService {

	@Autowired
	private VendaQueryService vendaQueryService;

	@Override
	public byte[] consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		try {
			InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diaria.jasper");

			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

			List<VendaDiaria> vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vendasDiarias);

			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

			// new JREmptyDataSource()); para pdf sem datasource

			return JasperExportManager.exportReportToPdf(jasperPrint);

		} catch (Exception e) {
			throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
		}

	}

}
