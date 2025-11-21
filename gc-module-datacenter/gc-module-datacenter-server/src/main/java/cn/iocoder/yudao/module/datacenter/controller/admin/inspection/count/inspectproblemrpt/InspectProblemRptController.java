package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo.InspectProblemRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo.InspectProblemRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo.InspectProblemRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectproblemrpt.InspectProblemRptDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.count.inspectproblemrpt.InspectProblemRptService;

@Tag(name = "管理后台 - 巡查巡检问题统计")
@RestController
@RequestMapping("/datacenter/inspect-problem-rpt")
@Validated
public class InspectProblemRptController {

    @Resource
    private InspectProblemRptService inspectProblemRptService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检问题统计")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:create')")
    public CommonResult<Long> createInspectProblemRpt(@Valid @RequestBody InspectProblemRptSaveReqVO createReqVO) {
        return success(inspectProblemRptService.createInspectProblemRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检问题统计")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:update')")
    public CommonResult<Boolean> updateInspectProblemRpt(@Valid @RequestBody InspectProblemRptSaveReqVO updateReqVO) {
        inspectProblemRptService.updateInspectProblemRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检问题统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:delete')")
    public CommonResult<Boolean> deleteInspectProblemRpt(@RequestParam("id") Long id) {
        inspectProblemRptService.deleteInspectProblemRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检问题统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:query')")
    public CommonResult<InspectProblemRptRespVO> getInspectProblemRpt(@RequestParam("id") Long id) {
        InspectProblemRptDO inspectProblemRpt = inspectProblemRptService.getInspectProblemRpt(id);
        return success(BeanUtils.toBean(inspectProblemRpt, InspectProblemRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检问题统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:query')")
    public CommonResult<PageResult<InspectProblemRptRespVO>> getInspectProblemRptPage(@Valid InspectProblemRptPageReqVO pageReqVO) {
        PageResult<InspectProblemRptDO> pageResult = inspectProblemRptService.getInspectProblemRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectProblemRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检问题统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectProblemRptExcel(@Valid InspectProblemRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectProblemRptDO> list = inspectProblemRptService.getInspectProblemRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检问题统计.xls", "数据", InspectProblemRptRespVO.class,
                        BeanUtils.toBean(list, InspectProblemRptRespVO.class));
    }

}