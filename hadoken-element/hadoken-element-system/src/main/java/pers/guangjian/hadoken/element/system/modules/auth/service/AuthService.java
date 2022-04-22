package pers.guangjian.hadoken.element.system.modules.auth.service;

import pers.guangjian.hadoken.element.system.modules.auth.rest.vo.AuthLoginReqVO;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/21 16:21
 */
public interface AuthService {
    String login(AuthLoginReqVO authUser);
}
