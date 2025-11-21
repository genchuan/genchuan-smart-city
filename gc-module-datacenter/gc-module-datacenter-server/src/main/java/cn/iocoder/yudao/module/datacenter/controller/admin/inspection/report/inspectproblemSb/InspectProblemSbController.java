package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemSb;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemSb.vo.InspectProblemSbPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemSb.vo.InspectProblemSbRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemSb.vo.InspectProblemSbSaveVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemSb.InspectProblemSbDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.report.inspectproblemSb.InspectProblemSbService;

@Tag(name = "管理后台 - 巡查巡检问题上报记录")
@RestController
@RequestMapping("/datacenter/inspect-problem-sb")
@Validated
public class InspectProblemSbController {

    @Resource
    private InspectProblemSbService inspectProblemSbService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检问题上报记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:create')")
    public CommonResult<Long> createInspectProblemRpt(@Valid @RequestBody InspectProblemSbSaveVO createReqVO) {
        return success(inspectProblemSbService.createInspectProblemRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检问题上报记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:update')")
    public CommonResult<Boolean> updateInspectProblemRpt(@Valid @RequestBody InspectProblemSbSaveVO updateReqVO) {
        inspectProblemSbService.updateInspectProblemRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检问题上报记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:delete')")
    public CommonResult<Boolean> deleteInspectProblemRpt(@RequestParam("id") Long id) {
        inspectProblemSbService.deleteInspectProblemRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检问题上报记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:query')")
    public CommonResult<InspectProblemSbRespVO> getInspectProblemRpt(@RequestParam("id") Long id) {
        InspectProblemSbDO inspectProblemRpt = inspectProblemSbService.getInspectProblemRpt(id);
        return success(BeanUtils.toBean(inspectProblemRpt, InspectProblemSbRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检问题上报记录分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:query')")
    public CommonResult<PageResult<InspectProblemSbRespVO>> getInspectProblemRptPage(@Valid InspectProblemSbPageReqVO pageReqVO) {
        PageResult<InspectProblemSbDO> pageResult = inspectProblemSbService.getInspectProblemRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectProblemSbRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检问题上报记录 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectProblemRptExcel(@Valid InspectProblemSbPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectProblemSbDO> list = inspectProblemSbService.getInspectProblemRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检问题上报记录.xls", "数据", InspectProblemSbRespVO.class,
                        BeanUtils.toBean(list, InspectProblemSbRespVO.class));
    }

}