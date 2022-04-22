package pers.guangjian.hadoken.element.system.modules.captcha.service.mapstruct;

import cn.hutool.captcha.AbstractCaptcha;
import org.mapstruct.Mapper;
import pers.guangjian.hadoken.element.system.modules.captcha.rest.vo.CaptchaImageRespVO;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/22 10:03
 */
@Mapper
public interface CaptchaMapper {

    default CaptchaImageRespVO convert(String uuid, AbstractCaptcha captcha) {
        return CaptchaImageRespVO.builder()
                .uuid(uuid)
                .img(captcha.getImageBase64())
                .build();
    }

}
