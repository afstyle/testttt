package com.huanghao.server.util;

import com.huanghao.server.vo.commons.ResultVO;

/**
 * @author HuangHao
 * @date 2020/8/16 17:11
 */
public class ResultVOUtil {

    public static ResultVO success(Object object, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMessage(msg);
        resultVO.setResult(object);
        return resultVO;
    }

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMessage("ok");
        resultVO.setResult(object);
        return resultVO;
    }

    public static ResultVO success(String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMessage(msg);
        return resultVO;
    }


    public static ResultVO success() {
        return success("ok");
    }

    public static ResultVO error(String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(400);
        resultVO.setSuccess(false);
        resultVO.setMessage(msg);
        return resultVO;
    }

    public static ResultVO validatorError(String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(401);
        resultVO.setSuccess(false);
        resultVO.setMessage(msg);
        return resultVO;
    }

}
