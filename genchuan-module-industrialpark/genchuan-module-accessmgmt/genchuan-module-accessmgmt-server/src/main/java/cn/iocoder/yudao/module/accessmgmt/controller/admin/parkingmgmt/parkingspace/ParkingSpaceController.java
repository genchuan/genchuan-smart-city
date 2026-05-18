package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo.*;
import cn.iocoder.yudao.module.accessmgmt.service.parkingmgmt.parkingspace.ParkingSpaceService;
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

@Tag(name = "管理后台 - 车位管理")
@RestController
@RequestMapping("/accessmgmt/parking-space")
@Validated
@Hidden
public class ParkingSpaceController {

    @Resource
    private ParkingSpaceService parkingSpaceService;

    @GetMapping("/page")
    @Operation(summary = "获得车位信息分页")
    @PreAuthorize("@ss.hasPermission('parking-space:query')")
    public CommonResult<PageResult<ParkingSpaceRespVO>> getParkingSpacePage(@Valid ParkingSpacePageReqVO pageReqVO) {
        PageResult<ParkingSpaceRespVO> pageResult = parkingSpaceService.getParkingSpacePage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车位信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('parking-space:query')")
    public CommonResult<ParkingSpaceRespVO> getParkingSpace(@RequestParam("id") Long id) {
        ParkingSpaceRespVO respVO = parkingSpaceService.getParkingSpace(id);
        return success(respVO);
    }

    @PostMapping("/create")
    @Operation(summary = "创建车位信息")
    @PreAuthorize("@ss.hasPermission('parking-space:create')")
    public CommonResult<Boolean> createParkingSpace(@Valid @RequestBody ParkingSpaceCreateReqVO createReqVO) {
        Boolean result = parkingSpaceService.createParkingSpace(createReqVO);
        return success(result);
    }

    @PutMapping("/allocate")
    @Operation(summary = "分配车位")
    @PreAuthorize("@ss.hasPermission('parking-space:allocate')")
    public CommonResult<Boolean> allocateParkingSpace(@Valid @RequestBody ParkingSpaceAllocateReqVO reqVO) {
        Boolean result = parkingSpaceService.allocateParkingSpace(reqVO);
        return success(result);
    }

    @PostMapping("/reserve")
    @Operation(summary = "预约车位")
    @PreAuthorize("@ss.hasPermission('parking-space:reserve')")
    public CommonResult<Boolean> reserveParkingSpace(@Valid @RequestBody ParkingSpaceReserveReqVO reqVO) {
        Boolean result = parkingSpaceService.reserveParkingSpace(reqVO);
        return success(result);
    }

    @PutMapping("/release")
    @Operation(summary = "释放车位")
    @PreAuthorize("@ss.hasPermission('parking-space:release')")
    public CommonResult<Boolean> releaseParkingSpace(@Valid @RequestBody ParkingSpaceReleaseReqVO reqVO) {
        Boolean result = parkingSpaceService.releaseParkingSpace(reqVO);
        return success(result);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用车位")
    @PreAuthorize("@ss.hasPermission('parking-space:disable')")
    public CommonResult<Boolean> disableParkingSpace(@Valid @RequestBody ParkingSpaceDisableReqVO reqVO) {
        Boolean result = parkingSpaceService.disableParkingSpace(reqVO);
        return success(result);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消车位")
    @PreAuthorize("@ss.hasPermission('parking-space:cancel')")
    public CommonResult<Boolean> cancelParkingSpace(@Valid @RequestBody ParkingSpaceCancelReqVO reqVO) {
        Boolean result = parkingSpaceService.cancelParkingSpace(reqVO);
        return success(result);
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认车位")
    @PreAuthorize("@ss.hasPermission('parking-space:confirm')")
    public CommonResult<Boolean> confirmParkingSpace(@Valid @RequestBody ParkingSpaceConfirmReqVO reqVO) {
        Boolean result = parkingSpaceService.confirmParkingSpace(reqVO);
        return success(result);
    }

    @GetMapping("/export")
    @Operation(summary = "导出车位信息 Excel")
    @PreAuthorize("@ss.hasPermission('parking-space:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkingSpaceExcel(@Valid ParkingSpacePageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        List<ParkingSpaceRespVO> list = parkingSpaceService.getParkingSpaceList(pageReqVO);

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = "车位信息_" + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8", "");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        ExcelUtils.write(response, "车位信息.xls", "数据", ParkingSpaceRespVO.class,
                BeanUtils.toBean(list, ParkingSpaceRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "车位管理态势")
    @PreAuthorize("@ss.hasPermission('parking-space:query')")
    public CommonResult<ParkingSpaceChartRespVO> getParkingSpaceChart(
            @Parameter(name = "parkName", description = "停车场名称，支持模糊匹配") @RequestParam(value = "parkName", required = false) String parkName) {
        ParkingSpaceChartRespVO chartVO = parkingSpaceService.getParkingSpaceChart(parkName);
        return success(chartVO);
    }

}
