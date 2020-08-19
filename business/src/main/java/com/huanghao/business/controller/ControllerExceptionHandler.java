package com.huanghao.business.controller;

import com.huanghao.server.exception.ValidatorException;
import com.huanghao.server.util.ResultVOUtil;
import com.huanghao.server.vo.commons.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author HuangHao
 * @date 2020/8/19 22:42
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResultVO validatorExceptionHandler(ValidatorException exception) {
        LOG.warn(exception.getMessage());
        return ResultVOUtil.validatorError("请求参数异常！");
    }



}
