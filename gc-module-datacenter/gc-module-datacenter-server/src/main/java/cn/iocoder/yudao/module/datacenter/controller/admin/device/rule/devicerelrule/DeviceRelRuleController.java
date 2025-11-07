package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo.DeviceRelRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo.DeviceRelRuleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo.DeviceRelRuleSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.devicerelrule.DeviceRelRuleDO;
import cn.iocoder.yudao.module.datacenter.service.device.rule.devicerelrule.DeviceRelRuleService;

@Tag(name = "管理后台 - 设备关联规则配置")
@RestController
@RequestMapping("/datacenter/device-rel-rule")
@Validated
public class DeviceRelRuleController {

    @Resource
    private DeviceRelRuleService deviceRelRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建设备关联规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-rel-rule:create')")
    public CommonResult<Long> createDeviceRelRule(@Valid @RequestBody DeviceRelRuleSaveReqVO createReqVO) {
        return success(deviceRelRuleService.createDeviceRelRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备关联规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-rel-rule:update')")
    public CommonResult<Boolean> updateDeviceRelRule(@Valid @RequestBody DeviceRelRuleSaveReqVO updateReqVO) {
        deviceRelRuleService.updateDeviceRelRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备关联规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-rel-rule:delete')")
    public CommonResult<Boolean> deleteDeviceRelRule(@RequestParam("id") Long id) {
        deviceRelRuleService.deleteDeviceRelRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备关联规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-rel-rule:query')")
    public CommonResult<DeviceRelRuleRespVO> getDeviceRelRule(@RequestParam("id") Long id) {
        DeviceRelRuleDO deviceRelRule = deviceRelRuleService.getDeviceRelRule(id);
        return success(BeanUtils.toBean(deviceRelRule, DeviceRelRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备关联规则配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-rel-rule:query')")
    public CommonResult<PageResult<DeviceRelRuleRespVO>> getDeviceRelRulePage(@Valid DeviceRelRulePageReqVO pageReqVO) {
        PageResult<DeviceRelRuleDO> pageResult = deviceRelRuleService.getDeviceRelRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceRelRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备关联规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-rel-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceRelRuleExcel(@Valid DeviceRelRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceRelRuleDO> list = deviceRelRuleService.getDeviceRelRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备关联规则配置.xls", "数据", DeviceRelRuleRespVO.class,
                        BeanUtils.toBean(list, DeviceRelRuleRespVO.class));
    }

}