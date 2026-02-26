/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.user;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendanceabnormaltype.AttendanceAbnormalTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendanceabnormaltype.AttendanceAbnormalTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendanceabnormaltype.AttendanceAbnormalTypeSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceAbnormalTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.user.attendanceabnormaltype.AttendanceAbnormalTypeService;

@Tag(name = "管理后台 - 考勤异常类型字典表")
@RestController
@RequestMapping("/envirhealth/attendance-abnormal-type")
@Validated
public class AttendanceAbnormalTypeController {

    @Resource
    private AttendanceAbnormalTypeService attendanceAbnormalTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建考勤异常类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance-abnormal-type:create')")
    public CommonResult<Long> createAttendanceAbnormalType(@Valid @RequestBody AttendanceAbnormalTypeSaveReqVO createReqVO) {
        return success(attendanceAbnormalTypeService.createAttendanceAbnormalType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新考勤异常类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance-abnormal-type:update')")
    public CommonResult<Boolean> updateAttendanceAbnormalType(@Valid @RequestBody AttendanceAbnormalTypeSaveReqVO updateReqVO) {
        attendanceAbnormalTypeService.updateAttendanceAbnormalType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除考勤异常类型字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance-abnormal-type:delete')")
    public CommonResult<Boolean> deleteAttendanceAbnormalType(@RequestParam("id") Long id) {
        attendanceAbnormalTypeService.deleteAttendanceAbnormalType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得考勤异常类型字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance-abnormal-type:query')")
    public CommonResult<AttendanceAbnormalTypeRespVO> getAttendanceAbnormalType(@RequestParam("id") Long id) {
        AttendanceAbnormalTypeDO attendanceAbnormalType = attendanceAbnormalTypeService.getAttendanceAbnormalType(id);
        return success(BeanUtils.toBean(attendanceAbnormalType, AttendanceAbnormalTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得考勤异常类型字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance-abnormal-type:query')")
    public CommonResult<PageResult<AttendanceAbnormalTypeRespVO>> getAttendanceAbnormalTypePage(@Valid AttendanceAbnormalTypePageReqVO pageReqVO) {
        PageResult<AttendanceAbnormalTypeDO> pageResult = attendanceAbnormalTypeService.getAttendanceAbnormalTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AttendanceAbnormalTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出考勤异常类型字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:attendance-abnormal-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAttendanceAbnormalTypeExcel(@Valid AttendanceAbnormalTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AttendanceAbnormalTypeDO> list = attendanceAbnormalTypeService.getAttendanceAbnormalTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "考勤异常类型字典表.xls", "数据", AttendanceAbnormalTypeRespVO.class,
                        BeanUtils.toBean(list, AttendanceAbnormalTypeRespVO.class));
    }

}*/
