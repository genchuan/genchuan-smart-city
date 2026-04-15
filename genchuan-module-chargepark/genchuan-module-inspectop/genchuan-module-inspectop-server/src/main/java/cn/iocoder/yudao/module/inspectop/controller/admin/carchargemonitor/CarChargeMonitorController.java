package cn.iocoder.yudao.module.inspectop.controller.admin.carchargemonitor;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.carchargemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.carchargemonitor.CarChargeMonitorDO;
import cn.iocoder.yudao.module.inspectop.service.carchargemonitor.CarChargeMonitorService;

@Tag(name = "管理后台 - 汽车充电监测")
@RestController
@RequestMapping("/inspectop/car-charge-monitor")
@Validated
public class CarChargeMonitorController {

    @Resource
    private CarChargeMonitorService carChargeMonitorService;

    @PostMapping("/create")
    @Operation(summary = "创建汽车充电监测")
    @PreAuthorize("@ss.hasPermission('inspectop:car-charge-monitor:create')")
    public CommonResult<Long> createCarChargeMonitor(@Valid @RequestBody CarChargeMonitorSaveReqVO createReqVO) {
        return success(carChargeMonitorService.createCarChargeMonitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新汽车充电监测")
    @PreAuthorize("@ss.hasPermission('inspectop:car-charge-monitor:update')")
    public CommonResult<Boolean> updateCarChargeMonitor(@Valid @RequestBody CarChargeMonitorSaveReqVO updateReqVO) {
        carChargeMonitorService.updateCarChargeMonitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除汽车充电监测")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:car-charge-monitor:delete')")
    public CommonResult<Boolean> deleteCarChargeMonitor(@RequestParam("id") Long id) {
        carChargeMonitorService.deleteCarChargeMonitor(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除汽车充电监测")
                @PreAuthorize("@ss.hasPermission('inspectop:car-charge-monitor:delete')")
    public CommonResult<Boolean> deleteCarChargeMonitorList(@RequestParam("ids") List<Long> ids) {
        carChargeMonitorService.deleteCarChargeMonitorListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得汽车充电监测")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:car-charge-monitor:query')")
    public CommonResult<CarChargeMonitorRespVO> getCarChargeMonitor(@RequestParam("id") Long id) {
        CarChargeMonitorDO carChargeMonitor = carChargeMonitorService.getCarChargeMonitor(id);
        return success(BeanUtils.toBean(carChargeMonitor, CarChargeMonitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得汽车充电监测分页")
    @PreAuthorize("@ss.hasPermission('inspectop:car-charge-monitor:query')")
    public CommonResult<PageResult<CarChargeMonitorRespVO>> getCarChargeMonitorPage(@Valid CarChargeMonitorPageReqVO pageReqVO) {
        PageResult<CarChargeMonitorDO> pageResult = carChargeMonitorService.getCarChargeMonitorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CarChargeMonitorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出汽车充电监测 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:car-charge-monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCarChargeMonitorExcel(@Valid CarChargeMonitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CarChargeMonitorDO> list = carChargeMonitorService.getCarChargeMonitorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "汽车充电监测.xls", "数据", CarChargeMonitorRespVO.class,
                        BeanUtils.toBean(list, CarChargeMonitorRespVO.class));
    }

}