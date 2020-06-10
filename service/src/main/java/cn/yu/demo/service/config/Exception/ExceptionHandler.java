package cn.yu.demo.service.config.Exception;

import cn.yu.demo.service.config.i18n.I18nUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * exception handler
 *
 * @author bin.yu
 * @create 2020-02-23 15:25
 * @info best wishes no bug
 **/
public class ExceptionHandler {

    public static void publish(String errorCode) throws BusiException {
        publish(errorCode, "", null, null);
    }

    public static void publish(String errorCode, String errorMsg) throws BusiException {
        publish(errorCode, errorMsg, null, null);
    }

    public static void publish(String errorCode, String errorMsg, String... params) throws BusiException {
        publish(errorCode, errorMsg, null, params);
    }

    public static void publish(String errorCode, String errorMsg, Throwable t) throws BusiException {
        publish(errorCode, errorMsg, t, null);
    }

    public static void publish(String errorCode, String errorMsg, Throwable t, Object[] params) throws BusiException {
        BusiException be;
        if ((t != null) && (t instanceof BusiException)) {
            be = (BusiException) t;
        }
        else {
            String i18nErrorMsg = I18nUtils.getMessage(errorCode, params);
            if (StringUtils.isNotBlank(i18nErrorMsg) && !i18nErrorMsg.equals(errorCode)) {
                errorMsg = i18nErrorMsg;
            }
            be = new BusiException(errorCode, errorMsg, t);
        }
        throw be;
    }
}
