package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeavePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveCheckReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveIgnoreReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveUpdateProgressReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.abnormalleave.AbnormalLeaveDO;
import cn.iocoder.yudao.module.vehiclepass.service.leavemgmt.abnormalleave.AbnormalLeaveService;
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

@Tag(name = "管理后台 - 异常离场")
@RestController
@RequestMapping("/vehiclepass/abnormal-leave")
@Validated
public class AbnormalLeaveController {

    @Resource
    private AbnormalLeaveService leaveService;

    @PostMapping("/create")
    @Operation(summary = "创建异常离场")
    @PreAuthorize("@ss.hasPermission('abnormal:leave:create')")
    public CommonResult<Long> createLeave(@Valid @RequestBody AbnormalLeaveSaveReqVO createReqVO) {
        return success(leaveService.createLeave(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新异常离场")
    @PreAuthorize("@ss.hasPermission('abnormal:leave:update')")
    public CommonResult<Boolean> updateLeave(@Valid @RequestBody AbnormalLeaveSaveReqVO updateReqVO) {
        leaveService.updateLeave(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除异常离场")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('abnormal:leave:delete')")
    public CommonResult<Boolean> deleteLeave(@RequestParam("id") Long id) {
        leaveService.deleteLeave(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除异常离场")
    @PreAuthorize("@ss.hasPermission('abnormal:leave:delete')")
    public CommonResult<Boolean> deleteLeaveList(@RequestParam("ids") List<Long> ids) {
        leaveService.deleteLeaveListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得异常离场")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('abnormal:leave:query')")
    public CommonResult<AbnormalLeaveRespVO> getLeave(@RequestParam("id") Long id) {
        return success(leaveService.getLeaveWithStation(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得异常离场分页")
    @PreAuthorize("@ss.hasPermission('abnormal:leave:query')")
    public CommonResult<PageResult<AbnormalLeaveRespVO>> getLeavePage(@Valid AbnormalLeavePageReqVO pageReqVO) {
        return success(leaveService.getLeavePageWithJoin(pageReqVO));
    }

    @PostMapping("/batch-handle")
    @Operation(summary = "批量处置异常离场")
    @PreAuthorize("@ss.hasPermission('vehiclepass:abnormal-leave:batch-handle')")
    public CommonResult<Boolean> batchHandle(@Valid @RequestBody AbnormalLeaveBatchHandleReqVO reqVO) {
        leaveService.batchHandle(reqVO);
        return success(true);
    }

    @PutMapping("/check")
    @Operation(summary = "核查异常离场")
    @PreAuthorize("@ss.hasPermission('vehiclepass:abnormal-leave:check')")
    public CommonResult<Boolean> checkLeave(@Valid @RequestBody AbnormalLeaveCheckReqVO reqVO) {
        leaveService.checkLeave(reqVO);
        return success(true);
    }

    @PutMapping("/ignore")
    @Operation(summary = "忽略异常离场")
    @PreAuthorize("@ss.hasPermission('vehiclepass:abnormal-leave:ignore')")
    public CommonResult<Boolean> ignoreLeave(@Valid @RequestBody AbnormalLeaveIgnoreReqVO reqVO) {
        leaveService.ignoreLeave(reqVO);
        return success(true);
    }

    @PutMapping("/update-progress")
    @Operation(summary = "更新处置进度")
    @PreAuthorize("@ss.hasPermission('vehiclepass:abnormal-leave:update-progress')")
    public CommonResult<Boolean> updateProgress(@Valid @RequestBody AbnormalLeaveUpdateProgressReqVO reqVO) {
        leaveService.updateProgress(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "异常离场统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:abnormal-leave:chart')")
    public CommonResult<AbnormalLeaveChartRespVO> getChart(@Valid AbnormalLeaveChartReqVO reqVO) {
        return success(leaveService.getChart(reqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出异常离场 Excel")
    @PreAuthorize("@ss.hasPermission('abnormal:leave:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLeaveExcel(AbnormalLeavePageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageNo(1);
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<AbnormalLeaveRespVO> pageResult = leaveService.getLeavePageWithJoin(pageReqVO);
        ExcelUtils.write(response, "异常离场.xls", "数据", AbnormalLeaveRespVO.class, pageResult.getList());
    }

}