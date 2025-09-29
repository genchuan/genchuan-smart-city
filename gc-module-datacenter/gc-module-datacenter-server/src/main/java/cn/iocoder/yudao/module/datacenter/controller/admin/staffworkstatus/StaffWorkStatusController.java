package cn.iocoder.yudao.module.datacenter.controller.admin.staffworkstatus;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.staffworkstatus.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffworkstatus.StaffWorkStatusDO;
import cn.iocoder.yudao.module.datacenter.service.staffworkstatus.StaffWorkStatusService;

@Tag(name = "管理后台 - 人员作业状态")
@RestController
@RequestMapping("/datacenter/staff-work-status")
@Validated
public class StaffWorkStatusController {

    @Resource
    private StaffWorkStatusService staffWorkStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建人员作业状态")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-work-status:create')")
    public CommonResult<Long> createStaffWorkStatus(@Valid @RequestBody StaffWorkStatusSaveReqVO createReqVO) {
        return success(staffWorkStatusService.createStaffWorkStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新人员作业状态")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-work-status:update')")
    public CommonResult<Boolean> updateStaffWorkStatus(@Valid @RequestBody StaffWorkStatusSaveReqVO updateReqVO) {
        staffWorkStatusService.updateStaffWorkStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人员作业状态")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:staff-work-status:delete')")
    public CommonResult<Boolean> deleteStaffWorkStatus(@RequestParam("id") Long id) {
        staffWorkStatusService.deleteStaffWorkStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人员作业状态")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-work-status:query')")
    public CommonResult<StaffWorkStatusRespVO> getStaffWorkStatus(@RequestParam("id") Long id) {
        StaffWorkStatusDO staffWorkStatus = staffWorkStatusService.getStaffWorkStatus(id);
        return success(BeanUtils.toBean(staffWorkStatus, StaffWorkStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得人员作业状态分页")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-work-status:query')")
    public CommonResult<PageResult<StaffWorkStatusRespVO>> getStaffWorkStatusPage(@Valid StaffWorkStatusPageReqVO pageReqVO) {
        PageResult<StaffWorkStatusDO> pageResult = staffWorkStatusService.getStaffWorkStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StaffWorkStatusRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得所有人员作业状态")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-work-status:query')")
    public CommonResult<List<StaffWorkStatusRespVO>> getAllStaffWorkStatus() {
        List<StaffWorkStatusDO> list = staffWorkStatusService.getAllStaffWorkStatus();
        return success(BeanUtils.toBean(list, StaffWorkStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出人员作业状态 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-work-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStaffWorkStatusExcel(@Valid StaffWorkStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StaffWorkStatusDO> list = staffWorkStatusService.getStaffWorkStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "人员作业状态.xls", "数据", StaffWorkStatusRespVO.class,
                        BeanUtils.toBean(list, StaffWorkStatusRespVO.class));
    }

}