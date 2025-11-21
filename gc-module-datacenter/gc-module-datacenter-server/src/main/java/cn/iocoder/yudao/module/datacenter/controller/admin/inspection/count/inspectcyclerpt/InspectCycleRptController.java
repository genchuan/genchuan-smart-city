package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo.InspectCycleRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo.InspectCycleRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo.InspectCycleRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectcyclerpt.InspectCycleRptDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.count.inspectcyclerpt.InspectCycleRptService;

@Tag(name = "管理后台 - 巡查巡检周期统计")
@RestController
@RequestMapping("/datacenter/inspect-cycle-rpt")
@Validated
public class InspectCycleRptController {

    @Resource
    private InspectCycleRptService inspectCycleRptService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检周期统计")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-cycle-rpt:create')")
    public CommonResult<Long> createInspectCycleRpt(@Valid @RequestBody InspectCycleRptSaveReqVO createReqVO) {
        return success(inspectCycleRptService.createInspectCycleRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检周期统计")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-cycle-rpt:update')")
    public CommonResult<Boolean> updateInspectCycleRpt(@Valid @RequestBody InspectCycleRptSaveReqVO updateReqVO) {
        inspectCycleRptService.updateInspectCycleRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检周期统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-cycle-rpt:delete')")
    public CommonResult<Boolean> deleteInspectCycleRpt(@RequestParam("id") Long id) {
        inspectCycleRptService.deleteInspectCycleRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检周期统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-cycle-rpt:query')")
    public CommonResult<InspectCycleRptRespVO> getInspectCycleRpt(@RequestParam("id") Long id) {
        InspectCycleRptDO inspectCycleRpt = inspectCycleRptService.getInspectCycleRpt(id);
        return success(BeanUtils.toBean(inspectCycleRpt, InspectCycleRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检周期统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-cycle-rpt:query')")
    public CommonResult<PageResult<InspectCycleRptRespVO>> getInspectCycleRptPage(@Valid InspectCycleRptPageReqVO pageReqVO) {
        PageResult<InspectCycleRptDO> pageResult = inspectCycleRptService.getInspectCycleRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectCycleRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检周期统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-cycle-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectCycleRptExcel(@Valid InspectCycleRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectCycleRptDO> list = inspectCycleRptService.getInspectCycleRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检周期统计.xls", "数据", InspectCycleRptRespVO.class,
                        BeanUtils.toBean(list, InspectCycleRptRespVO.class));
    }

}