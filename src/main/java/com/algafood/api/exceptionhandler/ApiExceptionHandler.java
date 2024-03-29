package com.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
			+ "o problema persistir, entre em contato com o administrador do sistema.";

	@Autowired
	private MessageSource messageSource;
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status).headers(headers).build();
	}
	 
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O recurso '%s' que tentou acessar, é inexistente", ex.getRequestURL());

		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		BindingResult bindingResult = ex.getBindingResult();

		
		
		List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
				.map(fieldError ->{
				String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
				return new Problem.Field.Builder().name(fieldError.getField()).userMessage(message).build();
				})
				
				.collect(Collectors.toList());

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).fields(problemFields).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		if (rootCause instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) rootCause, headers, status,
					request);
		}

		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		String detail = String.format(
				"O parâmetro de url '%s' recebeu o valor '%s', que é de um tipo inválido. "
						+ "Corrija e informe um valor compatível do tipo '%s'.",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();

		return handleExceptionInternal(ex, problem, headers, status, request);

	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String path = joinPath(ex.getPath());

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format(
				"A propriedade '%s' recebeu o valor '%s', "
						+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath());
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String
				.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente", path);
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private String joinPath(List<Reference> references) {
		return references.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradoException(EntidadeNaoEncontradaException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		String detail = ex.getMessage();
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioExpception(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.NEGOCIO_EXCEPTION;
		String detail = ex.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleUncaugth(Exception ex, WebRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		String detail = "Ocorreu erro interno inesperado de sistema. Tente novamente e se o problema persistir, entre em contato.";
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = new Problem.Builder().title(status.getReasonPhrase()).status(status.value())
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		} else if (body instanceof String) {
			body = new Problem.Builder().title(status.getReasonPhrase()).status(status.value())
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problem.Builder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

		return new Problem.Builder().status(status.value()).type(problemType.getUri()).title(problemType.getTitle())
				.detail(detail).timestamp(OffsetDateTime.now());
	}

}
