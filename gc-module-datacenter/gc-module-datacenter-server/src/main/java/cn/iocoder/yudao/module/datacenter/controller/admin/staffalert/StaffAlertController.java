package cn.iocoder.yudao.module.datacenter.controller.admin.staffalert;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.staffalert.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffalert.StaffAlertDO;
import cn.iocoder.yudao.module.datacenter.service.staffalert.StaffAlertService;

@Tag(name = "管理后台 - 人员异常报警")
@RestController
@RequestMapping("/datacenter/staff-alert")
@Validated
public class StaffAlertController {

    @Resource
    private StaffAlertService staffAlertService;

    @PostMapping("/create")
    @Operation(summary = "创建人员异常报警")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-alert:create')")
    public CommonResult<Long> createStaffAlert(@Valid @RequestBody StaffAlertSaveReqVO createReqVO) {
        return success(staffAlertService.createStaffAlert(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新人员异常报警")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-alert:update')")
    public CommonResult<Boolean> updateStaffAlert(@Valid @RequestBody StaffAlertSaveReqVO updateReqVO) {
        staffAlertService.updateStaffAlert(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人员异常报警")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:staff-alert:delete')")
    public CommonResult<Boolean> deleteStaffAlert(@RequestParam("id") Long id) {
        staffAlertService.deleteStaffAlert(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人员异常报警")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-alert:query')")
    public CommonResult<StaffAlertRespVO> getStaffAlert(@RequestParam("id") Long id) {
        StaffAlertDO staffAlert = staffAlertService.getStaffAlert(id);
        return success(BeanUtils.toBean(staffAlert, StaffAlertRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得人员异常报警分页")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-alert:query')")
    public CommonResult<PageResult<StaffAlertRespVO>> getStaffAlertPage(@Valid StaffAlertPageReqVO pageReqVO) {
        PageResult<StaffAlertDO> pageResult = staffAlertService.getStaffAlertPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StaffAlertRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得所有人员异常报警列表")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-alert:query')")
    public CommonResult<List<StaffAlertRespVO>> getStaffAlertList() {
        List<StaffAlertDO> list = staffAlertService.getStaffAlertList();
        return success(BeanUtils.toBean(list, StaffAlertRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出人员异常报警 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:staff-alert:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStaffAlertExcel(@Valid StaffAlertPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StaffAlertDO> list = staffAlertService.getStaffAlertPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "人员异常报警.xls", "数据", StaffAlertRespVO.class,
                        BeanUtils.toBean(list, StaffAlertRespVO.class));
    }

}