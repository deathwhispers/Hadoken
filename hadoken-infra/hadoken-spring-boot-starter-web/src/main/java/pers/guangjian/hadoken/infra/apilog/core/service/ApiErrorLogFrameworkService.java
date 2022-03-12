package pers.guangjian.hadoken.infra.apilog.core.service;

import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiErrorLogDTO;

import javax.validation.Valid;

/**
 * @author yanggj
 *  API 错误日志 接口
 * @date 2022/03/02 9:28
 * @version 1.0.0
 */
public interface ApiErrorLogFrameworkService {

    /**
     * 创建 API 错误日志
     *
     * @param createDTO 创建信息
     */
    void createApiErrorLogAsync(@Valid ApiErrorLogDTO createDTO);

}
