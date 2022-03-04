package pers.guangjian.hadoken.infra.apilog.core.service;

import pers.guangjian.hadoken.infra.apilog.core.service.dto.ApiErrorLogCreateReqDTO;

import javax.validation.Valid;

/**
 * @Author: yanggj
 * @Description: API 错误日志 接口
 * @Date: 2022/03/02 9:28
 * @Version: 1.0.0
 */
public interface ApiErrorLogFrameworkService {

    /**
     * 创建 API 错误日志
     *
     * @param createDTO 创建信息
     */
    void createApiErrorLogAsync(@Valid ApiErrorLogCreateReqDTO createDTO);

}
