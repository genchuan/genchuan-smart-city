package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehicleOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehiclePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehicleRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehicleSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.vehicle.vehicle.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "环境卫生管理 - 车辆")
@RestController
@RequestMapping("/envirhealth/vehicle")
@Validated
public class VehicleController {

    @Resource
    private VehicleService vehicleService;

    @PostMapping("/create")
    @Operation(summary = "创建车辆")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle:create')")
    public CommonResult<Long> createVehicle(@Valid @RequestBody VehicleSaveReqVO createReqVO) {
        return success(vehicleService.createVehicle(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车辆")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle:update')")
    public CommonResult<Boolean> updateVehicle(@Valid @RequestBody VehicleSaveReqVO updateReqVO) {
        vehicleService.updateVehicle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车辆")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle:delete')")
    public CommonResult<Boolean> deleteVehicle(@RequestParam("id") Long id) {
        vehicleService.deleteVehicle(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车辆")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle:query')")
    public CommonResult<VehicleRespVO> getVehicle(@RequestParam("id") Long id) {
        VehicleDO vehicle = vehicleService.getVehicle(id);
        return success(BeanUtils.toBean(vehicle, VehicleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车辆分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle:query')")
    public CommonResult<PageResult<VehicleRespVO>> getVehiclePage(@Valid VehiclePageReqVO pageReqVO) {
        PageResult<VehicleDO> pageResult = vehicleService.getVehiclePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, VehicleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车辆 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportVehicleExcel(@Valid VehiclePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<VehicleDO> list = vehicleService.getVehiclePage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("车辆_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "车辆.xls", "数据", VehicleRespVO.class,
                        BeanUtils.toBean(list, VehicleRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得车辆详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:vehicle:query')")
    public CommonResult<PageResult<VehicleDetailDO>> getVehicleDetailPage(
            @Valid VehiclePageReqVO pageReqVO) {
        PageResult<VehicleDetailDO> pageResult =
                vehicleService.getVehicleDetailPage(pageReqVO);

        return success(pageResult);
    }

    /**
     * 获得车辆下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得车辆(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:vehicle:query')")
    public CommonResult<List<VehicleOptionVO>> getVehicleOptions() {
        return success(vehicleService.getVehicleOptions());
    }
}
