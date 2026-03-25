package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle;

import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehiclestatus.VehicleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehiclestatus.VehicleStatusRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehiclestatus.VehicleStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleStatusDO;
import cn.iocoder.yudao.module.envirhealth.service.vehicle.vehiclestatus.VehicleStatusService;

@Tag(name = "字典表 - 车辆状态")
@RestController
@RequestMapping("/envirhealth/vehicle-status")
@Validated
public class VehicleStatusController {

    @Resource
    private VehicleStatusService vehicleStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建车辆状态字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-status:create')")
    public CommonResult<Long> createVehicleStatus(@Valid @RequestBody VehicleStatusSaveReqVO createReqVO) {
        return success(vehicleStatusService.createVehicleStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车辆状态字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-status:update')")
    public CommonResult<Boolean> updateVehicleStatus(@Valid @RequestBody VehicleStatusSaveReqVO updateReqVO) {
        vehicleStatusService.updateVehicleStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车辆状态字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-status:delete')")
    public CommonResult<Boolean> deleteVehicleStatus(@RequestParam("id") Long id) {
        vehicleStatusService.deleteVehicleStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车辆状态字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-status:query')")
    public CommonResult<VehicleStatusRespVO> getVehicleStatus(@RequestParam("id") Long id) {
        VehicleStatusDO vehicleStatus = vehicleStatusService.getVehicleStatus(id);
        return success(BeanUtils.toBean(vehicleStatus, VehicleStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车辆状态字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-status:query')")
    public CommonResult<PageResult<VehicleStatusRespVO>> getVehicleStatusPage(@Valid VehicleStatusPageReqVO pageReqVO) {
        PageResult<VehicleStatusDO> pageResult = vehicleStatusService.getVehicleStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, VehicleStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车辆状态字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportVehicleStatusExcel(@Valid VehicleStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<VehicleStatusDO> list = vehicleStatusService.getVehicleStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车辆状态字典.xls", "数据", VehicleStatusRespVO.class,
                        BeanUtils.toBean(list, VehicleStatusRespVO.class));
    }

    /**
     * 获得维护类型字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得车辆状态字典(下拉框)")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-status:query')")
    public CommonResult<List<OptionVO>> getVehicleStatusOptions() {
        return success(vehicleStatusService.getVehicleStatusOptions());
    }
}
