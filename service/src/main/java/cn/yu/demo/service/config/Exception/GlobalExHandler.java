package cn.yu.demo.service.config.Exception;

import cn.yu.demo.client.model.ResponseMsg;
import cn.yu.demo.service.config.i18n.I18nUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * global ex handler
 * 统一异常处理
 * @author bin.yu
 * @create 2020-02-23 15:40
 * @info best wishes no bug
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExHandler {

    /**
     * 专门处理业务异常
     *
     * @return
     */
    @ExceptionHandler(BusiException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public <T> ResponseMsg<T> handleAllException(BusiException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return ResponseMsg.buildFail(e.getErrorCode(), e.getMessage());
    }

    /**
     * session expired
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SessionException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public <T> ResponseMsg<T> handleSessionExpiredException(SessionException e, HttpServletRequest request) {
        log.error(e.getErrorMsg(), e);
        return ResponseMsg.buildFail(e.getErrorCode(), e.getMessage());
    }

    /**
     * 处理未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> ResponseMsg<T> throwable(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return ResponseMsg.buildFail("-1", "系统异常，请联系管理员！");
    }


    /**
     * 处理参数校验异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMsg methodArgumentNotValidExceptionHandler(Exception ex, HttpServletRequest request) {
        String msgCode = "PARAM CHECK ERROR";
        String msgDesc = "";
        // 参数校验异常
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException mve = (MethodArgumentNotValidException) ex;
            BindingResult bindingResult = mve.getBindingResult();
//            List<String> messageList = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            Set<String> fieldName = bindingResult.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toSet());
            List<String> globalizationMessage = fieldName.stream().map(I18nUtils::getMessage).collect(Collectors.toList());
            msgDesc = String.join(";", globalizationMessage);
        }
        // validation 捕获的参数校验异常
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) ex;
            List<String> messageList = cve.getConstraintViolations().stream().map(ee -> ee.getMessage()).collect(Collectors.toList());
            msgDesc = messageList.stream().map(I18nUtils::getMessage).collect(Collectors.joining(";"));
        }
        return ResponseMsg.buildFail(msgCode, msgDesc);
    }

}
