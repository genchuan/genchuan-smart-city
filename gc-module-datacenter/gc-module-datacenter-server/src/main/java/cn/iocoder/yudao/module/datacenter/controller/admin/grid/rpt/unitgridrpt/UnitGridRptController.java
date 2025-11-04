package cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.unitgridrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.unitgridrpt.vo.UnitGridRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.unitgridrpt.vo.UnitGridRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.unitgridrpt.vo.UnitGridRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.unitgridrpt.UnitGridRptDO;
import cn.iocoder.yudao.module.datacenter.service.grid.rpt.unitgridrpt.UnitGridRptService;

@Tag(name = "管理后台 - 单元网格统计")
@RestController
@RequestMapping("/datacenter/unit-grid-rpt")
@Validated
public class UnitGridRptController {

    @Resource
    private UnitGridRptService unitGridRptService;

    @PostMapping("/create")
    @Operation(summary = "创建单元网格统计")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-rpt:create')")
    public CommonResult<Long> createUnitGridRpt(@Valid @RequestBody UnitGridRptSaveReqVO createReqVO) {
        return success(unitGridRptService.createUnitGridRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新单元网格统计")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-rpt:update')")
    public CommonResult<Boolean> updateUnitGridRpt(@Valid @RequestBody UnitGridRptSaveReqVO updateReqVO) {
        unitGridRptService.updateUnitGridRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除单元网格统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-rpt:delete')")
    public CommonResult<Boolean> deleteUnitGridRpt(@RequestParam("id") Long id) {
        unitGridRptService.deleteUnitGridRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得单元网格统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-rpt:query')")
    public CommonResult<UnitGridRptRespVO> getUnitGridRpt(@RequestParam("id") Long id) {
        UnitGridRptDO unitGridRpt = unitGridRptService.getUnitGridRpt(id);
        return success(BeanUtils.toBean(unitGridRpt, UnitGridRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得单元网格统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-rpt:query')")
    public CommonResult<PageResult<UnitGridRptRespVO>> getUnitGridRptPage(@Valid UnitGridRptPageReqVO pageReqVO) {
        PageResult<UnitGridRptDO> pageResult = unitGridRptService.getUnitGridRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UnitGridRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出单元网格统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUnitGridRptExcel(@Valid UnitGridRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UnitGridRptDO> list = unitGridRptService.getUnitGridRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "单元网格统计.xls", "数据", UnitGridRptRespVO.class,
                        BeanUtils.toBean(list, UnitGridRptRespVO.class));
    }

}