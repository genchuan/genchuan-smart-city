package cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation.vo.VehicleInformationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation.vo.VehicleInformationRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation.vo.VehicleInformationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.vehicleinformation.VehicleInformationDO;
import cn.iocoder.yudao.module.smartcity.service.vehicleinformation.VehicleInformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 车辆信息")
@RestController
@RequestMapping("/smartcity/vehicle-information")
@Validated
public class VehicleInformationController {

    @Resource
    private VehicleInformationService vehicleInformationService;

    @PostMapping("/create")
    @Operation(summary = "创建车辆信息")
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-information:create')")
    public CommonResult<Long> createVehicleInformation(@Valid @RequestBody VehicleInformationSaveReqVO createReqVO) {
        return success(vehicleInformationService.createVehicleInformation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车辆信息")
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-information:update')")
    public CommonResult<Boolean> updateVehicleInformation(@Valid @RequestBody VehicleInformationSaveReqVO updateReqVO) {
        vehicleInformationService.updateVehicleInformation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车辆信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-information:delete')")
    public CommonResult<Boolean> deleteVehicleInformation(@RequestParam("id") Long id) {
        vehicleInformationService.deleteVehicleInformation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车辆信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-information:query')")
    public CommonResult<VehicleInformationRespVO> getVehicleInformation(@RequestParam("id") Long id) {
        VehicleInformationDO vehicleInformation = vehicleInformationService.getVehicleInformation(id);
        return success(BeanUtils.toBean(vehicleInformation, VehicleInformationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车辆信息分页")
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-information:query')")
    public CommonResult<PageResult<VehicleInformationRespVO>> getVehicleInformationPage(@Valid VehicleInformationPageReqVO pageReqVO) {
        PageResult<VehicleInformationDO> pageResult = vehicleInformationService.getVehicleInformationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, VehicleInformationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车辆信息 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-information:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportVehicleInformationExcel(@Valid VehicleInformationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<VehicleInformationDO> list = vehicleInformationService.getVehicleInformationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车辆信息.xls", "数据", VehicleInformationRespVO.class,
                        BeanUtils.toBean(list, VehicleInformationRespVO.class));
    }

}