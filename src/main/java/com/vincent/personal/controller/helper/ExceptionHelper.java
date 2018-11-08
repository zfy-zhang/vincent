package com.vincent.personal.controller.helper;

import com.vincent.personal.exception.TipException;
import com.vincent.personal.modal.bo.RestResponseBo;
import org.slf4j.Logger;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/7
 */
public class ExceptionHelper {

    /**
     * 统一处理异常
     *
     * @param logger
     * @param msg
     * @param e
     * @return
     */
    public static RestResponseBo handlerException(Logger logger, String msg, Exception e) {
        if (e instanceof TipException) {
            msg = e.getMessage();
        } else {
            logger.error(msg, e);
        }
        return RestResponseBo.fail(msg);
    }

}
