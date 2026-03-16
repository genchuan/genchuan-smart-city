/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle;

import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationstatus.ViolationStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationstatus.ViolationStatusRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationstatus.ViolationStatusSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.ViolationStatusDO;
import cn.iocoder.yudao.module.envirhealth.service.vehicle.violationstatus.ViolationStatusService;

@Tag(name = "管理后台 - 违规状态字典表")
@RestController
@RequestMapping("/envirhealth/violation-status")
@Validated
public class ViolationStatusController {

    @Resource
    private ViolationStatusService violationStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建违规状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-status:create')")
    public CommonResult<Long> createViolationStatus(@Valid @RequestBody ViolationStatusSaveReqVO createReqVO) {
        return success(violationStatusService.createViolationStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违规状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-status:update')")
    public CommonResult<Boolean> updateViolationStatus(@Valid @RequestBody ViolationStatusSaveReqVO updateReqVO) {
        violationStatusService.updateViolationStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违规状态字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-status:delete')")
    public CommonResult<Boolean> deleteViolationStatus(@RequestParam("id") Long id) {
        violationStatusService.deleteViolationStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得违规状态字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-status:query')")
    public CommonResult<ViolationStatusRespVO> getViolationStatus(@RequestParam("id") Long id) {
        ViolationStatusDO violationStatus = violationStatusService.getViolationStatus(id);
        return success(BeanUtils.toBean(violationStatus, ViolationStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违规状态字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-status:query')")
    public CommonResult<PageResult<ViolationStatusRespVO>> getViolationStatusPage(@Valid ViolationStatusPageReqVO pageReqVO) {
        PageResult<ViolationStatusDO> pageResult = violationStatusService.getViolationStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ViolationStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出违规状态字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportViolationStatusExcel(@Valid ViolationStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ViolationStatusDO> list = violationStatusService.getViolationStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "违规状态字典表.xls", "数据", ViolationStatusRespVO.class,
                        BeanUtils.toBean(list, ViolationStatusRespVO.class));
    }

}*/
