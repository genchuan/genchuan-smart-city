package cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentmaintenance;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentmaintenance.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.equipmentmaintenance.EquipmentMaintenanceDO;
import cn.iocoder.yudao.module.waterdetection.service.equipmentmaintenance.EquipmentMaintenanceService;

@Tag(name = "管理后台 - 设备保养计划管理")
@RestController
@RequestMapping("/waterdetection/equipment-maintenance")
@Validated
public class EquipmentMaintenanceController {

    @Resource
    private EquipmentMaintenanceService equipmentMaintenanceService;

    @PostMapping("/create")
    @Operation(summary = "创建设备保养计划管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-maintenance:create')")
    public CommonResult<Long> createEquipmentMaintenance(@Valid @RequestBody EquipmentMaintenanceSaveReqVO createReqVO) {
        return success(equipmentMaintenanceService.createEquipmentMaintenance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备保养计划管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-maintenance:update')")
    public CommonResult<Boolean> updateEquipmentMaintenance(@Valid @RequestBody EquipmentMaintenanceSaveReqVO updateReqVO) {
        equipmentMaintenanceService.updateEquipmentMaintenance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备保养计划管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-maintenance:delete')")
    public CommonResult<Boolean> deleteEquipmentMaintenance(@RequestParam("id") Long id) {
        equipmentMaintenanceService.deleteEquipmentMaintenance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备保养计划管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-maintenance:query')")
    public CommonResult<EquipmentMaintenanceRespVO> getEquipmentMaintenance(@RequestParam("id") Long id) {
        EquipmentMaintenanceDO equipmentMaintenance = equipmentMaintenanceService.getEquipmentMaintenance(id);
        return success(BeanUtils.toBean(equipmentMaintenance, EquipmentMaintenanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备保养计划管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-maintenance:query')")
    public CommonResult<PageResult<EquipmentMaintenanceRespVO>> getEquipmentMaintenancePage(@Valid EquipmentMaintenancePageReqVO pageReqVO) {
        PageResult<EquipmentMaintenanceDO> pageResult = equipmentMaintenanceService.getEquipmentMaintenancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EquipmentMaintenanceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备保养计划管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:equipment-maintenance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEquipmentMaintenanceExcel(@Valid EquipmentMaintenancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EquipmentMaintenanceDO> list = equipmentMaintenanceService.getEquipmentMaintenancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备保养计划管理.xls", "数据", EquipmentMaintenanceRespVO.class,
                        BeanUtils.toBean(list, EquipmentMaintenanceRespVO.class));
    }

}