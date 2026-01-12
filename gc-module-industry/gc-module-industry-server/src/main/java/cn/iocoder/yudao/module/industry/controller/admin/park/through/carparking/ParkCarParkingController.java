package cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo.ParkCarParkingPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo.ParkCarParkingRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo.ParkCarParkingSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carparking.ParkCarParkingDO;
import cn.iocoder.yudao.module.industry.service.park.through.carparking.ParkCarParkingService;

@Tag(name = "管理后台 - 在停车辆")
@RestController
@RequestMapping("/industry/park-car-parking")
@Validated
public class ParkCarParkingController {

    @Resource
    private ParkCarParkingService parkCarParkingService;

    @PostMapping("/create")
    @Operation(summary = "创建在停车辆")
    @PreAuthorize("@ss.hasPermission('industry:park-car-parking:create')")
    public CommonResult<Long> createParkCarParking(@Valid @RequestBody ParkCarParkingSaveReqVO createReqVO) {
        return success(parkCarParkingService.createParkCarParking(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新在停车辆")
    @PreAuthorize("@ss.hasPermission('industry:park-car-parking:update')")
    public CommonResult<Boolean> updateParkCarParking(@Valid @RequestBody ParkCarParkingSaveReqVO updateReqVO) {
        parkCarParkingService.updateParkCarParking(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除在停车辆")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-car-parking:delete')")
    public CommonResult<Boolean> deleteParkCarParking(@RequestParam("id") Long id) {
        parkCarParkingService.deleteParkCarParking(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得在停车辆")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-car-parking:query')")
    public CommonResult<ParkCarParkingRespVO> getParkCarParking(@RequestParam("id") Long id) {
        ParkCarParkingDO parkCarParking = parkCarParkingService.getParkCarParking(id);
        return success(BeanUtils.toBean(parkCarParking, ParkCarParkingRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得在停车辆分页")
    @PreAuthorize("@ss.hasPermission('industry:park-car-parking:query')")
    public CommonResult<PageResult<ParkCarParkingRespVO>> getParkCarParkingPage(@Valid ParkCarParkingPageReqVO pageReqVO) {
        PageResult<ParkCarParkingDO> pageResult = parkCarParkingService.getParkCarParkingPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkCarParkingRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出在停车辆 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-car-parking:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkCarParkingExcel(@Valid ParkCarParkingPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkCarParkingDO> list = parkCarParkingService.getParkCarParkingPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "在停车辆.xls", "数据", ParkCarParkingRespVO.class,
                        BeanUtils.toBean(list, ParkCarParkingRespVO.class));
    }

}