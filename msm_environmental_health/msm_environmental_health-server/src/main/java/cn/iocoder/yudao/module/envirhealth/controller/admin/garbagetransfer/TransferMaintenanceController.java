package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenanceRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenanceSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferMaintenanceDetailDO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transfermaintenance.TransferMaintenanceService;

@Tag(name = "环境卫生管理 - 设备维护")
@RestController
@RequestMapping("/envirhealth/transfer-maintenance")
@Validated
public class TransferMaintenanceController {

    @Resource
    private TransferMaintenanceService transferMaintenanceService;

    @PostMapping("/create")
    @Operation(summary = "创建设备维护")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-maintenance:create')")
    public CommonResult<Long> createTransferMaintenance(@Valid @RequestBody TransferMaintenanceSaveReqVO createReqVO) {
        return success(transferMaintenanceService.createTransferMaintenance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备维护")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-maintenance:update')")
    public CommonResult<Boolean> updateTransferMaintenance(@Valid @RequestBody TransferMaintenanceSaveReqVO updateReqVO) {
        transferMaintenanceService.updateTransferMaintenance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备维护")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-maintenance:delete')")
    public CommonResult<Boolean> deleteTransferMaintenance(@RequestParam("id") Long id) {
        transferMaintenanceService.deleteTransferMaintenance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备维护")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-maintenance:query')")
    public CommonResult<TransferMaintenanceRespVO> getTransferMaintenance(@RequestParam("id") Long id) {
        TransferMaintenanceDO transferMaintenance = transferMaintenanceService.getTransferMaintenance(id);
        return success(BeanUtils.toBean(transferMaintenance, TransferMaintenanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备维护分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-maintenance:query')")
    public CommonResult<PageResult<TransferMaintenanceRespVO>> getTransferMaintenancePage(@Valid TransferMaintenancePageReqVO pageReqVO) {
        PageResult<TransferMaintenanceDO> pageResult = transferMaintenanceService.getTransferMaintenancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TransferMaintenanceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备维护 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-maintenance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTransferMaintenanceExcel(@Valid TransferMaintenancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TransferMaintenanceDO> list = transferMaintenanceService.getTransferMaintenancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备维护.xls", "数据", TransferMaintenanceRespVO.class,
                        BeanUtils.toBean(list, TransferMaintenanceRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得设备维护详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:transfer-maintenance:query')")
    public CommonResult<PageResult<TransferMaintenanceDetailDO>> getPublicToiletDetailPage(
            @Valid TransferMaintenancePageReqVO pageReqVO) {
        PageResult<TransferMaintenanceDetailDO> pageResult =
                transferMaintenanceService.getTransferMaintenanceDetailPage(pageReqVO);

        return success(pageResult);
    }
}