package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo.AlarmRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo.AlarmRuleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo.AlarmRuleSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.alarmrule.AlarmRuleDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.alarmrule.AlarmRuleService;

@Tag(name = "管理后台 - 预警告警规则配置")
@RestController
@RequestMapping("/datacenter/alarm-rule")
@Validated
public class AlarmRuleController {

    @Resource
    private AlarmRuleService alarmRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:create')")
    public CommonResult<Long> createAlarmRule(@Valid @RequestBody AlarmRuleSaveReqVO createReqVO) {
        return success(alarmRuleService.createAlarmRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:update')")
    public CommonResult<Boolean> updateAlarmRule(@Valid @RequestBody AlarmRuleSaveReqVO updateReqVO) {
        alarmRuleService.updateAlarmRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:delete')")
    public CommonResult<Boolean> deleteAlarmRule(@RequestParam("id") Long id) {
        alarmRuleService.deleteAlarmRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:query')")
    public CommonResult<AlarmRuleRespVO> getAlarmRule(@RequestParam("id") Long id) {
        AlarmRuleDO alarmRule = alarmRuleService.getAlarmRule(id);
        return success(BeanUtils.toBean(alarmRule, AlarmRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警规则配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:query')")
    public CommonResult<PageResult<AlarmRuleRespVO>> getAlarmRulePage(@Valid AlarmRulePageReqVO pageReqVO) {
        PageResult<AlarmRuleDO> pageResult = alarmRuleService.getAlarmRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AlarmRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAlarmRuleExcel(@Valid AlarmRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AlarmRuleDO> list = alarmRuleService.getAlarmRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警规则配置.xls", "数据", AlarmRuleRespVO.class,
                        BeanUtils.toBean(list, AlarmRuleRespVO.class));
    }

    @PutMapping("/enable")
    @Operation(summary = "启用预警告警规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:update')")
    public CommonResult<Boolean> enableAlarmRule(@RequestParam("id") Long id) {
        alarmRuleService.enableAlarmRule(id);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用预警告警规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:update')")
    public CommonResult<Boolean> disableAlarmRule(@RequestParam("id") Long id) {
        alarmRuleService.disableAlarmRule(id);
        return success(true);
    }

    @PostMapping("/validate-unique")
    @Operation(summary = "校验规则唯一性")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:query')")
    public CommonResult<Boolean> validateAlarmRuleUnique(@Valid @RequestBody AlarmRuleSaveReqVO validateReqVO,
                                                         @RequestParam(value = "excludeId", required = false) Long excludeId) {
        alarmRuleService.validateAlarmRuleUnique(validateReqVO, excludeId);
        return success(true);
    }

    @GetMapping("/list-by-status")
    @Operation(summary = "根据状态获得预警告警规则配置列表")
    @Parameter(name = "enableStatus", description = "启用状态", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:query')")
    public CommonResult<List<AlarmRuleRespVO>> getAlarmRuleListByStatus(@RequestParam("enableStatus") Integer enableStatus) {
        List<AlarmRuleDO> list = alarmRuleService.getAlarmRuleListByStatus(enableStatus);
        return success(BeanUtils.toBean(list, AlarmRuleRespVO.class));
    }

    @GetMapping("/enabled-list")
    @Operation(summary = "获得所有启用的预警告警规则配置列表")
    @PreAuthorize("@ss.hasPermission('datacenter:alarm-rule:query')")
    public CommonResult<List<AlarmRuleRespVO>> getEnabledAlarmRuleList() {
        List<AlarmRuleDO> list = alarmRuleService.getEnabledAlarmRuleList();
        return success(BeanUtils.toBean(list, AlarmRuleRespVO.class));
    }

}