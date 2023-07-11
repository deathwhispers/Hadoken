package pers.guangjian.hadoken.connector.core.exception;

import pers.guangjian.hadoken.connector.core.utils.LocaleUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

/**
 * 支持国际化消息的异常,code为
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:54
 */
@Getter
@Setter(AccessLevel.PROTECTED)
public class I18nSupportException extends RuntimeException {

    /**
     * 消息code,在message.properties文件中定义的key
     */
    private String i18nCode;

    /**
     * 消息参数
     */
    private Object[] args;

    protected I18nSupportException() {

    }

    public I18nSupportException(String code, Object... args) {
        super(code);
        this.i18nCode = code;
        this.args = args;
    }

    public I18nSupportException(String code, Throwable cause, Object... args) {
        super(code, cause);
        this.args = args;
        this.i18nCode = code;
    }

    public String getOriginalMessage() {
        return super.getMessage() != null ? super.getMessage() : getI18nCode();
    }

    @Override
    public String getMessage() {
        return super.getMessage() != null ? super.getMessage() : getLocalizedMessage();
    }

    @Override
    public final String getLocalizedMessage() {
        return getLocalizedMessage(LocaleUtils.current());
    }

    public String getLocalizedMessage(Locale locale) {
        return LocaleUtils.resolveMessage(i18nCode, locale, getOriginalMessage(), args);
    }

    public final String getLocalizedMessageReactive() {
        Locale locale = LocaleUtils
                .currentReactive();
        return this.getLocalizedMessage(locale);
    }
}
