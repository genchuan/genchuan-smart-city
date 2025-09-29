package cn.iocoder.yudao.module.datacenter.controller.admin.inspectionstaff;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.inspectionstaff.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspectionstaff.InspectionStaffDO;
import cn.iocoder.yudao.module.datacenter.service.inspectionstaff.InspectionStaffService;

@Tag(name = "管理后台 - 巡查人员信息")
@RestController
@RequestMapping("/datacenter/inspection-staff")
@Validated
public class InspectionStaffController {

    @Resource
    private InspectionStaffService inspectionStaffService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查人员信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-staff:create')")
    public CommonResult<Long> createInspectionStaff(@Valid @RequestBody InspectionStaffSaveReqVO createReqVO) {
        return success(inspectionStaffService.createInspectionStaff(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查人员信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-staff:update')")
    public CommonResult<Boolean> updateInspectionStaff(@Valid @RequestBody InspectionStaffSaveReqVO updateReqVO) {
        inspectionStaffService.updateInspectionStaff(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查人员信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-staff:delete')")
    public CommonResult<Boolean> deleteInspectionStaff(@RequestParam("id") Long id) {
        inspectionStaffService.deleteInspectionStaff(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查人员信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-staff:query')")
    public CommonResult<InspectionStaffRespVO> getInspectionStaff(@RequestParam("id") Long id) {
        InspectionStaffDO inspectionStaff = inspectionStaffService.getInspectionStaff(id);
        return success(BeanUtils.toBean(inspectionStaff, InspectionStaffRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查人员信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-staff:query')")
    public CommonResult<PageResult<InspectionStaffRespVO>> getInspectionStaffPage(@Valid InspectionStaffPageReqVO pageReqVO) {
        PageResult<InspectionStaffDO> pageResult = inspectionStaffService.getInspectionStaffPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionStaffRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得全部巡查人员信息列表")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-staff:query')")
    public CommonResult<List<InspectionStaffRespVO>> getInspectionStaffList() {
        List<InspectionStaffDO> list = inspectionStaffService.getInspectionStaffList();
        return success(BeanUtils.toBean(list, InspectionStaffRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查人员信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-staff:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectionStaffExcel(@Valid InspectionStaffPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionStaffDO> list = inspectionStaffService.getInspectionStaffPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查人员信息.xls", "数据", InspectionStaffRespVO.class,
                        BeanUtils.toBean(list, InspectionStaffRespVO.class));
    }

}