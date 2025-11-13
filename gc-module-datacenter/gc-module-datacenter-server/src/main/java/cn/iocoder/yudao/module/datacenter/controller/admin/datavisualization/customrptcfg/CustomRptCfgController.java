package cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo.CustomRptCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo.CustomRptCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo.CustomRptCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.datavisualization.customrptcfg.CustomRptCfgDO;
import cn.iocoder.yudao.module.datacenter.service.datavisualization.customrptcfg.CustomRptCfgService;

@Tag(name = "管理后台 - 自定义报表配置")
@RestController
@RequestMapping("/datacenter/custom-rpt-cfg")
@Validated
public class CustomRptCfgController {

    @Resource
    private CustomRptCfgService customRptCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建自定义报表配置")
    @PreAuthorize("@ss.hasPermission('datacenter:custom-rpt-cfg:create')")
    public CommonResult<Long> createCustomRptCfg(@Valid @RequestBody CustomRptCfgSaveReqVO createReqVO) {
        return success(customRptCfgService.createCustomRptCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新自定义报表配置")
    @PreAuthorize("@ss.hasPermission('datacenter:custom-rpt-cfg:update')")
    public CommonResult<Boolean> updateCustomRptCfg(@Valid @RequestBody CustomRptCfgSaveReqVO updateReqVO) {
        customRptCfgService.updateCustomRptCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除自定义报表配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:custom-rpt-cfg:delete')")
    public CommonResult<Boolean> deleteCustomRptCfg(@RequestParam("id") Long id) {
        customRptCfgService.deleteCustomRptCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得自定义报表配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:custom-rpt-cfg:query')")
    public CommonResult<CustomRptCfgRespVO> getCustomRptCfg(@RequestParam("id") Long id) {
        CustomRptCfgDO customRptCfg = customRptCfgService.getCustomRptCfg(id);
        return success(BeanUtils.toBean(customRptCfg, CustomRptCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得自定义报表配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:custom-rpt-cfg:query')")
    public CommonResult<PageResult<CustomRptCfgRespVO>> getCustomRptCfgPage(@Valid CustomRptCfgPageReqVO pageReqVO) {
        PageResult<CustomRptCfgDO> pageResult = customRptCfgService.getCustomRptCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomRptCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出自定义报表配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:custom-rpt-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomRptCfgExcel(@Valid CustomRptCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomRptCfgDO> list = customRptCfgService.getCustomRptCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "自定义报表配置.xls", "数据", CustomRptCfgRespVO.class,
                        BeanUtils.toBean(list, CustomRptCfgRespVO.class));
    }

}