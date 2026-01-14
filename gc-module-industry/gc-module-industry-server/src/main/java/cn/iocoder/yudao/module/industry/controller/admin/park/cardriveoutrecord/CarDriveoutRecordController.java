package cn.iocoder.yudao.module.industry.controller.admin.park.cardriveoutrecord;

import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveoutrecord.vo.CarDriveoutRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveoutrecord.vo.CarDriveoutRecordRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveoutrecord.vo.CarDriveoutRecordSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveoutrecord.CarDriveoutRecordDO;
import cn.iocoder.yudao.module.industry.service.park.cardriveoutrecord.CarDriveoutRecordService;

@Tag(name = "管理后台 - 车辆出场记录")
@RestController
@RequestMapping("/industry/car-driveout-record")
@Validated
public class CarDriveoutRecordController {

    @Resource
    private CarDriveoutRecordService carDriveoutRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建车辆出场记录")
    @PreAuthorize("@ss.hasPermission('industry:car-driveout-record:create')")
    public CommonResult<Long> createCarDriveoutRecord(@Valid @RequestBody CarDriveoutRecordSaveReqVO createReqVO) {
        return success(carDriveoutRecordService.createCarDriveoutRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车辆出场记录")
    @PreAuthorize("@ss.hasPermission('industry:car-driveout-record:update')")
    public CommonResult<Boolean> updateCarDriveoutRecord(@Valid @RequestBody CarDriveoutRecordSaveReqVO updateReqVO) {
        carDriveoutRecordService.updateCarDriveoutRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车辆出场记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:car-driveout-record:delete')")
    public CommonResult<Boolean> deleteCarDriveoutRecord(@RequestParam("id") Long id) {
        carDriveoutRecordService.deleteCarDriveoutRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车辆出场记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:car-driveout-record:query')")
    public CommonResult<CarDriveoutRecordRespVO> getCarDriveoutRecord(@RequestParam("id") Long id) {
        CarDriveoutRecordDO carDriveoutRecord = carDriveoutRecordService.getCarDriveoutRecord(id);
        return success(BeanUtils.toBean(carDriveoutRecord, CarDriveoutRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车辆出场记录分页")
    @PreAuthorize("@ss.hasPermission('industry:car-driveout-record:query')")
    public CommonResult<PageResult<CarDriveoutRecordRespVO>> getCarDriveoutRecordPage(@Valid CarDriveoutRecordPageReqVO pageReqVO) {
        PageResult<CarDriveoutRecordDO> pageResult = carDriveoutRecordService.getCarDriveoutRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CarDriveoutRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车辆出场记录 Excel")
    @PreAuthorize("@ss.hasPermission('industry:car-driveout-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCarDriveoutRecordExcel(@Valid CarDriveoutRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CarDriveoutRecordDO> list = carDriveoutRecordService.getCarDriveoutRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车辆出场记录.xls", "数据", CarDriveoutRecordRespVO.class,
                        BeanUtils.toBean(list, CarDriveoutRecordRespVO.class));
    }

}