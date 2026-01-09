package cn.iocoder.yudao.module.smartcity.controller.admin.monitoringrule;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.monitoringrule.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.monitoringrule.MonitoringRuleDO;
import cn.iocoder.yudao.module.smartcity.service.monitoringrule.MonitoringRuleService;

@Tag(name = "管理后台 - 监测项规则设置")
@RestController
@RequestMapping("/smartcity/monitoring-rule")
@Validated
public class MonitoringRuleController {

    @Resource
    private MonitoringRuleService monitoringRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建监测项规则设置")
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-rule:create')")
    public CommonResult<Long> createMonitoringRule(@Valid @RequestBody MonitoringRuleSaveReqVO createReqVO) {
        return success(monitoringRuleService.createMonitoringRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测项规则设置")
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-rule:update')")
    public CommonResult<Boolean> updateMonitoringRule(@Valid @RequestBody MonitoringRuleSaveReqVO updateReqVO) {
        monitoringRuleService.updateMonitoringRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测项规则设置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-rule:delete')")
    public CommonResult<Boolean> deleteMonitoringRule(@RequestParam("id") Long id) {
        monitoringRuleService.deleteMonitoringRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测项规则设置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-rule:query')")
    public CommonResult<MonitoringRuleRespVO> getMonitoringRule(@RequestParam("id") Long id) {
        MonitoringRuleDO monitoringRule = monitoringRuleService.getMonitoringRule(id);
        return success(BeanUtils.toBean(monitoringRule, MonitoringRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测项规则设置分页")
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-rule:query')")
    public CommonResult<PageResult<MonitoringRuleRespVO>> getMonitoringRulePage(@Valid MonitoringRulePageReqVO pageReqVO) {
        PageResult<MonitoringRuleDO> pageResult = monitoringRuleService.getMonitoringRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonitoringRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测项规则设置 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonitoringRuleExcel(@Valid MonitoringRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonitoringRuleDO> list = monitoringRuleService.getMonitoringRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测项规则设置.xls", "数据", MonitoringRuleRespVO.class,
                        BeanUtils.toBean(list, MonitoringRuleRespVO.class));
    }

}