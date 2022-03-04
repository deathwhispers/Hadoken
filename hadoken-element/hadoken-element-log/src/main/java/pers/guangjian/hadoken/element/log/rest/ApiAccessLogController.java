package pers.guangjian.hadoken.element.log.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guangjian.hadoken.common.entity.PageResult;
import pers.guangjian.hadoken.common.result.CommonResult;
import pers.guangjian.hadoken.element.log.domain.query.ApiAccessLogExportReqVO;
import pers.guangjian.hadoken.element.log.domain.query.ApiAccessLogPageReqVO;
import pers.guangjian.hadoken.element.log.domain.query.ApiAccessLogRespVO;
import pers.guangjian.hadoken.element.log.service.ApiAccessLogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


@Api(tags = "管理后台 - API 访问日志")
@RestController
@RequestMapping("/infra/api-access-log")
@Validated
public class ApiAccessLogController {

    @Resource
    private ApiAccessLogService apiAccessLogService;

    @GetMapping("/page")
    @ApiOperation("获得API 访问日志分页")
    public CommonResult<PageResult<ApiAccessLogRespVO>> getApiAccessLogPage(@Valid ApiAccessLogPageReqVO pageVO) {
        return null;
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出API 访问日志 Excel")
    public void exportApiAccessLogExcel(@Valid ApiAccessLogExportReqVO exportReqVO,
                                        HttpServletResponse response) throws IOException {

    }

}
