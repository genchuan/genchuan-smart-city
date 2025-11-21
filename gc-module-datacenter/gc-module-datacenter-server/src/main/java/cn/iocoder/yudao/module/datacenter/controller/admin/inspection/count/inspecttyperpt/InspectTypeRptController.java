package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspecttyperpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspecttyperpt.vo.InspectTypeRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspecttyperpt.vo.InspectTypeRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspecttyperpt.vo.InspectTypeRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspecttyperpt.InspectTypeRptDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.count.inspecttyperpt.InspectTypeRptService;

@Tag(name = "管理后台 - 巡查巡检类型统计")
@RestController
@RequestMapping("/datacenter/inspect-type-rpt")
@Validated
public class InspectTypeRptController {

    @Resource
    private InspectTypeRptService inspectTypeRptService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检类型统计")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-type-rpt:create')")
    public CommonResult<Long> createInspectTypeRpt(@Valid @RequestBody InspectTypeRptSaveReqVO createReqVO) {
        return success(inspectTypeRptService.createInspectTypeRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检类型统计")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-type-rpt:update')")
    public CommonResult<Boolean> updateInspectTypeRpt(@Valid @RequestBody InspectTypeRptSaveReqVO updateReqVO) {
        inspectTypeRptService.updateInspectTypeRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检类型统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-type-rpt:delete')")
    public CommonResult<Boolean> deleteInspectTypeRpt(@RequestParam("id") Long id) {
        inspectTypeRptService.deleteInspectTypeRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检类型统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-type-rpt:query')")
    public CommonResult<InspectTypeRptRespVO> getInspectTypeRpt(@RequestParam("id") Long id) {
        InspectTypeRptDO inspectTypeRpt = inspectTypeRptService.getInspectTypeRpt(id);
        return success(BeanUtils.toBean(inspectTypeRpt, InspectTypeRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检类型统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-type-rpt:query')")
    public CommonResult<PageResult<InspectTypeRptRespVO>> getInspectTypeRptPage(@Valid InspectTypeRptPageReqVO pageReqVO) {
        PageResult<InspectTypeRptDO> pageResult = inspectTypeRptService.getInspectTypeRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectTypeRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检类型统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-type-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectTypeRptExcel(@Valid InspectTypeRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectTypeRptDO> list = inspectTypeRptService.getInspectTypeRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检类型统计.xls", "数据", InspectTypeRptRespVO.class,
                        BeanUtils.toBean(list, InspectTypeRptRespVO.class));
    }

}