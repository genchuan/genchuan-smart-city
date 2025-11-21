package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath.vo.RiskTracePathPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath.vo.RiskTracePathRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath.vo.RiskTracePathSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.risktracepath.RiskTracePathDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.traceability.risktracepath.RiskTracePathService;

@Tag(name = "管理后台 - 风险溯源路径")
@RestController
@RequestMapping("/datacenter/risk-trace-path")
@Validated
public class RiskTracePathController {

    @Resource
    private RiskTracePathService riskTracePathService;

    @PostMapping("/create")
    @Operation(summary = "创建风险溯源路径")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-trace-path:create')")
    public CommonResult<Long> createRiskTracePath(@Valid @RequestBody RiskTracePathSaveReqVO createReqVO) {
        return success(riskTracePathService.createRiskTracePath(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新风险溯源路径")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-trace-path:update')")
    public CommonResult<Boolean> updateRiskTracePath(@Valid @RequestBody RiskTracePathSaveReqVO updateReqVO) {
        riskTracePathService.updateRiskTracePath(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除风险溯源路径")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:risk-trace-path:delete')")
    public CommonResult<Boolean> deleteRiskTracePath(@RequestParam("id") Long id) {
        riskTracePathService.deleteRiskTracePath(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得风险溯源路径")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-trace-path:query')")
    public CommonResult<RiskTracePathRespVO> getRiskTracePath(@RequestParam("id") Long id) {
        RiskTracePathDO riskTracePath = riskTracePathService.getRiskTracePath(id);
        return success(BeanUtils.toBean(riskTracePath, RiskTracePathRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得风险溯源路径分页")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-trace-path:query')")
    public CommonResult<PageResult<RiskTracePathRespVO>> getRiskTracePathPage(@Valid RiskTracePathPageReqVO pageReqVO) {
        PageResult<RiskTracePathDO> pageResult = riskTracePathService.getRiskTracePathPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RiskTracePathRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出风险溯源路径 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-trace-path:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRiskTracePathExcel(@Valid RiskTracePathPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RiskTracePathDO> list = riskTracePathService.getRiskTracePathPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "风险溯源路径.xls", "数据", RiskTracePathRespVO.class,
                        BeanUtils.toBean(list, RiskTracePathRespVO.class));
    }

}