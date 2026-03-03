/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.user;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendance.AttendancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendance.AttendanceRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendance.AttendanceSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceDO;
import cn.iocoder.yudao.module.envirhealth.service.user.attendance.AttendanceService;

@Tag(name = "管理后台 - 考勤")
@RestController
@RequestMapping("/envirhealth/attendance")
@Validated
public class AttendanceController {

    @Resource
    private AttendanceService attendanceService;

    @PostMapping("/create")
    @Operation(summary = "创建考勤")
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance:create')")
    public CommonResult<Long> createAttendance(@Valid @RequestBody AttendanceSaveReqVO createReqVO) {
        return success(attendanceService.createAttendance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新考勤")
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance:update')")
    public CommonResult<Boolean> updateAttendance(@Valid @RequestBody AttendanceSaveReqVO updateReqVO) {
        attendanceService.updateAttendance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除考勤")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance:delete')")
    public CommonResult<Boolean> deleteAttendance(@RequestParam("id") Long id) {
        attendanceService.deleteAttendance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得考勤")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance:query')")
    public CommonResult<AttendanceRespVO> getAttendance(@RequestParam("id") Long id) {
        AttendanceDO attendance = attendanceService.getAttendance(id);
        return success(BeanUtils.toBean(attendance, AttendanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得考勤分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance:query')")
    public CommonResult<PageResult<AttendanceRespVO>> getAttendancePage(@Valid AttendancePageReqVO pageReqVO) {
        PageResult<AttendanceDO> pageResult = attendanceService.getAttendancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AttendanceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出考勤 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAttendanceExcel(@Valid AttendancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AttendanceDO> list = attendanceService.getAttendancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "考勤.xls", "数据", AttendanceRespVO.class,
                        BeanUtils.toBean(list, AttendanceRespVO.class));
    }

}*/
