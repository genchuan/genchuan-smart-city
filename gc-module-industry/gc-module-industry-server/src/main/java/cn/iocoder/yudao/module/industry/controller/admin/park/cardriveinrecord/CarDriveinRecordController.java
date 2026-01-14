package cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord;

import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord.vo.CarDriveinRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord.vo.CarDriveinRecordRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord.vo.CarDriveinRecordSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveinrecord.CarDriveinRecordDO;
import cn.iocoder.yudao.module.industry.service.park.cardriveinrecord.CarDriveinRecordService;

@Tag(name = "管理后台 - 车辆入场记录")
@RestController
@RequestMapping("/industry/car-drivein-record")
@Validated
public class CarDriveinRecordController {

    @Resource
    private CarDriveinRecordService carDriveinRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建车辆入场记录")
    @PreAuthorize("@ss.hasPermission('industry:car-drivein-record:create')")
    public CommonResult<Long> createCarDriveinRecord(@Valid @RequestBody CarDriveinRecordSaveReqVO createReqVO) {
        return success(carDriveinRecordService.createCarDriveinRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车辆入场记录")
    @PreAuthorize("@ss.hasPermission('industry:car-drivein-record:update')")
    public CommonResult<Boolean> updateCarDriveinRecord(@Valid @RequestBody CarDriveinRecordSaveReqVO updateReqVO) {
        carDriveinRecordService.updateCarDriveinRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车辆入场记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:car-drivein-record:delete')")
    public CommonResult<Boolean> deleteCarDriveinRecord(@RequestParam("id") Long id) {
        carDriveinRecordService.deleteCarDriveinRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车辆入场记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:car-drivein-record:query')")
    public CommonResult<CarDriveinRecordRespVO> getCarDriveinRecord(@RequestParam("id") Long id) {
        CarDriveinRecordDO carDriveinRecord = carDriveinRecordService.getCarDriveinRecord(id);
        return success(BeanUtils.toBean(carDriveinRecord, CarDriveinRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车辆入场记录分页")
    @PreAuthorize("@ss.hasPermission('industry:car-drivein-record:query')")
    public CommonResult<PageResult<CarDriveinRecordRespVO>> getCarDriveinRecordPage(@Valid CarDriveinRecordPageReqVO pageReqVO) {
        PageResult<CarDriveinRecordDO> pageResult = carDriveinRecordService.getCarDriveinRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CarDriveinRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车辆入场记录 Excel")
    @PreAuthorize("@ss.hasPermission('industry:car-drivein-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCarDriveinRecordExcel(@Valid CarDriveinRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CarDriveinRecordDO> list = carDriveinRecordService.getCarDriveinRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车辆入场记录.xls", "数据", CarDriveinRecordRespVO.class,
                        BeanUtils.toBean(list, CarDriveinRecordRespVO.class));
    }

}