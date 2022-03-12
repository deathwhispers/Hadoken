package pers.guangjian.hadoken.infra.apilog.core.service;

import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiAccessLogDTO;

import javax.validation.Valid;

/**
 * @author yanggj
 *  API 访问日志接口
 * @date 2022/03/02 9:28
 * @version 1.0.0
 */
public interface ApiAccessLogFrameworkService {

    /**
     * 创建 API 访问日志
     *
     * @param createDTO 创建信息
     */
    void createApiAccessLogAsync(@Valid ApiAccessLogDTO createDTO);

}
