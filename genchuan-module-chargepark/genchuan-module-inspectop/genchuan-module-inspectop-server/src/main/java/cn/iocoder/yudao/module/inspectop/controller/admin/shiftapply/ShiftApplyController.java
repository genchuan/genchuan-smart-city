package cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply;

import io.swagger.v3.oas.annotations.Hidden;
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

import cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.shiftapply.ShiftApplyDO;
import cn.iocoder.yudao.module.inspectop.service.shiftapply.ShiftApplyService;

@Tag(name = "巡查巡检 - 换班申请")
@RestController
@RequestMapping("/inspectop/shift-apply")
@Validated
public class ShiftApplyController {

    @Resource
    private ShiftApplyService shiftApplyService;

    @PostMapping("/create")
    @Operation(summary = "创建换班申请")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:create')")
    public CommonResult<Long> createShiftApply(@Valid @RequestBody ShiftApplySaveReqVO createReqVO) {
        return success(shiftApplyService.createShiftApply(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新换班申请")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:update')")
    public CommonResult<Boolean> updateShiftApply(@Valid @RequestBody ShiftApplySaveReqVO updateReqVO) {
        shiftApplyService.updateShiftApply(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除换班申请")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:delete')")
    public CommonResult<Boolean> deleteShiftApply(@RequestParam("id") Long id) {
        shiftApplyService.deleteShiftApply(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除换班申请")
                @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:delete')")
    public CommonResult<Boolean> deleteShiftApplyList(@RequestParam("ids") List<Long> ids) {
        shiftApplyService.deleteShiftApplyListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得换班申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:query')")
    public CommonResult<ShiftApplyRespVO> getShiftApply(@RequestParam("id") Long id) {
        ShiftApplyRespVO shiftApply = shiftApplyService.getShiftApply(id);
        return success(shiftApply);
    }

    @GetMapping("/page")
    @Operation(summary = "获得换班申请分页")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:query')")
    public CommonResult<PageResult<ShiftApplyRespVO>> getShiftApplyPage(@Valid ShiftApplyPageReqVO pageReqVO) {
        PageResult<ShiftApplyRespVO> pageResult = shiftApplyService.getShiftApplyPage(pageReqVO);
        return success(pageResult);
    }

    @PutMapping("/batch-audit")
    @Operation(summary = "批量审核换班申请")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:batch-audit')")
    public CommonResult<Boolean> batchAuditShiftApply(@Valid @RequestBody ShiftApplyBatchAuditReqVO reqVO) {
        shiftApplyService.batchAuditShiftApply(reqVO);
        return success(true);
    }

    @PutMapping("/approve")
    @Operation(summary = "通过换班申请")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:approve')")
    public CommonResult<Boolean> approveShiftApply(@Valid @RequestBody ShiftApplyApproveReqVO reqVO) {
        shiftApplyService.approveShiftApply(reqVO);
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "驳回换班申请")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:reject')")
    public CommonResult<Boolean> rejectShiftApply(@Valid @RequestBody ShiftApplyRejectReqVO reqVO) {
        shiftApplyService.rejectShiftApply(reqVO);
        return success(true);
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认换班申请")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:confirm')")
    public CommonResult<Boolean> confirmShiftApply(@Valid @RequestBody ShiftApplyConfirmReqVO reqVO) {
        shiftApplyService.confirmShiftApply(reqVO);
        return success(true);
    }

    @PostMapping("/reapply")
    @Operation(summary = "重新申请换班")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:reapply')")
    public CommonResult<Boolean> reapplyShiftApply(@Valid @RequestBody ShiftApplyReapplyReqVO reqVO) {
        shiftApplyService.reapplyShiftApply(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取换班申请统计图表")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:chart')")
    public CommonResult<ShiftApplyChartRespVO> getShiftApplyChart(@Valid ShiftApplyChartReqVO reqVO) {
        ShiftApplyChartRespVO chartData = shiftApplyService.getShiftApplyChart(reqVO);
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出换班申请 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:shift-apply:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportShiftApplyExcel(@Valid ShiftApplyPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        // 1. 获取数据列表
        List<ShiftApplyRespVO> list = shiftApplyService.getShiftApplyPage(pageReqVO).getList();

        // 【新增】2. 对VO列表中的字典值进行转换（数字 -> 中文）
        convertShiftApplyDictValues(list);

        // 3. 导出 Excel
        ExcelUtils.write(response, "换班申请.xls", "数据", ShiftApplyRespVO.class, list);
    }

    /**
     * 【新增】转换换班申请状态字典值为中文显示
     * 此方法会修改传入的 voList 中每个对象的 status 字段
     * @param voList 换班申请响应VO列表
     */
    private void convertShiftApplyDictValues(List<ShiftApplyRespVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return;
        }
        for (ShiftApplyRespVO vo : voList) {
            // 转换申请状态
            vo.setStatus(convertShiftApplyStatus(vo.getStatus()));
        }
    }

    /**
     * 【新增】转换换班申请状态字典值
     * 根据您提供的映射：1-已重新申请，2-已驳回，3-已通过，4-待审核
     * @param statusCode 状态编码（例如 "1", "2"）
     * @return 对应的中文状态描述
     */
    private String convertShiftApplyStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode.trim()) {
            case "1":
                return "已重新申请";
            case "2":
                return "已驳回";
            case "3":
                return "已通过";
            case "4":
                return "待审核";
            default:
                // 如果遇到未知编码，可以选择返回原编码或空字符串，这里返回原编码以便排查
                return statusCode;
        }
    }

}