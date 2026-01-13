package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo.ParkPeriodPackagePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo.ParkPeriodPackageRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo.ParkPeriodPackageSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkperiodpackage.ParkPeriodPackageDO;
import cn.iocoder.yudao.module.industry.service.park.discount.parkperiodpackage.ParkPeriodPackageService;
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


@Tag(name = "漳州停车管理-收费优惠域 - 期卡套餐")
@RestController
@RequestMapping("/industry/park-period-package")
@Validated
public class ParkPeriodPackageController {

    @Resource
    private ParkPeriodPackageService parkPeriodPackageService;

    @PostMapping("/create")
    @Operation(summary = "创建期卡套餐")
    @PreAuthorize("@ss.hasPermission('industry:park-period-package:create')")
    public CommonResult<Long> createParkPeriodPackage(@Valid @RequestBody ParkPeriodPackageSaveReqVO createReqVO) {
        return success(parkPeriodPackageService.createParkPeriodPackage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新期卡套餐")
    @PreAuthorize("@ss.hasPermission('industry:park-period-package:update')")
    public CommonResult<Boolean> updateParkPeriodPackage(@Valid @RequestBody ParkPeriodPackageSaveReqVO updateReqVO) {
        parkPeriodPackageService.updateParkPeriodPackage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除期卡套餐")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-period-package:delete')")
    public CommonResult<Boolean> deleteParkPeriodPackage(@RequestParam("id") Long id) {
        parkPeriodPackageService.deleteParkPeriodPackage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得期卡套餐")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-period-package:query')")
    public CommonResult<ParkPeriodPackageRespVO> getParkPeriodPackage(@RequestParam("id") Long id) {
        ParkPeriodPackageDO parkPeriodPackage = parkPeriodPackageService.getParkPeriodPackage(id);
        return success(BeanUtils.toBean(parkPeriodPackage, ParkPeriodPackageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得期卡套餐分页")
    @PreAuthorize("@ss.hasPermission('industry:park-period-package:query')")
    public CommonResult<PageResult<ParkPeriodPackageRespVO>> getParkPeriodPackagePage(@Valid ParkPeriodPackagePageReqVO pageReqVO) {
        PageResult<ParkPeriodPackageDO> pageResult = parkPeriodPackageService.getParkPeriodPackagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkPeriodPackageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出期卡套餐 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-period-package:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkPeriodPackageExcel(@Valid ParkPeriodPackagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkPeriodPackageDO> list = parkPeriodPackageService.getParkPeriodPackagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "期卡套餐.xls", "数据", ParkPeriodPackageRespVO.class,
                        BeanUtils.toBean(list, ParkPeriodPackageRespVO.class));
    }

}
