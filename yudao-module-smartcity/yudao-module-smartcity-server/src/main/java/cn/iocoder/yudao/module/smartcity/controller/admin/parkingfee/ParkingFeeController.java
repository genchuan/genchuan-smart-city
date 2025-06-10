package cn.iocoder.yudao.module.smartcity.controller.admin.parkingfee;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.parkingfee.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parkingfee.ParkingFeeDO;
import cn.iocoder.yudao.module.smartcity.service.parkingfee.ParkingFeeService;

@Tag(name = "管理后台 - 停车收费管理")
@RestController
@RequestMapping("/smartcity/parking-fee")
@Validated
public class ParkingFeeController {

    @Resource
    private ParkingFeeService parkingFeeService;

    @PostMapping("/create")
    @Operation(summary = "创建停车收费管理")
    @PreAuthorize("@ss.hasPermission('smartcity:parking-fee:create')")
    public CommonResult<Long> createParkingFee(@Valid @RequestBody ParkingFeeSaveReqVO createReqVO) {
        return success(parkingFeeService.createParkingFee(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车收费管理")
    @PreAuthorize("@ss.hasPermission('smartcity:parking-fee:update')")
    public CommonResult<Boolean> updateParkingFee(@Valid @RequestBody ParkingFeeSaveReqVO updateReqVO) {
        parkingFeeService.updateParkingFee(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车收费管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:parking-fee:delete')")
    public CommonResult<Boolean> deleteParkingFee(@RequestParam("id") Long id) {
        parkingFeeService.deleteParkingFee(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车收费管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:parking-fee:query')")
    public CommonResult<ParkingFeeRespVO> getParkingFee(@RequestParam("id") Long id) {
        ParkingFeeDO parkingFee = parkingFeeService.getParkingFee(id);
        return success(BeanUtils.toBean(parkingFee, ParkingFeeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车收费管理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:parking-fee:query')")
    public CommonResult<PageResult<ParkingFeeRespVO>> getParkingFeePage(@Valid ParkingFeePageReqVO pageReqVO) {
        PageResult<ParkingFeeDO> pageResult = parkingFeeService.getParkingFeePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkingFeeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出停车收费管理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:parking-fee:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkingFeeExcel(@Valid ParkingFeePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkingFeeDO> list = parkingFeeService.getParkingFeePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车收费管理.xls", "数据", ParkingFeeRespVO.class,
                        BeanUtils.toBean(list, ParkingFeeRespVO.class));
    }

}