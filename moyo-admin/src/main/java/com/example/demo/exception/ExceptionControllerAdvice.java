//package com.example.demo.exception;
//
//import com.example.demo.error.BaseBusinessException;
//import com.example.demo.error.DefaultError;
//import com.example.demo.error.Response;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///**
// * @Auther: cch
// * @Date: 2020/9/21 14:07
// * @Description: 全局处理异常
// */
//@RestControllerAdvice
//public class ExceptionControllerAdvice {
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Response> APIExceptionHandler(Exception exception) {
//
//        Response response = new Response();
//
//        if (exception instanceof BaseBusinessException) {
//            BaseBusinessException bbe = (BaseBusinessException)exception;
//            response.setCode(bbe.getError().getErrorCode());
//            response.setData(bbe.getError().getErrorMessage());
//            if (exception.getMessage() != null) {
//                response.setData(exception.getMessage());
//            }
//        } else if (exception instanceof BindException) {
//            BindException bindException = (BindException)exception;
//            response.setCode(DefaultError.PARAMETER_ERROR.getErrorCode());
//            response.setData(DefaultError.PARAMETER_ERROR.getErrorMessage());
//            FieldError fieldError = bindException.getBindingResult().getFieldError();
//            response.setMessage(fieldError.getDefaultMessage());
//        }else{
//            exception.printStackTrace();
//            response.setCode(DefaultError.SYSTEM_INTERNAL_ERROR.getErrorCode());
//            response.setData(DefaultError.SYSTEM_INTERNAL_ERROR.getErrorMessage());
//        }
//        return new ResponseEntity (response, HttpStatus.LOOP_DETECTED);
//    }
//
//}
//
