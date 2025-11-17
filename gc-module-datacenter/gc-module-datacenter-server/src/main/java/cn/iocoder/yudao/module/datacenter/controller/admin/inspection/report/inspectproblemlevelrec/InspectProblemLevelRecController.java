package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec.vo.InspectProblemLevelRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec.vo.InspectProblemLevelRecRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec.vo.InspectProblemLevelRecSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemlevelrec.InspectProblemLevelRecDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.report.inspectproblemlevelrec.InspectProblemLevelRecService;

@Tag(name = "管理后台 - 巡查巡检问题分级记录")
@RestController
@RequestMapping("/datacenter/inspect-problem-level-rec")
@Validated
public class InspectProblemLevelRecController {

    @Resource
    private InspectProblemLevelRecService inspectProblemLevelRecService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检问题分级记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-level-rec:create')")
    public CommonResult<Long> createInspectProblemLevelRec(@Valid @RequestBody InspectProblemLevelRecSaveReqVO createReqVO) {
        return success(inspectProblemLevelRecService.createInspectProblemLevelRec(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检问题分级记录")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-level-rec:update')")
    public CommonResult<Boolean> updateInspectProblemLevelRec(@Valid @RequestBody InspectProblemLevelRecSaveReqVO updateReqVO) {
        inspectProblemLevelRecService.updateInspectProblemLevelRec(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检问题分级记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-level-rec:delete')")
    public CommonResult<Boolean> deleteInspectProblemLevelRec(@RequestParam("id") Long id) {
        inspectProblemLevelRecService.deleteInspectProblemLevelRec(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检问题分级记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-level-rec:query')")
    public CommonResult<InspectProblemLevelRecRespVO> getInspectProblemLevelRec(@RequestParam("id") Long id) {
        InspectProblemLevelRecDO inspectProblemLevelRec = inspectProblemLevelRecService.getInspectProblemLevelRec(id);
        return success(BeanUtils.toBean(inspectProblemLevelRec, InspectProblemLevelRecRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检问题分级记录分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-level-rec:query')")
    public CommonResult<PageResult<InspectProblemLevelRecRespVO>> getInspectProblemLevelRecPage(@Valid InspectProblemLevelRecPageReqVO pageReqVO) {
        PageResult<InspectProblemLevelRecDO> pageResult = inspectProblemLevelRecService.getInspectProblemLevelRecPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectProblemLevelRecRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检问题分级记录 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-problem-level-rec:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectProblemLevelRecExcel(@Valid InspectProblemLevelRecPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectProblemLevelRecDO> list = inspectProblemLevelRecService.getInspectProblemLevelRecPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检问题分级记录.xls", "数据", InspectProblemLevelRecRespVO.class,
                        BeanUtils.toBean(list, InspectProblemLevelRecRespVO.class));
    }

}