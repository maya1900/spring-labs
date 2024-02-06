package com.maya.core.web;

import com.maya.constants.ServiceExceptionEnum;
import com.maya.core.vo.CommonResult;
import com.maya.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.maya.controller")
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理Service异常
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(HttpServletRequest req, ServiceException exception) {
        logger.debug("[serviceExceptionHandler]", exception);
        return CommonResult.error(exception.getCode(), exception.getMessage());
    }

    /**
     * 处理 MissingServletRequestParameterException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException exception) {
        logger.debug("[missingServletRequestParameterExceptionHandler]", exception);
        return CommonResult.error(ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getCode(), exception.getMessage());
    }

    /**
     * 处理 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(HttpServletRequest req, Exception exception) {
        logger.error("[exceptionHandler]", exception);
        return CommonResult.error(ServiceExceptionEnum.SYS_ERROR.getCode(), exception.getMessage());
    }
}
