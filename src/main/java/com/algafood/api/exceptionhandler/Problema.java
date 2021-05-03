package com.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

public class Problema {
	private LocalDateTime dataHora;
	private String mensagem;

	private Problema() {

	}

	public static class Builder {
		private LocalDateTime dataHora;
		private String mensagem;

		public Builder() {

		}

		public Builder dataHora(LocalDateTime dataHora) {
			this.dataHora = dataHora;
			return this;
		}

		public Builder mensagem(String mensagem) {
			this.mensagem = mensagem;
			return this;
		}

		public Problema build() {
			Problema problema = new Problema();
			problema.dataHora = this.dataHora;
			problema.mensagem = this.mensagem;
			return problema;
		}
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
