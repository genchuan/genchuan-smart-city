package cn.iocoder.yudao.module.smartcity.controller.admin.parkinglotinformation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.parkinglotinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parkinglotinformation.ParkingLotInformationDO;
import cn.iocoder.yudao.module.smartcity.service.parkinglotinformation.ParkingLotInformationService;

@Tag(name = "管理后台 - 停车场信息管理")
@RestController
@RequestMapping("/smartcity/parking-lot-information")
@Validated
public class ParkingLotInformationController {

    @Resource
    private ParkingLotInformationService parkingLotInformationService;

    @PostMapping("/create")
    @Operation(summary = "创建停车场信息管理")
    @PreAuthorize("@ss.hasPermission('smartcity:parking-lot-information:create')")
    public CommonResult<Long> createParkingLotInformation(@Valid @RequestBody ParkingLotInformationSaveReqVO createReqVO) {
        return success(parkingLotInformationService.createParkingLotInformation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车场信息管理")
    @PreAuthorize("@ss.hasPermission('smartcity:parking-lot-information:update')")
    public CommonResult<Boolean> updateParkingLotInformation(@Valid @RequestBody ParkingLotInformationSaveReqVO updateReqVO) {
        parkingLotInformationService.updateParkingLotInformation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车场信息管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:parking-lot-information:delete')")
    public CommonResult<Boolean> deleteParkingLotInformation(@RequestParam("id") Long id) {
        parkingLotInformationService.deleteParkingLotInformation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车场信息管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:parking-lot-information:query')")
    public CommonResult<ParkingLotInformationRespVO> getParkingLotInformation(@RequestParam("id") Long id) {
        ParkingLotInformationDO parkingLotInformation = parkingLotInformationService.getParkingLotInformation(id);
        return success(BeanUtils.toBean(parkingLotInformation, ParkingLotInformationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车场信息管理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:parking-lot-information:query')")
    public CommonResult<PageResult<ParkingLotInformationRespVO>> getParkingLotInformationPage(@Valid ParkingLotInformationPageReqVO pageReqVO) {
        PageResult<ParkingLotInformationDO> pageResult = parkingLotInformationService.getParkingLotInformationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkingLotInformationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出停车场信息管理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:parking-lot-information:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkingLotInformationExcel(@Valid ParkingLotInformationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkingLotInformationDO> list = parkingLotInformationService.getParkingLotInformationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车场信息管理.xls", "数据", ParkingLotInformationRespVO.class,
                        BeanUtils.toBean(list, ParkingLotInformationRespVO.class));
    }

}