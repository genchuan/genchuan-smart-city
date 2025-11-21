package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt.vo.InspectAreaRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt.vo.InspectAreaRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt.vo.InspectAreaRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspectarearpt.InspectAreaRptDO;
import cn.iocoder.yudao.module.datacenter.service.inspectarearpt.InspectAreaRptService;

@Tag(name = "管理后台 - 巡查巡检区域统计")
@RestController
@RequestMapping("/datacenter/inspect-area-rpt")
@Validated
public class InspectAreaRptController {

    @Resource
    private InspectAreaRptService inspectAreaRptService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检区域统计")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-area-rpt:create')")
    public CommonResult<Long> createInspectAreaRpt(@Valid @RequestBody InspectAreaRptSaveReqVO createReqVO) {
        return success(inspectAreaRptService.createInspectAreaRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检区域统计")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-area-rpt:update')")
    public CommonResult<Boolean> updateInspectAreaRpt(@Valid @RequestBody InspectAreaRptSaveReqVO updateReqVO) {
        inspectAreaRptService.updateInspectAreaRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检区域统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-area-rpt:delete')")
    public CommonResult<Boolean> deleteInspectAreaRpt(@RequestParam("id") Long id) {
        inspectAreaRptService.deleteInspectAreaRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检区域统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-area-rpt:query')")
    public CommonResult<InspectAreaRptRespVO> getInspectAreaRpt(@RequestParam("id") Long id) {
        InspectAreaRptDO inspectAreaRpt = inspectAreaRptService.getInspectAreaRpt(id);
        return success(BeanUtils.toBean(inspectAreaRpt, InspectAreaRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检区域统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-area-rpt:query')")
    public CommonResult<PageResult<InspectAreaRptRespVO>> getInspectAreaRptPage(@Valid InspectAreaRptPageReqVO pageReqVO) {
        PageResult<InspectAreaRptDO> pageResult = inspectAreaRptService.getInspectAreaRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectAreaRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检区域统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-area-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectAreaRptExcel(@Valid InspectAreaRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectAreaRptDO> list = inspectAreaRptService.getInspectAreaRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检区域统计.xls", "数据", InspectAreaRptRespVO.class,
                        BeanUtils.toBean(list, InspectAreaRptRespVO.class));
    }

}