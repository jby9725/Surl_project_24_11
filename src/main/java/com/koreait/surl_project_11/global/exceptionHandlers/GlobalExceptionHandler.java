package com.koreait.surl_project_11.global.exceptionHandlers;

import com.koreait.surl_project_11.global.exceptions.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 어떤 오류가 발생하던 그걸 낚아채서 어디서든 발동하는 것.
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    // 범위가 넓음.
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception exception) {
        log.debug("handleException 1");
        return exception.getMessage();
    }

    // 글로벌 예외만. 글로벌 상황 에서는 이 함수가 우선으로 실행 된다.
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public String handleException(GlobalException exception) {
        log.debug("handleException 2");
        return exception.getMessage();
    }

}
