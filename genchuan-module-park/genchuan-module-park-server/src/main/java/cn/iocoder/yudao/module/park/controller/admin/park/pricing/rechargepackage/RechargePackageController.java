package cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage.vo.RechargePackagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage.vo.RechargePackageRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage.vo.RechargePackageSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.rechargepackage.RechargePackageDO;
import cn.iocoder.yudao.module.park.service.park.pricing.rechargepackage.RechargePackageService;
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


@Tag(name = "管理后台 - 充值套餐")
@RestController
@RequestMapping("/park/recharge-package")
@Validated
public class RechargePackageController {

    @Resource
    private RechargePackageService rechargePackageService;

    @PostMapping("/create")
    @Operation(summary = "创建充值套餐")
    @PreAuthorize("@ss.hasPermission('park:recharge-package:create')")
    public CommonResult<Long> createRechargePackage(@Valid @RequestBody RechargePackageSaveReqVO createReqVO) {
        return success(rechargePackageService.createRechargePackage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新充值套餐")
    @PreAuthorize("@ss.hasPermission('park:recharge-package:update')")
    public CommonResult<Boolean> updateRechargePackage(@Valid @RequestBody RechargePackageSaveReqVO updateReqVO) {
        rechargePackageService.updateRechargePackage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除充值套餐")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:recharge-package:delete')")
    public CommonResult<Boolean> deleteRechargePackage(@RequestParam("id") Long id) {
        rechargePackageService.deleteRechargePackage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得充值套餐")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:recharge-package:query')")
    public CommonResult<RechargePackageRespVO> getRechargePackage(@RequestParam("id") Long id) {
        RechargePackageDO rechargePackage = rechargePackageService.getRechargePackage(id);
        return success(BeanUtils.toBean(rechargePackage, RechargePackageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得充值套餐分页")
    @PreAuthorize("@ss.hasPermission('park:recharge-package:query')")
    public CommonResult<PageResult<RechargePackageRespVO>> getRechargePackagePage(@Valid RechargePackagePageReqVO pageReqVO) {
        PageResult<RechargePackageDO> pageResult = rechargePackageService.getRechargePackagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RechargePackageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出充值套餐 Excel")
    @PreAuthorize("@ss.hasPermission('park:recharge-package:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRechargePackageExcel(@Valid RechargePackagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RechargePackageDO> list = rechargePackageService.getRechargePackagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "充值套餐.xls", "数据", RechargePackageRespVO.class,
                        BeanUtils.toBean(list, RechargePackageRespVO.class));
    }

}
