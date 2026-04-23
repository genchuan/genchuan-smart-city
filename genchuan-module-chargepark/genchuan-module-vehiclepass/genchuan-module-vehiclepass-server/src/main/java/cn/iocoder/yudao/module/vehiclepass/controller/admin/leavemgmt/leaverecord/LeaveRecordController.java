package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordUpdateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordCorrectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.leaverecord.LeaveRecordDO;
import cn.iocoder.yudao.module.vehiclepass.service.leavemgmt.leaverecord.LeaveRecordService;
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


@Tag(name = "管理后台 - 离场记录")
@RestController
@RequestMapping("/leave/record")
@Validated
public class LeaveRecordController {

    @Resource
    private LeaveRecordService leaveRecordService;

    @PostMapping("/create")
    @Operation(summary = "补录离场记录")
    @PreAuthorize("@ss.hasPermission('vehiclepass:leave-record:create')")
    public CommonResult<Long> createRecordSupplement(@Valid @RequestBody LeaveRecordCreateReqVO reqVO) {
        return success(leaveRecordService.createRecordSupplement(reqVO));
    }

    @PostMapping("/original-create")
    @Operation(summary = "创建离场记录")
    @PreAuthorize("@ss.hasPermission('leave:record:create')")
    public CommonResult<Long> createRecord(@Valid @RequestBody LeaveRecordSaveReqVO createReqVO) {
        return success(leaveRecordService.createRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新离场记录")
    @PreAuthorize("@ss.hasPermission('leave:record:update')")
    public CommonResult<Boolean> updateRecord(@Valid @RequestBody LeaveRecordSaveReqVO updateReqVO) {
        leaveRecordService.updateRecord(updateReqVO);
        return success(true);
    }

    @PutMapping("/edit")
    @Operation(summary = "编辑离场记录")
    @PreAuthorize("@ss.hasPermission('vehiclepass:leave-record:update')")
    public CommonResult<Boolean> updateRecordForEdit(@Valid @RequestBody LeaveRecordUpdateReqVO reqVO) {
        leaveRecordService.updateRecordForEdit(reqVO);
        return success(true);
    }

    @PutMapping("/correct")
    @Operation(summary = "修正离场记录")
    @PreAuthorize("@ss.hasPermission('vehiclepass:leave-record:correct')")
    public CommonResult<Boolean> correctRecord(@Valid @RequestBody LeaveRecordCorrectReqVO reqVO) {
        leaveRecordService.correctRecord(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除离场记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('leave:record:delete')")
    public CommonResult<Boolean> deleteRecord(@RequestParam("id") Long id) {
        leaveRecordService.deleteRecord(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除离场记录")
    @PreAuthorize("@ss.hasPermission('leave:record:delete')")
    public CommonResult<Boolean> deleteRecordList(@RequestParam("ids") List<Long> ids) {
        leaveRecordService.deleteRecordListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得离场记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('leave:record:query')")
    public CommonResult<LeaveRecordRespVO> getRecord(@RequestParam("id") Long id) {
        LeaveRecordDO record = leaveRecordService.getRecord(id);
        return success(BeanUtils.toBean(record, LeaveRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得离场记录分页")
    @PreAuthorize("@ss.hasPermission('leave:record:query')")
    public CommonResult<PageResult<LeaveRecordRespVO>> getRecordPage(@Valid LeaveRecordPageReqVO pageReqVO) {
        PageResult<LeaveRecordDO> pageResult = leaveRecordService.getRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LeaveRecordRespVO.class));
    }

    @GetMapping("/my/page")
    @Operation(summary = "离场记录筛选刷新")
    @PreAuthorize("@ss.hasPermission('vehiclepass:leave-record:query')")
    public CommonResult<PageResult<LeaveRecordRespVO>> getMyRecordPage(@Valid LeaveRecordPageReqVO pageReqVO) {
        return success(leaveRecordService.getRecordPageWithJoin(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出离场记录 Excel")
    @PreAuthorize("@ss.hasPermission('leave:record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRecordExcel(@Valid LeaveRecordPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<LeaveRecordRespVO> pageResult = leaveRecordService.getRecordPageWithJoin(pageReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "离场记录.xls", "数据", LeaveRecordRespVO.class,
                pageResult.getList());
    }

    @GetMapping("/chart")
    @Operation(summary = "获取离场记录统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:leave-record:chart')")
    public CommonResult<LeaveRecordChartRespVO> getChart(@Valid LeaveRecordChartReqVO reqVO) {
        return success(leaveRecordService.getChart(reqVO));
    }

}