package cn.iocoder.yudao.module.smartcity.controller.admin.parking;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.parking.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parking.ParkingDO;
import cn.iocoder.yudao.module.smartcity.service.parking.ParkingService;

@Tag(name = "管理后台 - 停车管理用户管理与服务")
@RestController
@RequestMapping("/smartcity/parking")
@Validated
public class ParkingController {

    @Resource
    private ParkingService parkingService;

    @PostMapping("/create")
    @Operation(summary = "创建停车管理用户管理与服务")
    @PreAuthorize("@ss.hasPermission('smartcity:parking:create')")
    public CommonResult<Long> createParking(@Valid @RequestBody ParkingSaveReqVO createReqVO) {
        return success(parkingService.createParking(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车管理用户管理与服务")
    @PreAuthorize("@ss.hasPermission('smartcity:parking:update')")
    public CommonResult<Boolean> updateParking(@Valid @RequestBody ParkingSaveReqVO updateReqVO) {
        parkingService.updateParking(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车管理用户管理与服务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:parking:delete')")
    public CommonResult<Boolean> deleteParking(@RequestParam("id") Long id) {
        parkingService.deleteParking(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车管理用户管理与服务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:parking:query')")
    public CommonResult<ParkingRespVO> getParking(@RequestParam("id") Long id) {
        ParkingDO parking = parkingService.getParking(id);
        return success(BeanUtils.toBean(parking, ParkingRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车管理用户管理与服务分页")
    @PreAuthorize("@ss.hasPermission('smartcity:parking:query')")
    public CommonResult<PageResult<ParkingRespVO>> getParkingPage(@Valid ParkingPageReqVO pageReqVO) {
        PageResult<ParkingDO> pageResult = parkingService.getParkingPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkingRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出停车管理用户管理与服务 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:parking:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkingExcel(@Valid ParkingPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkingDO> list = parkingService.getParkingPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车管理用户管理与服务.xls", "数据", ParkingRespVO.class,
                        BeanUtils.toBean(list, ParkingRespVO.class));
    }

}