package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo.DeviceCatRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo.DeviceCatRuleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo.DeviceCatRuleSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.devicecatrule.DeviceCatRuleDO;
import cn.iocoder.yudao.module.datacenter.service.device.rule.devicecatrule.DeviceCatRuleService;

@Tag(name = "管理后台 - 设备分类规则配置")
@RestController
@RequestMapping("/datacenter/device-cat-rule")
@Validated
public class DeviceCatRuleController {

    @Resource
    private DeviceCatRuleService deviceCatRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建设备分类规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rule:create')")
    public CommonResult<Long> createDeviceCatRule(@Valid @RequestBody DeviceCatRuleSaveReqVO createReqVO) {
        return success(deviceCatRuleService.createDeviceCatRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备分类规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rule:update')")
    public CommonResult<Boolean> updateDeviceCatRule(@Valid @RequestBody DeviceCatRuleSaveReqVO updateReqVO) {
        deviceCatRuleService.updateDeviceCatRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备分类规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rule:delete')")
    public CommonResult<Boolean> deleteDeviceCatRule(@RequestParam("id") Long id) {
        deviceCatRuleService.deleteDeviceCatRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备分类规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rule:query')")
    public CommonResult<DeviceCatRuleRespVO> getDeviceCatRule(@RequestParam("id") Long id) {
        DeviceCatRuleDO deviceCatRule = deviceCatRuleService.getDeviceCatRule(id);
        return success(BeanUtils.toBean(deviceCatRule, DeviceCatRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分类规则配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rule:query')")
    public CommonResult<PageResult<DeviceCatRuleRespVO>> getDeviceCatRulePage(@Valid DeviceCatRulePageReqVO pageReqVO) {
        PageResult<DeviceCatRuleDO> pageResult = deviceCatRuleService.getDeviceCatRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceCatRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备分类规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceCatRuleExcel(@Valid DeviceCatRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceCatRuleDO> list = deviceCatRuleService.getDeviceCatRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备分类规则配置.xls", "数据", DeviceCatRuleRespVO.class,
                        BeanUtils.toBean(list, DeviceCatRuleRespVO.class));
    }

}