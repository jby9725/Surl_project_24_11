package com.koreait.surl_project_11.global.exceptionHandlers;

import com.koreait.surl_project_11.global.exceptions.GlobalException;
import com.koreait.surl_project_11.global.rq.Rq;
import com.koreait.surl_project_11.global.rsData.RsData;
import com.koreait.surl_project_11.standard.dto.Empty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

// 어떤 오류가 발생하던 그걸 낚아채서 어디서든 발동하는 것.
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private Rq rq;

    //    // 범위가 넓음.
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public String handleException(Exception exception) {
//        log.debug("handleException 1");
//        return exception.getMessage();
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("resultCode", "500-1");
        body.put("statusCode", 500);
        body.put("msg", ex.getLocalizedMessage());
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        body.put("data", data);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        data.put("trace", sw.toString().replace("\t", "    ").split("\\r\\n"));
        String path = rq.getCurrentUrlPath();
        data.put("path", path);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 글로벌 예외만. 글로벌 상황 에서는 이 함수가 우선으로 실행 된다.
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    // ResponseEntity<String> : 상황에 따라서 다른 상태코드 지정 가능
    public ResponseEntity<RsData<Empty>> handleException(GlobalException ex) {

        RsData<Empty> rsData = ex.getRsData();

        return ResponseEntity
                .status(rsData.getStatusCode())
                .body(rsData);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<RsData<Empty>> handleException(MethodArgumentNotValidException ex) {
        String resultCode = "400-" + ex.getBindingResult().getFieldError().getCode();
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return handleException(
                new GlobalException(
                        resultCode,
                        msg
                )
        );
    }
}
