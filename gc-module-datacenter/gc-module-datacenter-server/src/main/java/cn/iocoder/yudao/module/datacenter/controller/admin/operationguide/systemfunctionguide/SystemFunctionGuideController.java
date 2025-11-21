package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo.SystemFunctionGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo.SystemFunctionGuideRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo.SystemFunctionGuideSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.systemfunctionguide.SystemFunctionGuideDO;
import cn.iocoder.yudao.module.datacenter.service.operationguide.systemfunctionguide.SystemFunctionGuideService;

@Tag(name = "管理后台 - 系统功能指南")
@RestController
@RequestMapping("/datacenter/system-function-guide")
@Validated
public class SystemFunctionGuideController {

    @Resource
    private SystemFunctionGuideService systemFunctionGuideService;

    @PostMapping("/create")
    @Operation(summary = "创建系统功能指南")
    @PreAuthorize("@ss.hasPermission('datacenter:system-function-guide:create')")
    public CommonResult<Long> createSystemFunctionGuide(@Valid @RequestBody SystemFunctionGuideSaveReqVO createReqVO) {
        return success(systemFunctionGuideService.createSystemFunctionGuide(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新系统功能指南")
    @PreAuthorize("@ss.hasPermission('datacenter:system-function-guide:update')")
    public CommonResult<Boolean> updateSystemFunctionGuide(@Valid @RequestBody SystemFunctionGuideSaveReqVO updateReqVO) {
        systemFunctionGuideService.updateSystemFunctionGuide(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除系统功能指南")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:system-function-guide:delete')")
    public CommonResult<Boolean> deleteSystemFunctionGuide(@RequestParam("id") Long id) {
        systemFunctionGuideService.deleteSystemFunctionGuide(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得系统功能指南")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:system-function-guide:query')")
    public CommonResult<SystemFunctionGuideRespVO> getSystemFunctionGuide(@RequestParam("id") Long id) {
        SystemFunctionGuideDO systemFunctionGuide = systemFunctionGuideService.getSystemFunctionGuide(id);
        return success(BeanUtils.toBean(systemFunctionGuide, SystemFunctionGuideRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得系统功能指南分页")
    @PreAuthorize("@ss.hasPermission('datacenter:system-function-guide:query')")
    public CommonResult<PageResult<SystemFunctionGuideRespVO>> getSystemFunctionGuidePage(@Valid SystemFunctionGuidePageReqVO pageReqVO) {
        PageResult<SystemFunctionGuideDO> pageResult = systemFunctionGuideService.getSystemFunctionGuidePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SystemFunctionGuideRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出系统功能指南 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:system-function-guide:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSystemFunctionGuideExcel(@Valid SystemFunctionGuidePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SystemFunctionGuideDO> list = systemFunctionGuideService.getSystemFunctionGuidePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "系统功能指南.xls", "数据", SystemFunctionGuideRespVO.class,
                        BeanUtils.toBean(list, SystemFunctionGuideRespVO.class));
    }

}