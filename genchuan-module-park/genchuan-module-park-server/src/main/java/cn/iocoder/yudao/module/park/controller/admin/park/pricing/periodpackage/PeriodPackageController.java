package cn.iocoder.yudao.module.park.controller.admin.park.pricing.periodpackage;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.periodpackage.vo.PeriodPackagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.periodpackage.vo.PeriodPackageRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.periodpackage.vo.PeriodPackageSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.periodpackage.PeriodPackageDO;
import cn.iocoder.yudao.module.park.service.park.pricing.periodpackage.PeriodPackageService;
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
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 期卡套餐")
@RestController
@RequestMapping("/park/period-package")
@Validated
public class PeriodPackageController {

    @Resource
    private PeriodPackageService periodPackageService;

    @PostMapping("/create")
    @Operation(summary = "创建期卡套餐")
    @PreAuthorize("@ss.hasPermission('park:period-package:create')")
    public CommonResult<Long> createPeriodPackage(@Valid @RequestBody PeriodPackageSaveReqVO createReqVO) {
        return success(periodPackageService.createPeriodPackage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新期卡套餐")
    @PreAuthorize("@ss.hasPermission('park:period-package:update')")
    public CommonResult<Boolean> updatePeriodPackage(@Valid @RequestBody PeriodPackageSaveReqVO updateReqVO) {
        periodPackageService.updatePeriodPackage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除期卡套餐")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:period-package:delete')")
    public CommonResult<Boolean> deletePeriodPackage(@RequestParam("id") Long id) {
        periodPackageService.deletePeriodPackage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得期卡套餐")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:period-package:query')")
    public CommonResult<PeriodPackageRespVO> getPeriodPackage(@RequestParam("id") Long id) {
        PeriodPackageDO periodPackage = periodPackageService.getPeriodPackage(id);
        return success(BeanUtils.toBean(periodPackage, PeriodPackageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得期卡套餐分页")
    @PreAuthorize("@ss.hasPermission('park:period-package:query')")
    public CommonResult<PageResult<PeriodPackageRespVO>> getPeriodPackagePage(@Valid PeriodPackagePageReqVO pageReqVO) {
        PageResult<PeriodPackageDO> pageResult = periodPackageService.getPeriodPackagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PeriodPackageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出期卡套餐 Excel")
    @PreAuthorize("@ss.hasPermission('park:period-package:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPeriodPackageExcel(@Valid PeriodPackagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PeriodPackageDO> list = periodPackageService.getPeriodPackagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "期卡套餐.xls", "数据", PeriodPackageRespVO.class,
                        BeanUtils.toBean(list, PeriodPackageRespVO.class));
    }

}
