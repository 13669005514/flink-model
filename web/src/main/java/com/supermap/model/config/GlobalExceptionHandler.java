package com.supermap.model.config;
import com.supermap.model.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhangfx 2020/1/15 18:01
 * @version : 1.0
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**-------- 通用异常处理方法 --------**/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().data("info",e.getMessage());
    }

    /**-------- 空指针 --------**/
    @ExceptionHandler({NullPointerException.class})
    @ResponseBody
    public R error(NullPointerException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.NULL_POINT).data("info",e.getMessage());
    }

    /**
     * spring validator 方法参数验证异常拦截
     * @param e 绑定验证异常
     * @return 错误返回消息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R defaultErrorHandler(MethodArgumentNotValidException e) {
        BindingResult validResult = e.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        Map<String,Object> messages = new HashMap<>();
        for (ObjectError error : validResult.getAllErrors()) {
            FieldError fieldError = (FieldError) error;
            messages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.info("参数校验异常:{}",messages.toString());
        return R.setResult(ResultCodeEnum.PARAM_ERROR).data("info",messages);
    }

    /**-------- 操作数据库异常 --------**/
    @ExceptionHandler({DataAccessException.class})
    @ResponseBody
    public R error(SQLException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.SQL_ERROR).data("info",e.getMessage());
    }


    /**-------- 文件未找到 --------**/
    @ExceptionHandler({FileNotFoundException.class})
    @ResponseBody
    public R error(FileNotFoundException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.FILE_ERROR).data("info",e.getMessage());
    }



}
