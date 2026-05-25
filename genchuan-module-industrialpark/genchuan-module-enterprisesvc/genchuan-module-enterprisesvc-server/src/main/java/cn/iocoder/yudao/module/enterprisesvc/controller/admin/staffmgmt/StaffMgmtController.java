package cn.iocoder.yudao.module.enterprisesvc.controller.admin.staffmgmt;

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

import cn.iocoder.yudao.module.enterprisesvc.controller.admin.staffmgmt.vo.*;
import cn.iocoder.yudao.module.enterprisesvc.dal.dataobject.staffmgmt.StaffMgmtDO;
import cn.iocoder.yudao.module.enterprisesvc.service.staffmgmt.StaffMgmtService;

@Tag(name = "管理后台 - 企业员工")
@RestController
@RequestMapping("/enterprisesvc/staff-mgmt")
@Validated
public class StaffMgmtController {

    @Resource
    private StaffMgmtService staffMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建企业员工")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:staff-mgmt:create')")
    public CommonResult<Long> createStaffMgmt(@Valid @RequestBody StaffMgmtSaveReqVO createReqVO) {
        return success(staffMgmtService.createStaffMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新企业员工")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:staff-mgmt:update')")
    public CommonResult<Boolean> updateStaffMgmt(@Valid @RequestBody StaffMgmtSaveReqVO updateReqVO) {
        staffMgmtService.updateStaffMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除企业员工")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('enterprisesvc:staff-mgmt:delete')")
    public CommonResult<Boolean> deleteStaffMgmt(@RequestParam("id") Long id) {
        staffMgmtService.deleteStaffMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除企业员工")
                @PreAuthorize("@ss.hasPermission('enterprisesvc:staff-mgmt:delete')")
    public CommonResult<Boolean> deleteStaffMgmtList(@RequestParam("ids") List<Long> ids) {
        staffMgmtService.deleteStaffMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得企业员工")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:staff-mgmt:query')")
    public CommonResult<StaffMgmtRespVO> getStaffMgmt(@RequestParam("id") Long id) {
        StaffMgmtDO staffMgmt = staffMgmtService.getStaffMgmt(id);
        return success(BeanUtils.toBean(staffMgmt, StaffMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得企业员工分页")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:staff-mgmt:query')")
    public CommonResult<PageResult<StaffMgmtRespVO>> getStaffMgmtPage(@Valid StaffMgmtPageReqVO pageReqVO) {
        PageResult<StaffMgmtDO> pageResult = staffMgmtService.getStaffMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StaffMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出企业员工 Excel")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:staff-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStaffMgmtExcel(@Valid StaffMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StaffMgmtDO> list = staffMgmtService.getStaffMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "企业员工.xls", "数据", StaffMgmtRespVO.class,
                        BeanUtils.toBean(list, StaffMgmtRespVO.class));
    }

}