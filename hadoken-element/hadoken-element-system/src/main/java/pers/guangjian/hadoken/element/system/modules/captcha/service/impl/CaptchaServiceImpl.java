package pers.guangjian.hadoken.element.system.modules.captcha.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pers.guangjian.hadoken.element.system.modules.captcha.repository.CaptchaRedisRepository;
import pers.guangjian.hadoken.element.system.modules.captcha.rest.vo.CaptchaImageRespVO;
import pers.guangjian.hadoken.element.system.modules.captcha.service.CaptchaService;
import pers.guangjian.hadoken.element.system.modules.captcha.service.dto.CaptchaProperties;
import pers.guangjian.hadoken.element.system.modules.captcha.service.mapstruct.CaptchaMapper;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/21 17:43
 */
@RequiredArgsConstructor
@Service
public class CaptchaServiceImpl implements CaptchaService {

    /**
     * 验证码是否开关
     */
    @Value("${hadoken.captcha.enable}")
    private Boolean enable;

    private final CaptchaProperties captchaProperties;
    private final CaptchaRedisRepository captchaRedisRepository;
    private final CaptchaMapper captchaMapper;

    @Override
    public CaptchaImageRespVO getCaptchaImage() {
        if (!Boolean.TRUE.equals(enable)) {
            return CaptchaImageRespVO.builder().enable(enable).build();
        }

        // 生成验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());

        // 缓存到 Redis 中
        String uuid = IdUtil.fastSimpleUUID();
        captchaRedisRepository.set(uuid, captcha.getCode(), captchaProperties.getTimeout());

        CaptchaImageRespVO convert = captchaMapper.convert(uuid, captcha);
        convert.setEnable(enable);
        return convert;
    }

    @Override
    public Boolean isCaptchaEnable() {
        return enable;
    }

    @Override
    public String getCaptchaCode(String uuid) {
        return captchaRedisRepository.get(uuid);
    }

    @Override
    public void deleteCaptchaCode(String uuid) {
        captchaRedisRepository.delete(uuid);
    }

}
