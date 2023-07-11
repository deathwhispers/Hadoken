package pers.guangjian.hadoken.connector.core.exception;

import lombok.Getter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 9:49
 */
@Getter
public class ProductNotActivatedException extends I18nSupportException {
    private String productId;

    public ProductNotActivatedException(String productId) {
        super("error.product_not_activated", productId);
    }
}