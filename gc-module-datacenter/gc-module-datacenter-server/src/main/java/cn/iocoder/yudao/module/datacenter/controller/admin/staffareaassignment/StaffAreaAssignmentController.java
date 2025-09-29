package cn.iocoder.yudao.module.datacenter.controller.admin.staffareaassignment;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.staffareaassignment.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffareaassignment.StaffAreaAssignmentDO;
import cn.iocoder.yudao.module.datacenter.service.staffareaassignment.StaffAreaAssignmentService;

@Tag(name = "管理后台 - 人员区域分配")
@RestController
@RequestMapping("/datacenter/staff-area-assignment")
@Validated
public class StaffAreaAssignmentController {

    @Resource
    private StaffAreaAssignmentService staffAreaAssignmentService;

    @PostMapping("/create")
    @Operation(summary = "创建人员区域分配")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-area-assignment:create')")
    public CommonResult<Long> createStaffAreaAssignment(@Valid @RequestBody StaffAreaAssignmentSaveReqVO createReqVO) {
        return success(staffAreaAssignmentService.createStaffAreaAssignment(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新人员区域分配")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-area-assignment:update')")
    public CommonResult<Boolean> updateStaffAreaAssignment(@Valid @RequestBody StaffAreaAssignmentSaveReqVO updateReqVO) {
        staffAreaAssignmentService.updateStaffAreaAssignment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人员区域分配")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:staff-area-assignment:delete')")
    public CommonResult<Boolean> deleteStaffAreaAssignment(@RequestParam("id") Long id) {
        staffAreaAssignmentService.deleteStaffAreaAssignment(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人员区域分配")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-area-assignment:query')")
    public CommonResult<StaffAreaAssignmentRespVO> getStaffAreaAssignment(@RequestParam("id") Long id) {
        StaffAreaAssignmentDO staffAreaAssignment = staffAreaAssignmentService.getStaffAreaAssignment(id);
        return success(BeanUtils.toBean(staffAreaAssignment, StaffAreaAssignmentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得人员区域分配分页")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-area-assignment:query')")
    public CommonResult<PageResult<StaffAreaAssignmentRespVO>> getStaffAreaAssignmentPage(@Valid StaffAreaAssignmentPageReqVO pageReqVO) {
        PageResult<StaffAreaAssignmentDO> pageResult = staffAreaAssignmentService.getStaffAreaAssignmentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StaffAreaAssignmentRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获取所有人员区域分配信息")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-area-assignment:query')")
    public CommonResult<List<StaffAreaAssignmentRespVO>> getAllStaffAreaAssignments() {
        List<StaffAreaAssignmentDO> list = staffAreaAssignmentService.getAllStaffAreaAssignments();
        return success(BeanUtils.toBean(list, StaffAreaAssignmentRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出人员区域分配 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-area-assignment:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStaffAreaAssignmentExcel(@Valid StaffAreaAssignmentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StaffAreaAssignmentDO> list = staffAreaAssignmentService.getStaffAreaAssignmentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "人员区域分配.xls", "数据", StaffAreaAssignmentRespVO.class,
                        BeanUtils.toBean(list, StaffAreaAssignmentRespVO.class));
    }

}