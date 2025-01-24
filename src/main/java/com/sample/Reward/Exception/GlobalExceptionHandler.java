package com.sample.Reward.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sample.Reward.dto.BaseResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RewardException.class)
	public ResponseEntity<BaseResponseDTO> rewardExceptionsHandler(RewardException exception){
		
		BaseResponseDTO exceptionDTO = new BaseResponseDTO();
		exceptionDTO.setMessage(exception.getMessage());
		return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
		
	}

}
