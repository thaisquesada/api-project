package br.com.tdd.api.exceptioncontroller;

import lombok.Getter;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StandardError {
	
	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String path;

}