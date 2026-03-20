package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle;

import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicletype.VehicleTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicletype.VehicleTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicletype.VehicleTypeSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.vehicle.vehicletype.VehicleTypeService;

@Tag(name = "字典表 - 车辆类型")
@RestController
@RequestMapping("/envirhealth/vehicle-type")
@Validated
public class VehicleTypeController {

    @Resource
    private VehicleTypeService vehicleTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建车辆类型字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-type:create')")
    public CommonResult<Long> createVehicleType(@Valid @RequestBody VehicleTypeSaveReqVO createReqVO) {
        return success(vehicleTypeService.createVehicleType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车辆类型字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-type:update')")
    public CommonResult<Boolean> updateVehicleType(@Valid @RequestBody VehicleTypeSaveReqVO updateReqVO) {
        vehicleTypeService.updateVehicleType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车辆类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-type:delete')")
    public CommonResult<Boolean> deleteVehicleType(@RequestParam("id") Long id) {
        vehicleTypeService.deleteVehicleType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车辆类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-type:query')")
    public CommonResult<VehicleTypeRespVO> getVehicleType(@RequestParam("id") Long id) {
        VehicleTypeDO vehicleType = vehicleTypeService.getVehicleType(id);
        return success(BeanUtils.toBean(vehicleType, VehicleTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车辆类型字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-type:query')")
    public CommonResult<PageResult<VehicleTypeRespVO>> getVehicleTypePage(@Valid VehicleTypePageReqVO pageReqVO) {
        PageResult<VehicleTypeDO> pageResult = vehicleTypeService.getVehicleTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, VehicleTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车辆类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportVehicleTypeExcel(@Valid VehicleTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<VehicleTypeDO> list = vehicleTypeService.getVehicleTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车辆类型字典.xls", "数据", VehicleTypeRespVO.class,
                        BeanUtils.toBean(list, VehicleTypeRespVO.class));
    }

    /**
     * 获得维护类型字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得车辆类型字典(下拉框)")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle-type:query')")
    public CommonResult<List<OptionVO>> getVehicleTypeOptions() {
        return success(vehicleTypeService.getVehicleTypeOptions());
    }
}
