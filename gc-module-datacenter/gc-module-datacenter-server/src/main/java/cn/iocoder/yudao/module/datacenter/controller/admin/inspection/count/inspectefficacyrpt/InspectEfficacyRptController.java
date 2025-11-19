package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt.vo.InspectEfficacyRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt.vo.InspectEfficacyRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt.vo.InspectEfficacyRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectefficacyrpt.InspectEfficacyRptDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.count.inspectefficacyrpt.InspectEfficacyRptService;

@Tag(name = "管理后台 - 巡查巡检效率统计")
@RestController
@RequestMapping("/datacenter/inspect-efficacy-rpt")
@Validated
public class InspectEfficacyRptController {

    @Resource
    private InspectEfficacyRptService inspectEfficacyRptService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检效率统计")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-efficacy-rpt:create')")
    public CommonResult<Long> createInspectEfficacyRpt(@Valid @RequestBody InspectEfficacyRptSaveReqVO createReqVO) {
        return success(inspectEfficacyRptService.createInspectEfficacyRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检效率统计")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-efficacy-rpt:update')")
    public CommonResult<Boolean> updateInspectEfficacyRpt(@Valid @RequestBody InspectEfficacyRptSaveReqVO updateReqVO) {
        inspectEfficacyRptService.updateInspectEfficacyRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检效率统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-efficacy-rpt:delete')")
    public CommonResult<Boolean> deleteInspectEfficacyRpt(@RequestParam("id") Long id) {
        inspectEfficacyRptService.deleteInspectEfficacyRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检效率统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-efficacy-rpt:query')")
    public CommonResult<InspectEfficacyRptRespVO> getInspectEfficacyRpt(@RequestParam("id") Long id) {
        InspectEfficacyRptDO inspectEfficacyRpt = inspectEfficacyRptService.getInspectEfficacyRpt(id);
        return success(BeanUtils.toBean(inspectEfficacyRpt, InspectEfficacyRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检效率统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-efficacy-rpt:query')")
    public CommonResult<PageResult<InspectEfficacyRptRespVO>> getInspectEfficacyRptPage(@Valid InspectEfficacyRptPageReqVO pageReqVO) {
        PageResult<InspectEfficacyRptDO> pageResult = inspectEfficacyRptService.getInspectEfficacyRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectEfficacyRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检效率统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-efficacy-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectEfficacyRptExcel(@Valid InspectEfficacyRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectEfficacyRptDO> list = inspectEfficacyRptService.getInspectEfficacyRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检效率统计.xls", "数据", InspectEfficacyRptRespVO.class,
                        BeanUtils.toBean(list, InspectEfficacyRptRespVO.class));
    }

}