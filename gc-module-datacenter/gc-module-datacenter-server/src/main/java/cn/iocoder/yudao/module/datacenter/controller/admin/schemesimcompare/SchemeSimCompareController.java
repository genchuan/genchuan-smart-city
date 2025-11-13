package cn.iocoder.yudao.module.datacenter.controller.admin.schemesimcompare;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.schemesimcompare.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.schemesimcompare.SchemeSimCompareDO;
import cn.iocoder.yudao.module.datacenter.service.schemesimcompare.SchemeSimCompareService;

@Tag(name = "管理后台 - 方案模拟对比统计")
@RestController
@RequestMapping("/datacenter/scheme-sim-compare")
@Validated
public class SchemeSimCompareController {

    @Resource
    private SchemeSimCompareService schemeSimCompareService;

    @PostMapping("/create")
    @Operation(summary = "创建方案模拟对比统计")
    @PreAuthorize("@ss.hasPermission('datacenter:scheme-sim-compare:create')")
    public CommonResult<Long> createSchemeSimCompare(@Valid @RequestBody SchemeSimCompareSaveReqVO createReqVO) {
        return success(schemeSimCompareService.createSchemeSimCompare(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新方案模拟对比统计")
    @PreAuthorize("@ss.hasPermission('datacenter:scheme-sim-compare:update')")
    public CommonResult<Boolean> updateSchemeSimCompare(@Valid @RequestBody SchemeSimCompareSaveReqVO updateReqVO) {
        schemeSimCompareService.updateSchemeSimCompare(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除方案模拟对比统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:scheme-sim-compare:delete')")
    public CommonResult<Boolean> deleteSchemeSimCompare(@RequestParam("id") Long id) {
        schemeSimCompareService.deleteSchemeSimCompare(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得方案模拟对比统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:scheme-sim-compare:query')")
    public CommonResult<SchemeSimCompareRespVO> getSchemeSimCompare(@RequestParam("id") Long id) {
        SchemeSimCompareDO schemeSimCompare = schemeSimCompareService.getSchemeSimCompare(id);
        return success(BeanUtils.toBean(schemeSimCompare, SchemeSimCompareRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得方案模拟对比统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:scheme-sim-compare:query')")
    public CommonResult<PageResult<SchemeSimCompareRespVO>> getSchemeSimComparePage(@Valid SchemeSimComparePageReqVO pageReqVO) {
        PageResult<SchemeSimCompareDO> pageResult = schemeSimCompareService.getSchemeSimComparePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SchemeSimCompareRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出方案模拟对比统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:scheme-sim-compare:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSchemeSimCompareExcel(@Valid SchemeSimComparePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SchemeSimCompareDO> list = schemeSimCompareService.getSchemeSimComparePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "方案模拟对比统计.xls", "数据", SchemeSimCompareRespVO.class,
                        BeanUtils.toBean(list, SchemeSimCompareRespVO.class));
    }

}