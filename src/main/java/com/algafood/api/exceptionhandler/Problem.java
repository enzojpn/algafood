package com.algafood.api.exceptionhandler;
 
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problem {

	private Integer status;
	private String type;
	private String title;
	private String detail;

	private String userMessage;
	private OffsetDateTime  timestamp;
	private List<Field> fields;

	private Problem() {

	}

	public static class Field {

		private String name;
		private String userMessage;

		public Field() {

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public void setUserMessage(String userMessage) {
			this.userMessage = userMessage;
		}

		public static class Builder {

			private String name;
			private String userMessage;

			public Builder() {

			}

			public Builder name(String name) {
				this.name = name;
				return this;
			}

			public Builder userMessage(String userMessage) {
				this.userMessage = userMessage;
				return this;
			}

			public Field build() {
				Field field = new Field();
				field.name = this.name;
				field.userMessage = this.userMessage;

				return field;
			}
		}

	}

	public static class Builder {

		private Integer status;
		private String type;
		private String title;
		private String detail;
		private String userMessge;
		private OffsetDateTime timestamp;
		private List<Field> fields;

		public Builder() {

		}

		public Builder status(Integer status) {
			this.status = status;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}

		public Builder userMessage(String userMessage) {
			this.userMessge = userMessage;
			return this;
		}

		public Builder timestamp(OffsetDateTime timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder fields(List<Field> fields) {
			this.fields = fields;
			return this;
		}

		public Problem build() {
			Problem problem = new Problem();
			problem.status = this.status;
			problem.type = this.type;
			problem.title = this.title;
			problem.detail = this.detail;
			problem.userMessage = this.userMessge;
			problem.timestamp = this.timestamp;
			problem.fields = this.fields;
			return problem;
		}
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUserMessge() {
		return userMessage;
	}

	public void setUserMessge(String userMessge) {
		this.userMessage = userMessge;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}
