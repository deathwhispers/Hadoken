package pers.guangjian.hadoken.component.log.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guangjian.hadoken.common.result.CommonResult;
import pers.guangjian.hadoken.component.log.domain.query.ApiAccessLogQueryCriteria;
import pers.guangjian.hadoken.component.log.service.ApiAccessLogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@Api(tags = "管理后台 - API 访问日志")
@RestController
@RequestMapping("/infra/api-access-log")
@Validated
public class ApiAccessLogController {

    @Resource
    private ApiAccessLogService apiAccessLogService;

    @GetMapping("/page")
    @ApiOperation("获得API 访问日志分页")
    public CommonResult<Object> getApiAccessLogPage(ApiAccessLogQueryCriteria criteria, Pageable pageable) {
        Object result = apiAccessLogService.queryAll(criteria, pageable);
        return CommonResult.success(result);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出API 访问日志 Excel")
    public void exportApiAccessLogExcel(ApiAccessLogQueryCriteria criteria, HttpServletResponse response) {
        apiAccessLogService.export(apiAccessLogService.queryAll(criteria));
    }

}
