package pers.guangjian.hadoken.connector.core;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 15:03
 */
public class UnsupportedMessageSource implements MessageSource {

    private static final UnsupportedMessageSource INSTANCE = new UnsupportedMessageSource();

    public static MessageSource instance() {
        return INSTANCE;
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return defaultMessage;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return code;
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return resolvable.getDefaultMessage();
    }
}
