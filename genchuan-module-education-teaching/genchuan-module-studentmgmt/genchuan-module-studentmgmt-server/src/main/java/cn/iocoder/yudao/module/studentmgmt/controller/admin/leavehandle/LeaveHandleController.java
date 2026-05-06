package cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.leavehandle.LeaveHandleDO;
import cn.iocoder.yudao.module.studentmgmt.service.leavehandle.LeaveHandleService;

@Tag(name = "学生管理后台 - 离校办理")
@RestController
@RequestMapping("/studentmgmt/leave-handle")
@Validated
public class LeaveHandleController {

    @Resource
    private LeaveHandleService leaveHandleService;

    @PostMapping("/create")
    @Operation(summary = "创建离校办理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:create')")
    public CommonResult<Long> createLeaveHandle(@Valid @RequestBody LeaveHandleCreateReqVO createReqVO) {
        return success(leaveHandleService.createLeaveHandle(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新离校办理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:update')")
    public CommonResult<Boolean> updateLeaveHandle(@Valid @RequestBody LeaveHandleSaveReqVO updateReqVO) {
        leaveHandleService.updateLeaveHandle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除离校办理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:delete')")
    public CommonResult<Boolean> deleteLeaveHandle(@RequestParam("id") Long id) {
        leaveHandleService.deleteLeaveHandle(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除离校办理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:delete')")
    public CommonResult<Boolean> deleteLeaveHandleList(@RequestParam("ids") List<Long> ids) {
        leaveHandleService.deleteLeaveHandleListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得离校办理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:query')")
    public CommonResult<LeaveHandleRespVO> getLeaveHandle(@RequestParam("id") Long id) {
        LeaveHandleDO leaveHandle = leaveHandleService.getLeaveHandle(id);
        return success(BeanUtils.toBean(leaveHandle, LeaveHandleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得离校办理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:query')")
    public CommonResult<PageResult<LeaveHandleRespVO>> getLeaveHandlePage(@Valid LeaveHandlePageReqVO pageReqVO) {
        PageResult<LeaveHandleDO> pageResult = leaveHandleService.getLeaveHandlePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LeaveHandleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出离校办理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLeaveHandleExcel(@Valid LeaveHandlePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LeaveHandleDO> list = leaveHandleService.getLeaveHandlePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "离校办理.xls", "数据", LeaveHandleRespVO.class,
                        BeanUtils.toBean(list, LeaveHandleRespVO.class));
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认")
    @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:confirm')")
    public CommonResult<Boolean> confirm(@Valid @RequestBody LeaveHandleConfirmReqVO reqVO) {
        return success(leaveHandleService.confirm(reqVO));
    }

    @PutMapping("/handle")
    @Operation(summary = "办理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:handle')")
    public CommonResult<Boolean> handle(@Valid @RequestBody LeaveHandleHandleReqVO reqVO) {
        return success(leaveHandleService.handle(reqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "毕业生离校进度看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:query')")
    public CommonResult<LeaveHandleCharRespVO> chart(@Valid BaseChartReqVO reqVO) {
        return success(leaveHandleService.chart(reqVO));
    }

    @GetMapping("/chart/leaveIndex")
    @Operation(summary = "毕业生离校指标统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:leave-handle:query')")
    public CommonResult<LeaveHandleIndexRespVO> leaveIndex(@Valid BaseChartReqVO reqVO) {
        return success(leaveHandleService.leaveIndex(reqVO));
    }


}