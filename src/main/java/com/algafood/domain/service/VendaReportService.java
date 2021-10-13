package com.algafood.domain.service;

import com.algafood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	byte[] consultarVendasDiarias(VendaDiariaFilter filtro , String timeOffset);
}
