package pers.guangjian.hadoken.infra.apilog.core.service;

import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiAccessLogCreateReqDTO;

import javax.validation.Valid;

/**
 * @Author: yanggj
 * @Description: API 访问日志接口
 * @Date: 2022/03/02 9:28
 * @Version: 1.0.0
 */
public interface ApiAccessLogService {

    /**
     * 创建 API 访问日志
     *
     * @param createDTO 创建信息
     */
    void createApiAccessLogAsync(@Valid ApiAccessLogCreateReqDTO createDTO);

}
