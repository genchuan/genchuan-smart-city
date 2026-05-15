package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo.*;
import cn.iocoder.yudao.module.accessmgmt.service.parkingmgmt.vehicleaccess.VehicleAccessService;
import io.swagger.v3.oas.annotations.Hidden;
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
import java.nio.charset.StandardCharsets;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 车辆通行")
@RestController
@RequestMapping("/accessmgmt/vehicle-access")
@Validated
@Hidden
public class VehicleAccessController {

    @Resource
    private VehicleAccessService vehicleAccessService;

    @GetMapping("/page")
    @Operation(summary = "获得车辆通行分页")
    @PreAuthorize("@ss.hasPermission('vehicle-access:query')")
    public CommonResult<PageResult<VehicleAccessRespVO>> getVehicleAccessPage(@Valid VehicleAccessPageReqVO pageReqVO) {
        PageResult<VehicleAccessRespVO> pageResult = vehicleAccessService.getVehicleAccessPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车辆通行")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('vehicle-access:query')")
    public CommonResult<VehicleAccessRespVO> getVehicleAccess(@RequestParam("id") Long id) {
        VehicleAccessRespVO respVO = vehicleAccessService.getVehicleAccess(id);
        return success(respVO);
    }

    @PostMapping("/recognize")
    @Operation(summary = "车牌识别")
    @PreAuthorize("@ss.hasPermission('vehicle-access:recognize')")
    public CommonResult<VehicleAccessRecognizeRespVO> recognizeVehicleAccess(@Valid @RequestBody VehicleAccessRecognizeReqVO reqVO) {
        VehicleAccessRecognizeRespVO respVO = vehicleAccessService.recognizeVehicleAccess(reqVO);
        return success(respVO);
    }

    @PutMapping("/pass")
    @Operation(summary = "车辆放行")
    @PreAuthorize("@ss.hasPermission('vehicle-access:pass')")
    public CommonResult<Boolean> passVehicleAccess(@Valid @RequestBody VehicleAccessPassReqVO reqVO) {
        Boolean result = vehicleAccessService.passVehicleAccess(reqVO);
        return success(result);
    }

    @PutMapping("/block")
    @Operation(summary = "车辆拦截")
    @PreAuthorize("@ss.hasPermission('vehicle-access:block')")
    public CommonResult<Boolean> blockVehicleAccess(@Valid @RequestBody VehicleAccessBlockReqVO reqVO) {
        Boolean result = vehicleAccessService.blockVehicleAccess(reqVO);
        return success(result);
    }

    @PostMapping("/calculate")
    @Operation(summary = "费用计算")
    @PreAuthorize("@ss.hasPermission('vehicle-access:calculate')")
    public CommonResult<VehicleAccessCalculateRespVO> calculateVehicleAccess(@Valid @RequestBody VehicleAccessCalculateReqVO reqVO) {
        VehicleAccessCalculateRespVO respVO = vehicleAccessService.calculateVehicleAccess(reqVO);
        return success(respVO);
    }

    @PostMapping("/pay")
    @Operation(summary = "停车缴费")
    @PreAuthorize("@ss.hasPermission('vehicle-access:pay')")
    public CommonResult<VehicleAccessPayRespVO> payVehicleAccess(@Valid @RequestBody VehicleAccessPayReqVO reqVO) {
        VehicleAccessPayRespVO respVO = vehicleAccessService.payVehicleAccess(reqVO);
        return success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出车辆通行 Excel")
    @PreAuthorize("@ss.hasPermission('vehicle-access:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportVehicleAccessExcel(@Valid VehicleAccessPageReqVO pageReqVO,
                                         HttpServletResponse response) throws IOException {
        List<VehicleAccessRespVO> list = vehicleAccessService.getVehicleAccessList(pageReqVO);

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = "车辆通行_" + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8", "");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        ExcelUtils.write(response, "车辆通行.xls", "数据", VehicleAccessRespVO.class,
                BeanUtils.toBean(list, VehicleAccessRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "车辆通行态势")
    @PreAuthorize("@ss.hasPermission('vehicle-access:query')")
    public CommonResult<VehicleAccessChartRespVO> getVehicleAccessChart(
            @Parameter(name = "startTime", description = "统计开始时间，格式时间戳") @RequestParam(value = "startTime", required = false) Long startTime,
            @Parameter(name = "endTime", description = "统计结束时间，格式时间戳") @RequestParam(value = "endTime", required = false) Long endTime) {
        VehicleAccessChartRespVO chartVO = vehicleAccessService.getVehicleAccessChart(startTime, endTime);
        return success(chartVO);
    }

}
