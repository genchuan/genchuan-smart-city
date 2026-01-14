package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo.ParkRechargePackagePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo.ParkRechargePackageRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo.ParkRechargePackageSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkrechargepackage.ParkRechargePackageDO;
import cn.iocoder.yudao.module.industry.service.park.discount.parkrechargepackage.ParkRechargePackageService;
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


@Tag(name = "管理后台 - 充值套餐")
@RestController
@RequestMapping("/industry/park-recharge-package")
@Validated
public class ParkRechargePackageController {

    @Resource
    private ParkRechargePackageService parkRechargePackageService;

    @PostMapping("/create")
    @Operation(summary = "创建充值套餐")
    @PreAuthorize("@ss.hasPermission('industry:park-recharge-package:create')")
    public CommonResult<Long> createParkRechargePackage(@Valid @RequestBody ParkRechargePackageSaveReqVO createReqVO) {
        return success(parkRechargePackageService.createParkRechargePackage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新充值套餐")
    @PreAuthorize("@ss.hasPermission('industry:park-recharge-package:update')")
    public CommonResult<Boolean> updateParkRechargePackage(@Valid @RequestBody ParkRechargePackageSaveReqVO updateReqVO) {
        parkRechargePackageService.updateParkRechargePackage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除充值套餐")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-recharge-package:delete')")
    public CommonResult<Boolean> deleteParkRechargePackage(@RequestParam("id") Long id) {
        parkRechargePackageService.deleteParkRechargePackage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得充值套餐")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-recharge-package:query')")
    public CommonResult<ParkRechargePackageRespVO> getParkRechargePackage(@RequestParam("id") Long id) {
        ParkRechargePackageDO parkRechargePackage = parkRechargePackageService.getParkRechargePackage(id);
        return success(BeanUtils.toBean(parkRechargePackage, ParkRechargePackageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得充值套餐分页")
    @PreAuthorize("@ss.hasPermission('industry:park-recharge-package:query')")
    public CommonResult<PageResult<ParkRechargePackageRespVO>> getParkRechargePackagePage(@Valid ParkRechargePackagePageReqVO pageReqVO) {
        PageResult<ParkRechargePackageDO> pageResult = parkRechargePackageService.getParkRechargePackagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkRechargePackageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出充值套餐 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-recharge-package:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkRechargePackageExcel(@Valid ParkRechargePackagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkRechargePackageDO> list = parkRechargePackageService.getParkRechargePackagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "充值套餐.xls", "数据", ParkRechargePackageRespVO.class,
                        BeanUtils.toBean(list, ParkRechargePackageRespVO.class));
    }

}
