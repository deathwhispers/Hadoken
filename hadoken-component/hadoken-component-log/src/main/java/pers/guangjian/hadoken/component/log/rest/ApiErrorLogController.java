package pers.guangjian.hadoken.component.log.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.guangjian.hadoken.common.result.CommonResult;
import pers.guangjian.hadoken.component.log.domain.query.ApiErrorLogQueryCriteria;
import pers.guangjian.hadoken.component.log.service.ApiErrorLogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Api(tags = "管理后台 - API 错误日志")
@RestController
@RequestMapping("/infra/api-error-log")
@Validated
public class ApiErrorLogController {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    @PutMapping("/update-status")
    @ApiOperation("更新 API 错误日志的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "processStatus", value = "处理状态", required = true, example = "1", dataTypeClass = Integer.class)
    })
    public CommonResult<Boolean> updateApiErrorLogProcess(@RequestParam("id") Long id, @RequestParam("processStatus") Integer processStatus) {
        return null;
    }

    @GetMapping("/page")
    @ApiOperation("获得 API 错误日志分页")
    public CommonResult<Object> getApiErrorLogPage(ApiErrorLogQueryCriteria criteria, Pageable pageable) {
        Object result = apiErrorLogService.queryAll(criteria,pageable);
        return CommonResult.success(result);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出 API 错误日志 Excel")
    public void exportApiErrorLogExcel(ApiErrorLogQueryCriteria criteria, HttpServletResponse response) throws IOException {
    }

}
