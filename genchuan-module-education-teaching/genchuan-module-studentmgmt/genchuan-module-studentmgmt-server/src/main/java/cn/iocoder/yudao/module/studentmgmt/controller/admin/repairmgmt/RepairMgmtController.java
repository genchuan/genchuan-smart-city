package cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.repairmgmt.RepairMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.repairmgmt.RepairMgmtService;

@Tag(name = "学生管理后台 - 报修管理")
@RestController
@RequestMapping("/studentmgmt/repair-mgmt")
@Validated
public class RepairMgmtController {

    @Resource
    private RepairMgmtService repairMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建报修管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:create')")
    public CommonResult<Long> createRepairMgmt(@Valid @RequestBody RepairMgmtSaveReqVO createReqVO) {
        return success(repairMgmtService.createRepairMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报修管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:update')")
    public CommonResult<Boolean> updateRepairMgmt(@Valid @RequestBody RepairMgmtSaveReqVO updateReqVO) {
        repairMgmtService.updateRepairMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报修管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:delete')")
    public CommonResult<Boolean> deleteRepairMgmt(@RequestParam("id") Long id) {
        repairMgmtService.deleteRepairMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除报修管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:delete')")
    public CommonResult<Boolean> deleteRepairMgmtList(@RequestParam("ids") List<Long> ids) {
        repairMgmtService.deleteRepairMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报修管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:query')")
    public CommonResult<RepairMgmtRespVO> getRepairMgmt(@RequestParam("id") Long id) {
        RepairMgmtDO repairMgmt = repairMgmtService.getRepairMgmt(id);
        return success(BeanUtils.toBean(repairMgmt, RepairMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报修管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:query')")
    public CommonResult<PageResult<RepairMgmtRespVO>> getRepairMgmtPage(@Valid RepairMgmtPageReqVO pageReqVO) {
        PageResult<RepairMgmtDO> pageResult = repairMgmtService.getRepairMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RepairMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报修管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRepairMgmtExcel(@Valid RepairMgmtPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RepairMgmtDO> list = repairMgmtService.getRepairMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "报修管理.xls", "数据", RepairMgmtRespVO.class,
                BeanUtils.toBean(list, RepairMgmtRespVO.class));
    }

    @PutMapping("/assign")
    @Operation(summary = "派单")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:assign')")
    public CommonResult<Boolean> assign(@Valid @RequestBody RepairMgmtAssignReqVO reqVO) {
        Boolean isSuccess = repairMgmtService.assign(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/feedback")
    @Operation(summary = "派单")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:feedback')")
    public CommonResult<Boolean> feedback(@Valid @RequestBody RepairMgmtFeedbackReqVO reqVO) {
        Boolean isSuccess = repairMgmtService.feedback(reqVO);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "宿舍报修处置看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:query')")
    public CommonResult<RepairMgmtChartRespVO> chart(@Valid @RequestBody RepairMgmtChartReqVO reqVO) {
        RepairMgmtChartRespVO vo = repairMgmtService.chart(reqVO);
        return success(vo);
    }

    @GetMapping("/repairCount")
    @Operation(summary = "报修类型 / 维修完成率统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:query')")
    public CommonResult<RepairMgmtCountRespVO> repairCount(@Valid RepairMgmtCountReqVO reqVO) {
        RepairMgmtCountRespVO vo = repairMgmtService.repairCount(reqVO);
        return success(vo);
    }


}