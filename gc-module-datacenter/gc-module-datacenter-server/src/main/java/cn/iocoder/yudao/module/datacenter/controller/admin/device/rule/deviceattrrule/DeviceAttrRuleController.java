package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo.DeviceAttrRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo.DeviceAttrRuleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo.DeviceAttrRuleSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.deviceattrrule.DeviceAttrRuleDO;
import cn.iocoder.yudao.module.datacenter.service.device.rule.deviceattrrule.DeviceAttrRuleService;

@Tag(name = "管理后台 - 设备属性规则配置")
@RestController
@RequestMapping("/datacenter/device-attr-rule")
@Validated
public class DeviceAttrRuleController {

    @Resource
    private DeviceAttrRuleService deviceAttrRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建设备属性规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-attr-rule:create')")
    public CommonResult<Long> createDeviceAttrRule(@Valid @RequestBody DeviceAttrRuleSaveReqVO createReqVO) {
        return success(deviceAttrRuleService.createDeviceAttrRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备属性规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-attr-rule:update')")
    public CommonResult<Boolean> updateDeviceAttrRule(@Valid @RequestBody DeviceAttrRuleSaveReqVO updateReqVO) {
        deviceAttrRuleService.updateDeviceAttrRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备属性规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-attr-rule:delete')")
    public CommonResult<Boolean> deleteDeviceAttrRule(@RequestParam("id") Long id) {
        deviceAttrRuleService.deleteDeviceAttrRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备属性规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-attr-rule:query')")
    public CommonResult<DeviceAttrRuleRespVO> getDeviceAttrRule(@RequestParam("id") Long id) {
        DeviceAttrRuleDO deviceAttrRule = deviceAttrRuleService.getDeviceAttrRule(id);
        return success(BeanUtils.toBean(deviceAttrRule, DeviceAttrRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备属性规则配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-attr-rule:query')")
    public CommonResult<PageResult<DeviceAttrRuleRespVO>> getDeviceAttrRulePage(@Valid DeviceAttrRulePageReqVO pageReqVO) {
        PageResult<DeviceAttrRuleDO> pageResult = deviceAttrRuleService.getDeviceAttrRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceAttrRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备属性规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-attr-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceAttrRuleExcel(@Valid DeviceAttrRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceAttrRuleDO> list = deviceAttrRuleService.getDeviceAttrRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备属性规则配置.xls", "数据", DeviceAttrRuleRespVO.class,
                        BeanUtils.toBean(list, DeviceAttrRuleRespVO.class));
    }

}