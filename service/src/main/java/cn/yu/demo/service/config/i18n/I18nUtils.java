package cn.yu.demo.service.config.i18n;

import cn.yu.demo.service.common.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 国际化工具类
 *
 * @author bin.yu
 * @create 2019-03-27 17:15
 */
@Slf4j
public class I18nUtils {


    public I18nUtils() {
    }

    public static String getMessage(String errorCode) {
        String localeMessage = "";

        try {
            localeMessage = getMessage(errorCode, (Object[]) null);
        }
        catch (Exception var3) {
            log.error("No code named " + errorCode + " found in I18n file");
        }

        return localeMessage;
    }

    public static String getMessage(String errorCode, Object[] args) {
        String localeMessage = "";

        try {
            MessageSource messageSource = SpringContextUtils.getBean(ResourceBundleMessageSource.class);
            localeMessage = messageSource.getMessage(errorCode, args, LocaleContextHolder.getLocale());
        }
        catch (Exception var4) {
            log.error("No code named " + errorCode + " found in I18n file");
        }

        return localeMessage;
    }
}
