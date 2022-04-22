package pers.guangjian.hadoken.element.system.modules.captcha.service;

import pers.guangjian.hadoken.element.system.modules.captcha.rest.vo.CaptchaImageRespVO;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/21 17:43
 */
public interface CaptchaService {

    /**
     * 获得验证码图片
     *
     * @return 验证码图片
     */
    CaptchaImageRespVO getCaptchaImage();

    /**
     * 是否开启图片验证码
     *
     * @return 是否
     */
    Boolean isCaptchaEnable();

    /**
     * 获得 uuid 对应的验证码
     *
     * @param uuid 验证码编号
     * @return 验证码
     */
    String getCaptchaCode(String uuid);

    /**
     * 删除 uuid 对应的验证码
     *
     * @param uuid 验证码编号
     */
    void deleteCaptchaCode(String uuid);
}
